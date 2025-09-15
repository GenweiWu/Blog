# JDK 8 → JDK 21 新特性完整指南

从 JDK 8 升级到 JDK 21 是一个巨大的飞跃，包含了9年的Java发展成果。以下是所有重要新特性的详细介绍：

## 🎯 快速概览

| JDK 版本   | 发布年份 | 主要特性                         |
| :--------- | :------- | :------------------------------- |
| **JDK 8**  | 2014     | Lambda、Stream API、Optional     |
| **JDK 9**  | 2017     | 模块系统、JShell                 |
| **JDK 10** | 2018     | var局部变量推断                  |
| **JDK 11** | 2018     | HTTP Client、单文件源码运行      |
| **JDK 17** | 2021     | Sealed Classes、Pattern Matching |
| **JDK 21** | 2023     | Virtual Threads、Record Patterns |

## 1. 语言特性增强

### 1.1 Local Variable Type Inference (JDK 10)



```java
// JDK 8
List<String> list = new ArrayList<String>();
Map<String, Integer> map = new HashMap<>();

// JDK 10+ - 使用var
var list = new ArrayList<String>(); // 推断为List<String>
var map = new HashMap<String, Integer>();
var message = "Hello"; // 推断为String
```



### 1.2 Switch Expressions (JDK 14)



```java
// JDK 8
int dayValue;
switch (day) {
    case MONDAY:
    case FRIDAY:
    case SUNDAY:
        dayValue = 6;
        break;
    case TUESDAY:
        dayValue = 7;
        break;
    default:
        dayValue = 0;
}

// JDK 14+ - Switch表达式
int dayValue = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> 6;
    case TUESDAY -> 7;
    default -> 0;
};

// 还可以返回值
String result = switch (status) {
    case 200 -> "OK";
    case 404 -> "Not Found";
    default -> "Unknown";
};
```



### 1.3 Text Blocks (JDK 15)



```java
// JDK 8
String json = "{\n" +
              "  \"name\": \"John\",\n" +
              "  \"age\": 30\n" +
              "}";

// JDK 15+ - 文本块
String json = """
    {
        "name": "John",
        "age": 30
    }
    """;
```



### 1.4 Records (JDK 16)



```java
// JDK 8 - 需要完整POJO
public class Person {
    private String name;
    private int age;
    
    // 构造函数、getters、equals、hashCode、toString...
}

// JDK 16+ - Record
public record Person(String name, int age) { 
    // 自动生成构造函数、getters、equals、hashCode、toString
}

// 使用
Person person = new Person("John", 30);
String name = person.name(); // getter
```



### 1.5 Pattern Matching (JDK 16-21)



```java
// JDK 8
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.length());
}

// JDK 16+ - Pattern Matching for instanceof
if (obj instanceof String s) {
    System.out.println(s.length()); // 直接使用s
}

// JDK 21+ - Pattern Matching for switch
String formatted = switch (obj) {
    case Integer i -> String.format("int %d", i);
    case String s -> String.format("string %s", s);
    case null -> "null";
    default -> obj.toString();
};
```



### 1.6 Sealed Classes (JDK 17)



```java
// JDK 17+ - 密封类
public sealed class Shape permits Circle, Rectangle, Triangle {
    // 基类
}

public final class Circle extends Shape {
    private double radius;
}

public final class Rectangle extends Shape {
    private double width, height;
}

public non-sealed class Triangle extends Shape {
    private double base, height;
}
```



## 2. API 增强

### 2.1 New Collection Methods



```java
// JDK 8
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> filtered = list.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

// JDK 9+ - 新的集合工厂方法
List<Integer> list = List.of(1, 2, 3, 4, 5);
Set<String> set = Set.of("a", "b", "c");
Map<String, Integer> map = Map.of("a", 1, "b", 2);

// JDK 10+ - copyOf
List<Integer> copy = List.copyOf(list);

// JDK 11+ - toArray的改进
String[] array = list.toArray(String[]::new);
```



### 2.2 Stream API 增强



```java
// JDK 9+ - takeWhile/dropWhile
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 4, 3, 2, 1);
List<Integer> firstThree = numbers.stream()
    .takeWhile(n -> n < 4) // [1, 2, 3]
    .collect(Collectors.toList());

List<Integer> afterFour = numbers.stream()
    .dropWhile(n -> n < 4) // [4, 5, 4, 3, 2, 1]
    .collect(Collectors.toList());

// JDK 16+ - mapMulti
List<Number> numbers = List.of(1, 2.0, 3L);
List<Integer> integers = numbers.stream()
    .mapMulti((number, consumer) -> {
        if (number instanceof Integer i) {
            consumer.accept(i);
        }
    })
    .collect(Collectors.toList());
```



### 2.3 Optional 增强


```java
// JDK 9+ - ifPresentOrElse
optional.ifPresentOrElse(
    value -> System.out.println(value),
    () -> System.out.println("Not present")
);

// JDK 9+ - or
Optional<String> result = optional.or(() -> Optional.of("default"));

// JDK 11+ - isEmpty
if (optional.isEmpty()) {
    System.out.println("No value");
}
```



### 2.4 HTTP Client (JDK 11+)


```java
// JDK 8 - 需要HttpURLConnection或第三方库
// JDK 11+ - 内置HTTP Client
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .GET()
    .build();

HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());

System.out.println(response.statusCode());
System.out.println(response.body());
```



## 3. 性能特性

### 3.1 Virtual Threads (JDK 21+)

```java
// JDK 8 - 平台线程，昂贵
ExecutorService executor = Executors.newFixedThreadPool(200);

// JDK 21+ - 虚拟线程，轻量级
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 10_000; i++) {
        executor.submit(() -> {
            Thread.sleep(Duration.ofSeconds(1));
            return i;
        });
    }
}
```



### 3.2 ZGC & Shenandoah GC


```java
// 启动参数示例
// JDK 11+ - ZGC（低延迟）
java -XX:+UseZGC -Xmx4g MyApp

// JDK 12+ - Shenandoah GC（低暂停时间）
java -XX:+UseShenandoahGC -Xmx4g MyApp
```



## 4. 工具和开发体验

### 4.1 JShell (JDK 9+)


```bash
# REPL环境
$ jshell
|  Welcome to JShell -- Version 21
|  For an introduction type: /help intro

jshell> int x = 10
x ==> 10

jshell> String greeting = "Hello, World!"
greeting ==> "Hello, World!"

jshell> /list
   1 : int x = 10;
   2 : String greeting = "Hello, World!";
```



### 4.2 Single-File Source Code (JDK 11+)


```bash
# 直接运行单个Java文件
# Hello.java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello from single file!");
    }
}

# 运行
java Hello.java  # 无需先编译
```



## 5. 模块系统 (JDK 9+)

### 5.1 module-info.java


```java
// 模块声明
module com.example.myapp {
    requires java.base;           // 隐式依赖
    requires java.sql;            // 显式依赖
    requires org.apache.commons.lang3;
    
    exports com.example.api;      // 导出包
    opens com.example.model;      // 反射访问
}
```



## 6. 安全性增强

### 6.1 新的加密算法


```java
// JDK 11+ - 更现代的加密支持
KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519");
KeyPair kp = kpg.generateKeyPair();
```



## 7. 迁移建议

### 7.1 逐步升级策略

1. **先升级到JDK 11**：LTS版本，稳定性好
2. **然后到JDK 17**：另一个LTS，更多新特性
3. **最后到JDK 21**：最新LTS，虚拟线程等

### 7.2 兼容性检查


```bash
# 使用jdeprscan检查废弃API
jdeprscan --release 21 my-app.jar

# 使用jdeps检查模块依赖
jdeps --jdk-internals my-app.jar
```



## 8. 开始使用新特性

### 示例：现代化代码


```java
public class ModernJavaExample {
    
    public static void main(String[] args) {
        // Records + Pattern Matching
        var people = List.of(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 35)
        );
        
        var result = people.stream()
            .filter(p -> p.age() > 25)
            .map(p -> STR."\{p.name()} is \{p.age()} years old")
            .collect(Collectors.joining("\n"));
        
        System.out.println(result);
    }
    
    record Person(String name, int age) {}
}
```



## 总结

从 JDK 8 到 JDK 21 的升级带来了：

- ✅ **更好的性能**：虚拟线程、新GC
- ✅ **更简洁的代码**：Record、var、模式匹配
- ✅ **更强的API**：新的集合方法、HTTP Client
- ✅ **更好的开发体验**：JShell、单文件运行
- ✅ **更强的安全性**：现代加密算法

建议逐步升级，充分利用LTS版本的稳定性，同时享受现代Java的开发体验。
