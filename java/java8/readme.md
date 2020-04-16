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

## [flatMap](./flatMapTest.java)
```java
List<String> list1 = Arrays.asList("1", "2", "3");
List<String> list2 = Arrays.asList("a", "b", "c");

List<Object> collect = listList.stream().flatMap(l -> l.stream()).collect(Collectors.toList());
System.out.println(collect);  //[1, 2, 3, a, b, c]
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

## computeIfAbsent
> before
```java
Map<Integer, List<Person>> myHashMap = new HashMap();

for (Person person : persons) {
   int age = person.getAge();
   List<Person> personsOfSameAge = myHashMap.get(age);
   if (personsOfSameAge != null) {
       personsOfSameAge.add(person);
   } else {
       personsOfSameAge = new ArrayList();
       personsOfSameAge.add(person);
       myHashMap.put(age, personsOfSameAge);
   }
}
```

> after
```java
Map<Integer, List<Person>> myHashMap = new HashMap();

for (Person person : persons) {
   myHashMap.computeIfAbsent(age,age->new ArrayList<Person>()).add(person);
}
```


---
## 参考
- https://howtodoinjava.com/java-8/stream-max-min-examples/
