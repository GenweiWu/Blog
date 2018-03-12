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

- maven的repo复用问题
  从一台电脑上的repo直接拷贝到另一台机器上,但是修改了settting的源地址;发现没法直接用了。  
  （[参考](https://stackoverflow.com/a/31525835)）
```
报错类似：
 [ERROR] Failed to execute goal on project [...]: Could not resolve dependencies for project [...]: 
Cannot access central (https://repo.maven.apache.org/maven2) in offline mode and the artifact com.oracle:ojdbc6:jar:11.2.0.3.0 has not been downloaded from it before.

原因是
You also need to use the same repository <id>s in both locations.
```

- maven中的mirror和repository  
> [Maven：mirror和repository 区别](https://my.oschina.net/sunchp/blog/100634)
