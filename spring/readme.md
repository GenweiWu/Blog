
## Spring Bean Scopes
> https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch04s04.html

|Scope|Description|
|--|--|
|singleton|Scopes a single bean definition to a single object instance per Spring IoC container.|	
|prototype|Scopes a single bean definition to any number of object instances.|
|request|Scopes a single bean definition to the lifecycle of a single HTTP request; that is each and every HTTP request will have its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring ApplicationContext.|
|session|Scopes a single bean definition to the lifecycle of a HTTP Session. Only valid in the context of a web-aware Spring ApplicationContext.|
|global session|Scopes a single bean definition to the lifecycle of a global HTTP Session. Typically only valid when used in a portlet context. Only valid in the context of a web-aware Spring ApplicationContext.|

## @Value

> http://www.baeldung.com/spring-value-defaults

Let’s look at the basic syntax for setting a default value for a String property:
```java
@Value("${some.key:my default value}")
private String stringWithDefaultValue;
```
If some.key cannot be resolved, then stringWithDefaultValue will be set to the default value of “my default value”.

Similarly, we can set a zero-length String as the default value:
```java
@Value("${some.key:})"
private String stringWithBlankDefaultValue;
```





















