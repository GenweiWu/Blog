Spring Cloud Zuul
==
## 路径匹配

#### 1、基础

|通配符	|说明|
|--|--|
|?	|匹配任意的单个字符|
|*	|匹配任意数量的字符|
|**	|匹配任意数量的字符，支持多级目录|

#### 2、匹配优先级
如果一个路径被多个规则匹配，则按照先后顺序，排在前面的被匹配；
所以，要用yaml格式，而不是properties格式

## Zuul核心过滤器
|过滤器|	order	|描述|	类型|
|-|-|-|-|
|RibbonRoutingFilter	|10	|如果写配置的时候用ServiceId则用这个route过滤器，该过滤器可以用Ribbon 做负载均衡，用hystrix做熔断|	route|
|SimpleHostRoutingFilter|	100	|如果写配置的时候用url则用这个route过滤|	route|


## 路由的原理

> 路由配置的样例
```yaml
zuul:
  routes:
    user-service-ext:
      path: /user-service/ext/**
      serviceId: user-service-ext
    user-service:
      path: /user-service/**
      serviceId: user-service
```  

> 处理路由的主要代码:  
> org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter#run
```java
@Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        //省略代码

        String uri = this.helper.buildZuulRequestURI(request);
        this.helper.addIgnoredHeaders();

        try {
            CloseableHttpResponse response = forward(this.httpClient, verb, uri, request,
                    headers, params, requestEntity);
            setResponse(response);
        }
        catch (Exception ex) {
            throw new ZuulRuntimeException(ex);
        }
        return null;
    }
```

> 继而调用这里，这里完成了url到具体host的映射  
> org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter#forward

具体映射地址在
```java
RequestContext.getCurrentContext().get("proxy")
```
具体的匹配细节，继续看下面

## 路由匹配细节
> org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator#getSimpleMatchingRoute
```java
protected Route getSimpleMatchingRoute(final String path) {
		if (log.isDebugEnabled()) {
			log.debug("Finding route for path: " + path);
		}

		if (this.routes.get() == null) {
			this.routes.set(locateRoutes());
		}

		if (log.isDebugEnabled()) {
			log.debug("servletPath=" + this.dispatcherServletPath);
			log.debug("zuulServletPath=" + this.zuulServletPath);
			log.debug("RequestUtils.isDispatcherServletRequest()="
					+ RequestUtils.isDispatcherServletRequest());
			log.debug("RequestUtils.isZuulServletRequest()="
					+ RequestUtils.isZuulServletRequest());
		}

		String adjustedPath = adjustPath(path);

    //就在这里开始匹配，只要找到一个匹配的就结束，所以顺序很重要
		ZuulRoute route = getZuulRoute(adjustedPath);

		return getRoute(route, adjustedPath);
	}
```


> org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator#getZuulRoute
```java
protected ZuulRoute getZuulRoute(String adjustedPath) {
		if (!matchesIgnoredPatterns(adjustedPath)) {
			for (Entry<String, ZuulRoute> entry : this.routes.get().entrySet()) {
				String pattern = entry.getKey();
				log.debug("Matching pattern:" + pattern);
        //顺序很重要
				if (this.pathMatcher.match(pattern, adjustedPath)) {
					return entry.getValue();
				}
			}
		}
		return null;
	}
```

## 通过日志定位问题

#### 1、日志分析
 
下面的日志信息可以知道最终匹配到哪个route了，就知道有没有匹配错了
```
2018-07-18 14:16:07.587 DEBUG 11804 --- [.1-11005-exec-4] o.s.c.n.zuul.filters.SimpleRouteLocator  : route matched=ZuulProperties.ZuulRoute(id=client1-service-url, path=/client1-url/**, serviceId=null, url=https://1.2.3.4:8080, stripPrefix=false, retryable=true, sensitiveHeaders=[Set-Cookie, Authorization], customSensitiveHeaders=true)
```

#### 2、日志设置
1. 开启zuul的日志(可以不设置，默认就是开启的)
```
zuul组件要设置`zuul.debug.request=true`
```
2. 日志配置要打开spring的debug级别的配置
这样设置，可以避免debug日志太多的问题，同时又能看到route信息
```xml
<logger name="org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator" additivity="true" level="debug">
    <appender-ref ref="CONSOLE" />
    <appender-ref ref="FILE" />
</logger>
```    

## 遇到的route匹配问题

#### 1.、问题描述
发现配置文件顺序是对的，但是匹配的时候内存中并不是文件的顺序，导致匹配错误；

#### 2、问题分析
初步测试发现，是因为configServer中也有相应的配置，主要是覆写host地址，不连接配置中心顺序就对了，奇怪。

样例见下方：

> 本地
```yaml
zuul:
  routes:
    other-service:
      path: /other-service/**
      serviceId: other-service
    user-service-ext:
      path: /user-service/ext/**
      serviceId: user-service
    user-service:
      path: /user-service/**
      url: http://host111:port 
```  

> configServer(修改host地址)
```yaml
zuul:
  routes:
    user-service:
      url: http://host111_xxx:port   
```  

> 最终的顺序变成：
```yaml
zuul:
  routes:
    user-service:
      path: /user-service/**
      url: http://host111:port 
    other-service:
      path: /other-service/**
      serviceId: other-service
    user-service-ext:
      path: /user-service/ext/**
      serviceId: user-service  
```  
导致`/user-service/**` 优先于 `/user-service/ext/**`  
看起来效果就是configServer配置会重新插入到本地配置的前面去

#### 3、规避方法
配置中心中需要同时包含关注优先级的几个配置项，即加上
```yaml
user-service-ext:
      path: /user-service/ext/**
      serviceId: user-service
```      
(多试几次，你可能只加了个别的配置项，比如我发现加path有效果，但是像retryable: true这种可能没用)




## 参考:
- [SpringCloud实战小贴士：Zuul的路径匹配 | 程序猿DD](http://blog.didispace.com/spring-cloud-tips-zuul-path-config/)
- [深入理解Zuul之源码解析 - CSDN博客](https://blog.csdn.net/forezp/article/details/76211680)
