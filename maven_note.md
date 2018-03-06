Maven
==

- 离线模式运行 `-o`  
```
mvn -o clean
```

- maven打包资源文件  
  如何把web-fragment.xml放到META-INF目录下（[参考](https://stackoverflow.com/a/17531764)）
```
Create a new source folder with the location src/main/resources, then create your META-INF/services folder in there and drop in your FQCN file. This should copy them into the jar file automatically. So you'll have:

Project
| src
| | main
|   | java
|     | [your source code]
|   | resources
|     | META-INF
|       | services
|         | [your service files]
```