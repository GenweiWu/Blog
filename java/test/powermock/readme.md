

## powemock+mocktio2

> https://github.com/powermock/powermock/wiki/Mockito
>
> 

```pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>PowermockDemo</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>PowermockDemo</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <powermock-version>2.0.5</powermock-version>
    <mockito-version>2.23.4</mockito-version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.2</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>${mockito-version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.powermock</groupId>
      <artifactId>powermock-api-mockito2</artifactId>
      <version>${powermock-version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.powermock</groupId>
      <artifactId>powermock-module-junit4</artifactId>
      <version>${powermock-version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.powermock</groupId>
      <artifactId>powermock-core</artifactId>
      <version>${powermock-version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.powermock</groupId>
      <artifactId>powermock-module-junit4-rule</artifactId>
      <version>${powermock-version}</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```



### 1. PrepareForTest

`@PrepareForTest`在需要模拟静态方法、私有方法、final方法时需要



### 2. when-thenReturn   vs  doReturn-when

- mock总是不会调用真实方法
- spy有区别，doReturn-when不会先调用真实方法，而when-thenReturn会先调用真实方法(虽然最终返回的还是模拟值)



Both approaches behave differently if you use a spied object (annotated with `@Spy`) instead of a mock (annotated with `@Mock`):

- `when(...) thenReturn(...)` **makes a real method call** just before the specified value will be returned. So if the called method throws an Exception you have to deal with it / mock it etc. Of course you still get your result (what you define in `thenReturn(...)`)
- `doReturn(...) when(...)` **does not call the method at all**.



