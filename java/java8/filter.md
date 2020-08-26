

> filter会返回新的list
```java
List<Person> beerDrinkers = persons.stream()
    .filter(p -> p.getAge() > 16)
    .collect(Collectors.toList());
```

> removeIf不用返回新的list
```java
beerDrinkers.removeIf(p -> p.getAge() <= 16);
```
