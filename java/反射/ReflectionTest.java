package com.njust.test.kemu2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

class ReflectData
{
    public static final int SUCCESS = 0;
    
    public String name = "publicName";
    
    protected String home = "china";
    
    private String hobby = "jump";
    
    public ReflectData()
    {
    }
    
    protected ReflectData(String name)
    {
        this.name = name;
    }
    
    private ReflectData(String name, String home)
    {
        this.name = name;
        this.home = home;
    }
    
    public void publicMethod()
    {
        System.out.println("publicMethod");
    }
    
    protected void protectedMethod()
    {
        System.out.println("protectedMethod");
    }
}

public class ReflectionTest
{
    @Test
    public void testType()
    {
        Object reflectData = new ReflectData();
        
        Class<?> aClass = reflectData.getClass();
        System.out.println(aClass);
        
        //class com.njust.test.kemu2.ReflectData
    }
    
    @Test
    public void testConstructor()
    {
        //1、获取public构造函数
        Constructor<?>[] constructors = ReflectData.class.getConstructors();
        for (Constructor<?> constructor : constructors)
        {
            System.out.println(constructor);
        }
        
        System.out.println("-------------------------->");
        
        //2、获取所有构造函数
        Constructor<?>[] declaredConstructors = ReflectData.class.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors)
        {
            System.out.println(declaredConstructor);
        }
        
        // public com.njust.test.kemu2.ReflectData()
        //-------------------------->
        //public com.njust.test.kemu2.ReflectData()
        //protected com.njust.test.kemu2.ReflectData(java.lang.String)
        //private com.njust.test.kemu2.ReflectData(java.lang.String,java.lang.String)
        
    }
    
    @Test
    public void testMethod()
    {
        //1、返回所有public方法, 包括父类的方法
        Method[] methods = ReflectData.class.getMethods();
        for (Method method : methods)
        {
            System.out.println(method);
        }
        
        System.out.println("-------------->");
        
        //2、返回所有方法(包括public，protected，private)， 不包括父类方法
        Method[] declaredMethods = ReflectData.class.getDeclaredMethods();
        for (Method method : declaredMethods)
        {
            System.out.println(method);
        }
        
        // public void com.njust.test.kemu2.ReflectData.publicMethod()
        //public final void java.lang.Object.wait() throws java.lang.InterruptedException
        //public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
        //public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
        //public boolean java.lang.Object.equals(java.lang.Object)
        //public java.lang.String java.lang.Object.toString()
        //public native int java.lang.Object.hashCode()
        //public final native java.lang.Class java.lang.Object.getClass()
        //public final native void java.lang.Object.notify()
        //public final native void java.lang.Object.notifyAll()
        //-------------->
        //public void com.njust.test.kemu2.ReflectData.publicMethod()
        //protected void com.njust.test.kemu2.ReflectData.protectedMethod()
    }
    
    @Test
    public void testField()
        throws InstantiationException, IllegalAccessException
    {
        ReflectData reflectionTest = ReflectData.class.newInstance();
        
        //1、获取所有public属性
        Field[] fields = ReflectData.class.getFields();
        for (Field field : fields)
        {
            Object o = field.get(reflectionTest);
            System.out.println(field.getName() + "==>" + o);
        }
        
        System.out.println("------------------->");
        
        //2、获取所有属性，但是private的要setAccessible(true)
        Field[] declaredFields = ReflectData.class.getDeclaredFields();
        for (Field field : declaredFields)
        {
            field.setAccessible(true);
            Object o = field.get(reflectionTest);
            System.out.println(field.getName() + "==>" + o);
        }
        
        //    SUCCESS==>0
        //    name==>publicName
        //------------------->
        //    SUCCESS==>0
        //    name==>publicName
        //    home==>china
        //    hobby==>jump
    }
}
