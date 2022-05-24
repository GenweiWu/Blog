### Predicate

```java
@Test
    public void test01() {
        Predicate<Integer> predicate = x -> {
            return x >= 2;
        };

        Stream.of(1, 2, 3).filter(predicate).forEach(System.out::print); //23
    }
```

