
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
