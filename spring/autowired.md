## autowired



### 支持泛型类注入

> https://blog.csdn.net/Day_and_Night_2017/article/details/115609322



> 接口

```java
public interface GenericValidator<T> { 
 
    public T validate(T object);
 
}
```

> 多个实现类

```java
@Component
public class FooValidator implements GenericValidator<Foo> {
 
    @Override
    public Foo validate(Foo foo) {
        //TODO
    } 
}
```

```java
@Component
public class BarValidator implements GenericValidator<Bar> {
 
    @Override
    public Bar validate(Bar bar) {
 		//TODO:
    }
}
```

> 注入使用

```java
@Service
public class FooServiceImpl implements FooService {
    
    @Autowired
    private GenericValidator<Foo> fooValidator;
 
    @Override
    public void handleFoo(Foo foo) {
        foo = fooValidator.validate(foo); 
    }
 
}
```

