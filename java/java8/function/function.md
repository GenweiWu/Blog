### Function

```java
Function<Integer, String> function = i -> {
            return "value:" + i;
        };
        List<String> list = Stream.of(1, 2, 3).map(function).collect(Collectors.toList());
        System.out.println(list);  //[value:1, value:2, value:3]
```



### BiFunction

```java
 Map<Integer, String> map = new HashMap<>();
        map.put(1, "A1");

        //function只需要用到key
        Function<Integer, String> function = (key) -> {
            return "B" + key;
        };

        //biFunction要用到key+oldValue
        BiFunction<Integer, String, String> biFunction = (key, oldValue) -> {
            return oldValue + "-" + "C" + key;
        };

        map.computeIfAbsent(2, function);
        map.computeIfPresent(1, biFunction);
        System.out.println(map);  //{1=A1-C1, 2=B2}
```





### BinaryOperator 

```java
//表示key如果相等了，oldValue,newValue选择谁
        BinaryOperator<String> binaryOperator = (i1, i2) -> {
            return i2;
        };
        Map<Integer, String> map = Stream.of(1, 2, 1).collect(Collectors.toMap(i -> i, i -> String.valueOf("[" + i + "]"), binaryOperator));
        System.out.println(map);  //{1=[1], 2=[2]}
```





### UnaryOperator 

> UnaryOperatory用来限制int只能转换成int

```java
        IntUnaryOperator intUnaryOperator = i -> {
            return i + 10;
        };

        IntStream.of(1, 2, 3).map(intUnaryOperator).forEach(System.out::println);
//        11
//        12
//        13
```
