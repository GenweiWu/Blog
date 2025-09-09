## pom.xml
```pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>MockitoDemo</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>

        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.2</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>4.5.1</version>
            <scope>test</scope>
        </dependency>

        <!-- Mockito inline（支持静态方法mock、final类mock等） -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-inline</artifactId>
            <version>3.7.7</version>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <artifactId>mockito-core</artifactId>
                    <groupId>org.mockito</groupId>
                </exclusion>
            </exclusions>
        </dependency>

    </dependencies>

</project>
```


## 替换powermock中的`Whitebox`
If you are using Spring (the spring-test library specifically), 
you can simply use `ReflectionTestUtils.setField` instead of `Whitebox.setInternalState` 

```pom.xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <scope>test</scope>
</dependency>
```

## FAQ
### 问题1. UnnecessaryStubbingException
```
org.mockito.exceptions.misusing.UnnecessaryStubbingException: 
Unnecessary stubbings detected.
```

> 规避方法：确认不好优化的情况下，才这样配置规避  
```java 
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)  <-- //忽略没用到的模拟方法
```

