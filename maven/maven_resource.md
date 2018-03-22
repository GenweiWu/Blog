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

### 2. Filtering

对某个目录进行filtering处理，一方面可以实现资源文件的`${name}`这种变量进行替换；另一方面，会导致一些二进制文件发生变化，无法正常使用(像png、mp4、ttf文件等)
```xml
 <resource>
  <directory>src/main/resources</directory>
  <filtering>true</filtering>
</resource>
```

### 3. include和exclude目录或文件

#### include
```xml
 <resources>
   <resource>
     <directory>src/my-resources</directory>
     <includes>
       <include>**/*.txt</include>
       <include>**/*.rtf</include>
     </includes>
   </resource>
   ...
 </resources>
```
注意：这样会导致打包后只保留了对应的文件

#### exclude
```xml
<resources>
   <resource>
     <directory>src/my-resources</directory>
     <excludes>
       <exclude>**/*.bmp</exclude>
       <exclude>**/*.jpg</exclude>
       <exclude>**/*.jpeg</exclude>
       <exclude>**/*.gif</exclude>
     </excludes>
   </resource>
   ...
 </resources>
```
注意：这样会导致打包后对应的文件不会保留

---
参考
https://maven.apache.org/plugins/maven-resources-plugin/examples/resource-directory.html
