

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

### 可以结合JsonProperty来自定义映射的变量名

```java
@Data
public class Hello
{
    @JsonProperty("name")
    private String userName;
    
    private int age;
}
```

> 此时请求的userName变成name了，但是还可以成功
```
curl -X POST \
  http://127.0.0.1:21020/test-service/hello \
  -d '{
	
	"name":"Dave",
	"age":25
	
}'
```
