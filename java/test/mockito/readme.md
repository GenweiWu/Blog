## 注意
❌ 不要用`mockStatic`去模拟jdk中的静态方法  
❌ 不要用`mockConstruction`去模拟jdk中的类，比如`java.io.File`


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

        <!--替换powermock中的Whitebox-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>5.3.15</version>
            <scope>test</scope>
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

### 问题2：批量mockStatic处理
```java
//使用 @BeforeEach 和 @AfterEach
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StaticMockWithBeforeEachTest {
    
    private MockedStatic<StaticUtils> mockedStatic;
    
    @BeforeEach
    void setUp() {
        mockedStatic = mockStatic(StaticUtils.class);
        
        // 设置公共的模拟行为
        mockedStatic.when(() -> StaticUtils.getMessage(anyString()))
                   .thenReturn("Default Response");
        
        mockedStatic.when(() -> StaticUtils.calculate(eq(10), anyInt()))
                   .thenReturn(100);
    }
    
    @AfterEach
    void tearDown() {
        if (mockedStatic != null) {
            mockedStatic.close();
        }
    }
    
    @Test
    void testMethod1() {
        // 可以覆盖默认行为
        mockedStatic.when(() -> StaticUtils.getMessage("admin"))
                   .thenReturn("Welcome Admin");
        
        assertEquals("Welcome Admin", StaticUtils.getMessage("admin"));
        assertEquals("Default Response", StaticUtils.getMessage("user"));
    }
    
    @Test
    void testMethod2() {
        assertEquals(100, StaticUtils.calculate(10, 5));
        assertEquals("Default Response", StaticUtils.getMessage("test"));
    }
}

class StaticUtils {
    public static String getMessage(String name) {
        return "Hello, " + name;
    }
    
    public static int calculate(int a, int b) {
        return a + b;
    }
}
```

