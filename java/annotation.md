
## 注解的属性的类型

> 不能是自定义类
```
It's specified by section 9.6.1 of the JLS. The annotation member types must be one of:

- primitive
- String
- an Enum
- another Annotation
- Class
- an array of any of the above
It does seem restrictive, but no doubt there are reasons for it.

Also note that multidimensional arrays (e.g. String[][]) are implicitly forbidden by the above rule.

Arrays of Class are not allowed as described in this answer.
```

### class
```
@interface MyAnnotation {
    Class<? extends MyInterfaceValueSource> value();
}
```
