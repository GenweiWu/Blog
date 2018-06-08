java8 新玩法
==
## [max和min](./MaxTest.java)
```java
List<Integer> intArray = Arrays.asList(1, 6, 4, 9);
        System.out.println(intArray);

        //(n1, n2) -> n1 - n2
        Comparator<Integer> comparator = Comparator.comparingInt(n -> n);
        //max
        Integer maxResult = intArray.stream().max(comparator).orElse(-1);
        System.out.println("max:" + maxResult);
``` 

## [joining](./JoinTest.java)
```java
Stream.of(split).collect(Collectors.joining("-"));
```

## [toMap](./ToMapTest.java)
```java
bookList.stream().collect(Collectors.toMap(Book::getName, n -> n));
```

## [findFirst+findAny](./FindTest.java)
```java
Integer target = nums.stream().filter(n -> {
            System.out.println(n);
            return n > 5;
        }).findFirst().orElse(-1);
```        

## [groupBy+partitionBy](./GroupByTest.java)
```
bookList.stream().collect(Collectors.groupingBy(Book::getType));

bookList.stream().collect(Collectors.partitioningBy(b -> b.getPrice() > 25));
```

---
## 参考
- https://howtodoinjava.com/java-8/stream-max-min-examples/
