controller单例引起的线程错误
--

#### 1、主要代码

```java
@RestController
public class HelloController {

    @Autowired
    private TestThread thread;

    @GetMapping("/hello")
    public String hello() {
        thread.start();
        return "success";
    }
}

```

```java
@Component("TestThread")
@Scope("prototype")
public class TestThread extends Thread {
    @Override
    public void run() {
        System.out.println("this is run with id:" + Thread.currentThread().getId());
    }
}
```

#### 2、报错现象

  前台请求controller的服务。  
  第一次请求没问题，第二次请求就后台报错。
  ```
  java.lang.IllegalThreadStateException
  ```
  
#### 3、分析后发现：

  (a)同一个thread对象，start方法只能调用一次;  
  (b)`controller`是单例的，所以即使`TestThread`的类型不是单例的，但是单例的`controller`最终使用的都是同一个`TestThread`。
  
  

