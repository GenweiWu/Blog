
## 启用zipkin

(1)引用zipkin依赖包
```xml
<!-- zipkin -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
<dependency>
  <groupId>io.zipkin.java</groupId>
  <artifactId>zipkin</artifactId>
  <version>1.23.2</version>
</dependency>
<dependency>
  <groupId>io.zipkin.reporter</groupId>
  <artifactId>zipkin-reporter</artifactId>
  <version>0.7.0</version>
</dependency>
```

(2)开启zipkin
```
spring.zipkin.enabled: true
```
或不设置，因为
```java
@ConditionalOnProperty(value = "spring.zipkin.enabled", matchIfMissing = true)
public class ZipkinAutoConfiguration {
...
}
```

## 关闭zipkin
```yaml
spring.zipkin.enabled: false
```
