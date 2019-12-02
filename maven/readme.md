Maven
==

### 1. 离线模式运行 `-o`  
```
mvn -o clean
```

### 1.a 强制更新 `-U`
```
mvn clean -U
```

### 2. maven打包资源文件  
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

### 3. maven的repo复用问题
  从一台电脑上的repo直接拷贝到另一台机器上,但是修改了settting的源地址;发现没法直接用了。  
  （[参考](https://stackoverflow.com/a/31525835)）
```
报错类似：
 [ERROR] Failed to execute goal on project [...]: Could not resolve dependencies for project [...]: 
Cannot access central (https://repo.maven.apache.org/maven2) in offline mode and the artifact com.oracle:ojdbc6:jar:11.2.0.3.0 has not been downloaded from it before.

原因是
You also need to use the same repository <id>s in both locations.
```

### 4. maven中的mirror和repository  
> [Maven：mirror和repository 区别](https://my.oschina.net/sunchp/blog/100634)

### 5. mvn指定settings运行, [--参考--](https://stackoverflow.com/a/25279325)
```
You can simply use:
mvn --settings YourOwnSettings.xml clean install
or
mvn -s YourOwnSettings.xml clean install

注意:要指定settings文件全路径，要用引号包含
mvn clean compile --settings "D:\dev\maven\conf\settings_aaa.xml"  
```

### 6. maven本地install带源码 [--参考--](https://stackoverflow.com/a/5102640)
```
To download sources for your dependencies:

mvn eclipse:eclipse -DdownloadSources=true
To attach sources to an installation:

mvn source:jar install
```

### 7. 设置maven下载超时时间 [--参考--](https://stackoverflow.com/a/27015320/6182927)
```xml
<server>
  <id>central</id>
  <configuration>
    <httpConfiguration>
      <all>
        <connectionTimeout>120000</connectionTimeout>
        <readTimeout>120000</readTimeout>
      </all>
    </httpConfiguration>
  </configuration>
</server>
```

### 8. Maven中classifier
用于对jar包进行区分。
- https://blog.csdn.net/qiumengchen12/article/details/71688395
- https://blog.csdn.net/lovingprince/article/details/5894459

### 9.mvn deploy
> https://www.jianshu.com/p/2ef1642b769b

#### 第一步：在要上传的模块的pom.xml文件中添加如下代码
> pom.xml
```xml
<distributionManagement>
           <repository>
              <id>releases</id>
              <name>internal releases</name>
              <url>http://192.168.1.221:8081/repository/maven-releases</url>
          </repository>
          <snapshotRepository>
              <id>snapshots</id>
              <name>internal snapshot</name>
              <url>http://192.168.1.221:8081/repository/maven-snapshots</url>
          </snapshotRepository>
  </distributionManagement>
```

#### 第二步：在settings.xml中配置私服用户信息，要与上文的id相符合
```xml
<servers>
      <server>
        <id>releases</id>
        <username>android-jinchuang</username>
        <password>jinchuang</password>
      </server>
      <server>
        <id>snapshots</id>
        <username>android-jinchuang</username>
        <password>jinchuang</password>
      </server>
  </servers>
```




