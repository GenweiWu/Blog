maven resource打包
==

### 1. 指定资源目录
maven默认资源目录是`src/main/resource`,也可以自己指定目录

> 比如资源目录在`src/my-resources`目录
```xml
<resources>
   <resource>
     <directory>src/my-resources</directory>
   </resource>
 </resources>
```


---
参考
https://maven.apache.org/plugins/maven-resources-plugin/examples/resource-directory.html
