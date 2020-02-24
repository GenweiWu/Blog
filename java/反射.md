
```java
package com.njust.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest
{
    public static final int SUCCESS = 0;
    
    public String name = "publicName";
    
    protected String home = "china";
    
    private String hobby = "jump";
    
    private ReflectionTest()
    {
    }
    
    public ReflectionTest(String name)
    {
        this.name = name;
    }
    
    public static void main(String[] args)
        throws IllegalAccessException, InstantiationException
    {
        //        testField();
        
        //        testMethod();
        
        testConstructor();
    }
    
    private static void testConstructor()
    {
        //5.获取public构造函数
        Constructor<?>[] constructors = ReflectionTest.class.getConstructors();
        for (Constructor<?> constructor : constructors)
        {
            System.out.println(constructor);
        }
        
        System.out.println("-------------------------->");
        
        //6.获取所有构造函数
        Constructor<?>[] declaredConstructors = ReflectionTest.class.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors)
        {
            System.out.println(declaredConstructor);
        }
        
    }
    
    private static void testMethod()
        throws IllegalAccessException, InstantiationException
    {
        //3.返回所有public方法
        Method[] methods = ReflectionTest.class.getMethods();
        for (Method method : methods)
        {
            System.out.println(method);
        }
        
        //4.返回所有方法(包括public，protected，private)
        Method[] declaredMethods = ReflectionTest.class.getDeclaredMethods();
        for (Method method : declaredMethods)
        {
            System.out.println(method);
        }
        
    }
    
    //    SUCCESS==>0
    //    name==>publicName
    //------------------->
    //    SUCCESS==>0
    //    name==>publicName
    //    home==>china
    //    hobby==>jump
    private static void testField()
        throws InstantiationException, IllegalAccessException
    {
        ReflectionTest reflectionTest = ReflectionTest.class.newInstance();
        
        //1.获取所有public属性
        Field[] fields = ReflectionTest.class.getFields();
        for (Field field : fields)
        {
            Object o = field.get(reflectionTest);
            System.out.println(field.getName() + "==>" + o);
        }
        
        System.out.println("------------------->");
        
        //2.获取所有属性
        Field[] declaredFields = ReflectionTest.class.getDeclaredFields();
        for (Field field : declaredFields)
        {
            Object o = field.get(reflectionTest);
            System.out.println(field.getName() + "==>" + o);
        }
    }
}

```
