Spring AOP 代理
==
## 一、两种代理方式

### 1. JDK动态代理：
其代理对象必须是某个接口的实现，它是通过在运行期间创建一个接口的实现类来完成对目标对象的代理。

### 2. CGLIB代理：
实现原理类似于JDK动态代理，只是它在运行期间生成的代理对象是针对目标类扩展的子类。CGLIB是高效的代码生成包，底层是依靠ASM（开源的java字节码编辑类库）操作字节码实现的，性能比JDK强。

#### - spring强制使用cglib代理
```xml
<aop:aspectj-autoproxy proxy-target-class="true"/>
```

#### - 要使用cglib代理，则该类或方法不能声明称final的。

## 二、自动创建代理
参考：https://blog.csdn.net/yangshangwei/article/details/77547287  
```
Spring-AOP 自动创建代理之BeanNameAutoProxyCreator
Spring-AOP 自动创建代理之DefaultAdvisorAutoProxyCreator
Spring-AOP 自动创建代理之AnnotationAwareAspectJAutoProxyCreator
```

---
### 参考
- http://www.cnblogs.com/binyue/p/4519652.html 
- https://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch08s06.html 
- http://www.cnblogs.com/hustyangli/archive/2008/09/01/1281319.html 
