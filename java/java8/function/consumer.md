### forEach中使用功能consumer

```java
Consumer<Double> consumer1 = x -> {
            System.out.println("consumer1:" + x);
        };
        Stream.of(10d, 20d).forEach(consumer1);
```

### Optional.ifPresent

```java
        Consumer<String> consumer1 = x -> {
            System.out.println("consumer111:" + x);
        };
        Consumer<String> consumer2 = x -> {
            System.out.println("consumer222:" + x);
        };
        Optional<String> optional = Optional.of("hello");
        optional.ifPresent(consumer1.andThen(consumer2));
//        consumer111:hello
//        consumer222:hello
```

