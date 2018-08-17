
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
