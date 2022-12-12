
## 文件扫描
```java
//src/main/resource/test文件夹下的所有xml文件
 File dir = ResourceUtils.getFile("classpath:test");
            FilenameFilter filenameFilter = new SuffixFileFilter(".xml");
            File[] files = dir.listFiles(filenameFilter);
```


## classpath
```java
 Resource resource = new ClassPathResource("/config/111.json");
 //此时不需要写classpath
 //Resource resource = new ClassPathResource("classpath:/config/111.json");
InputStream is = resource.getInputStream();
```
