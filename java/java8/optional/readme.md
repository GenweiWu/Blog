optional
==

### 一、创建Optional的3种方法

```
1.Optional.empty()
2.Optional.of(T t)，传入null的话会抛空指针
3.Optional.ofNullable，如果传入null会返回Optional.empty
```

### 二、检查值是否存在
```
2.1 isPresent
2.2 ifPresent
```

### 三、可选操作
```
3.1 orElse,如果不存在则返回默认值
3.2 orElseGet，如果不存在则返回计算的默认值
        //区别在于，仅当不存在时才计算默认值(如果默认值很贵的话)
3.3 get  
容易空指针，一般搭配        
        Optional.ofNullable(nullValue).ifPresent(x -> {
            System.out.println("this is:" + x);
        });
```

### 四、常用方法
> 参考 OptionalTest.java
```
filter用来过滤
map用来转换，需要直接返回对象时使用
flatmap也用来转换，需要返回Optional时使用

```

### 五、可以放集合
```java
List<String> list = new ArrayList<>();
        
        Optional<List<String>> optionalList = Optional.of(list);
        System.out.println(optionalList.isPresent());
```
