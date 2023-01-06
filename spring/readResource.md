
## 文件扫描

### 方法1：使用PathMatchingResourcePatternResolver

> src/main/resource/process文件夹下的所有xml文件
```java
ClassLoader cl = this.getClass().getClassLoader();
ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
Resource[] resources = resolver.getResources("classpath:process/*.xml") ;
```

### 方法2：使用org.reflection开源包
> src/main/resource下(实际上是所有java class下都可以)所有文件名符合-process.xml的文件
> 比如  a-process.xml
```java
try {
    ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
    Collection<URL> urls = ClasspathHelper.forJavaClassPath();
    configurationBuilder.addUrls(urls);
    configurationBuilder.setScanners(new ResourcesScanner());
    Reflections reflections = new Reflections(configurationBuilder);
    Set<String> resources = reflections.getResources(Pattern.compile(".*?-process\\.xml"));

    if (resources == null || resources.isEmpty()) {
        log.warn("cannot found step files");
        return;
    }

    ClassLoader classLoader = this.getClass().getClassLoader();
    for (String resource : resources) {
        try (InputStream inputStream = classLoader.getResourceAsStream(resource)) {
           //TODO
        }
    }
} catch (IOException e) {
    log.error("read step file failed", e);
}
```


## classpath
```java
 Resource resource = new ClassPathResource("/config/111.json");
 //此时不需要写classpath
 //Resource resource = new ClassPathResource("classpath:/config/111.json");
InputStream is = resource.getInputStream();
```
