

```java
Supplier<String> stringSupplier = () -> {
            return "-1";
        };

        String s = null;
        String actual = Optional.ofNullable(s).orElseGet(stringSupplier);
        Assertions.assertEquals("-1", actual);
```

