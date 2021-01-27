
> https://www.hangge.com/blog/cache/detail_2504.html
```java
@Controller
public class MyErrorController extends BasicErrorController {
    @Autowired
    public MyErrorController(ErrorAttributes errorAttributes,
                             ServerProperties serverProperties,
                             List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }
 
   //针对返回类型是html的 
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        // 获取 Spring Boot 默认提供的错误信息，然后添加一个自定义的错误信息
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("msg", "出错啦++++++");
        ModelAndView modelAndView = new ModelAndView("errorPage", model, status);
        return modelAndView;
    }
 
    //针对其他类型的
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        // 获取 Spring Boot 默认提供的错误信息，然后添加一个自定义的错误信息
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        body.put("msg", "出错啦------");
        return new ResponseEntity<>(body, status);
    }
}

```
