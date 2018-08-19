
## springBoot server static resource
在SpringBoot中提供类似tomcat的静态资源访问能力

## 静态资源映射
比如你上传了一个html格式的文件(可能还包含了一堆js、css资源呢)到后台文件服务器，你没法直接罩一个tomcat来提供文件访问，那么就需要自己来提供静态资源访问

### 1)springBoot中的方法
```java
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("file:/opt/files/");
    }
}
```
上面的效果是：
`http://localhost:8081/resources/111/222.html`  
==> 
`/opt/files/111/222.html`  

### 2)在rest中部分映射
如果需要在restController中部分映射，即部分情况使用restController的逻辑，另外的情况是静态资源访问的话，可以使用redirect或forward

- 方法1：redirect处理

```java
@GetMapping(value = "/viewHighlightDoc/**")
public String viewHighlightDoc(@RequestParam(value = "key", required = false) String key,
    HttpServletRequest request, HttpServletResponse response)
{
    if (ValidateUtil.isNotEmptyString(key))
    {
        //...本身的处理逻辑
        return "xx";
    }

    //转发给静态资源处理
    // /service111/viewHighlightDoc/[xxx] => /service111/resources/[xxx]
    String sourceUri = request.getRequestURI();
    String targetUri = sourceUri.replace("/viewHighlightDoc", "/resources");
    log.debug("viewHighlightDoc redirect from [{}] to [{}] finish", sourceUri, targetUri);

    try
    {
        response.sendRedirect(targetUri);
    }
    catch (IOException e)
    {
        log.error("viewHighlightDoc redirect to {} failed", targetUri, e);
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
    return null;
}
```

- 方法2：forward处理
```java
    @GetMapping(value = "/viewHighlightDoc/**")
    public String viewHighlightDoc(@RequestParam(value = "key", required = false) String key,
        HttpServletRequest request, HttpServletResponse response)
    {
        if (ValidateUtil.isNotEmptyString(key))
        {
            //...本身的处理逻辑
            return "xx";
        }

        //转发给静态资源处理
        // /service111/viewHighlightDoc/[xxx] => /service111/resources/[xxx]
		// /service111/resources/[xxx] => /resources/[xxx]
        String sourceUri = request.getRequestURI();
        String targetUri = sourceUri.replace("/viewHighlightDoc", "/resources");
        targetUri = targetUri.replace(request.getContextPath(), "");
        log.debug("viewHighlightDoc redirect from [{}] to [{}] finish", sourceUri, targetUri);

        try
        {
            request.getRequestDispatcher(targetUri).forward(request,response);
        }
        catch (IOException | ServletException e)
        {
            log.error("viewHighlightDoc redirect to {} failed", targetUri, e);
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return null;
    }
```

#### 区别总结
1. redirect时,是包含content-path的(service111),所以forward支持跨应用的;而forward是不带content-path的,所以只能应用中转发
2. redirect会导致同一个请求在前台请求两次,一次302,一次200，会导致网页加载变慢!而forward在前台还是一次请求,所以性能要好一些

## 自定义资源映射 与 默认映射

### 默认映射

- 说明  
根据官方说明，系统初始化时候，会自动对resource目录下的多个指定目录进行映射

> 附官方说明：https://spring.io/blog/2013/12/19/serving-static-web-content-with-spring-boot
```
/META-INF/resources/
/resources/
/static/
/public/
```

- 个人测试结果  
个人测试结果为：的确可以，但是这些目录是放在resources目录下的，否则可能需要手工修改打包配置
> 工程目录如下：
```
F:\workspace\springaopdemo>tree /f src
卷 学习 的文件夹 PATH 列表
卷序列号为 000001CE 4E60:7797
F:\WORKSPACE\SPRINGAOPDEMO\SRC
├─main
│  ├─java
│  │  └─com
│  │      └─njust
│  │          │  AopApplication.java
│  │          │  WebLogAspect.java
│  │          │
│  │          ├─config
│  │          │      MvcConfig.java
│  │          │
│  │          └─controller
│  │                  HelloController.java
│  │                  HelloController222.java
│  │
│  └─resources
│      │  bootstrap.yml
│      │
│      ├─public
│      │  └─test
│      │          111.html   
│      │
│      └─static
│              222.html
│
└─test
    └─java
```

则对应的访问链接为：
- http://127.0.0.1:8080/test/111.html  200
- http://127.0.0.1:8080/222.html  200

### 使用EnableWebMvc导致默认配置失效
最近使用上面的自定义映射后，发现系统默认的static目录资源都无法访问了

- spring自动配置  

其中的自动配置代码: 
`org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#addResourceHandlers`
```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	if (!this.resourceProperties.isAddMappings()) {
		logger.debug("Default resource handling disabled");
		return;
	}
	Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
	CacheControl cacheControl = this.resourceProperties.getCache()
			.getCachecontrol().toHttpCacheControl();
	//(1)		
	if (!registry.hasMappingForPattern("/webjars/**")) {
		customizeResourceHandlerRegistration(registry
				.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/")
				.setCachePeriod(getSeconds(cachePeriod))
				.setCacheControl(cacheControl));
	}
	String staticPathPattern = this.mvcProperties.getStaticPathPattern();
	//(2)
	if (!registry.hasMappingForPattern(staticPathPattern)) {
		customizeResourceHandlerRegistration(
				registry.addResourceHandler(staticPathPattern)
						.addResourceLocations(getResourceLocations(
								this.resourceProperties.getStaticLocations()))
						.setCachePeriod(getSeconds(cachePeriod))
						.setCacheControl(cacheControl));
	}
}
```

其中(2)就是默认配置的代码,调试发现效果是添加了下面的映射
```
addResourceHandler("/**")

addResourceLocations
classpath:/META-INF/resources/
classpath:/resources/
classpath:/static/
classpath:/public/
/
```

- 默认配置失效原因  
参考：https://www.cnblogs.com/sufferingStriver/p/9026764.html

`WebMvcAutoConfiguration`类上有`@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)`;  
而`@EnableWebMvc`就是引用了`DelegatingWebMvcConfiguration`,就是继承了`WebMvcConfigurationSupport`，所以上面的代码就不运行了。  
```java
@Import(DelegatingWebMvcConfiguration.class)
public @interface EnableWebMvc {
}
...
```

```java
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
...
}
```

### 问题修复

- 自定义映射的同时，加上默认映射即可
```java
package com.njust.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    private static final String[] RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/wResource/**")
                .addResourceLocations("file:F:/go/");
        //加上默认映射
        registry.addResourceHandler("/**").addResourceLocations(
                RESOURCE_LOCATIONS);
    }
}

```

- 对应的日志也可以看出来
> 默认的,不自定义映射
```
2018-08-19 15:00:57.534  INFO 11700 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2018-08-19 15:00:57.535  INFO 11700 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
```
> 加了自定义映射，导致默认映射失效
```
2018-08-19 15:02:10.493  INFO 13112 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/wResource/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
```
> 修复问题后
```
2018-08-19 15:34:44.395  INFO 2728 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/wResource/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2018-08-19 15:34:44.395  INFO 2728 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
```







## 参考
- https://www.baeldung.com/spring-mvc-static-resources
- https://stackoverflow.com/a/29088306/6182927
```java
@RestController
public class FooController {

  @RequestMapping("/foo")
  void handleFoo(HttpServletResponse response) throws IOException {
    response.sendRedirect("some-url");
  }
}
```
