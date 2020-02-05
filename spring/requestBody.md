

### 使用requestBody可以进行json到对象的映射

```java
@Data
public class Hello
{
    private String userName;
    
    private int age;
}
```

```java
@PostMapping("/hello")
public String hello(@RequestBody Hello hello)
{
	return hello.toString();
}
```

> 请求
```curl
curl -X POST \
  http://127.0.0.1:21020/test-service/hello \
  -d '{
	
	"userName":"Dave",
	"age":25
	
}'
```
