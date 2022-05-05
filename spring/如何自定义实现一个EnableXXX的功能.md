## 如何自定义实现一个EnableXXX的功能

实现效果：只有当添加注解@EnableXXX时，才加载对应的类

> https://segmentfault.com/a/1190000040585229

### 1. 添加一个EnableXXX注解
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ImportCustomBeanDefinitionRegistrar.class)//结合@Import注解，这里参可参考其他Blog
public @interface EnableCustomBean {
    String name(); //不写default就是必填了
}
```



### 2. 在启动类上加上EnableXXX注解

```java
@SpringBootApplication
@EnableCustomBean
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
```



### 3. 定义一个bean

```java
public class CustomBean {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```



### 4.将CustomBean加入容器

```java
public class ImportCustomBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata im  portingClassMetadata, BeanDefinitionRegistry registry) {
        //获取EnableCustomBean注解的参数
        Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableCustomBean.class.getName());

        assert attributes != null;

        String name = (String)attributes.get("name");
        
        //到这里就很明白了，
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(CustomBean.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        //传入属性
        propertyValues.add("name", name);
        rootBeanDefinition.setPropertyValues(propertyValues);
        registry.registerBeanDefinition(CustomBean.class.getName(), rootBeanDefinition);
    }
}
```

 ### 5.使用

```java
@SpringBootTest
public class ImportBeanDefinitionRegistrarTest {
    
    //只管注入即可，
    @Resource
    CustomBean bean;
//
    @Resource
    ApplicationContext applicationContext;

    @Test
    public void test_register_custom_bean() {
        System.out.println(bean);
    }
}
```

