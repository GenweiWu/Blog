

> test.ip和test.port是环境变量的key，这里要求能动态取这些变量的值，来创建client进行请求
```java
@CosEndpoint(ipKeyInEnv = "test.ip", portKeyInEnv = "test.port")
public interface TestClient {

    @POST
    Call<Resp> test(@Url String url, @Body Req req);
}
```
