
## classpath
```java
 Resource resource = new ClassPathResource("/config/111.json");
 //此时不需要写classpath
 //Resource resource = new ClassPathResource("classpath:/config/111.json");
InputStream is = resource.getInputStream();
```
