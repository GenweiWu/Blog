package com.njust.test.kemu2;

import java.lang.reflect.Field;

/**
 * 1.public,protected的可以直接访问到
 * 2.private默认不能访问；但是可以设置setAccessible(true)之后访问
 */
class RTest
{
    public String field1 = "default1";
    
    String field2 = "default2";
    
    private String field3 = "default3";
}

/**
 * console:
 * <pre>
 * test111
 * test222
 * default3
 * test333
 * </pre>
 */
public class ReflectionTest2
{
    public static void main(String[] args)
        throws Exception
    {
        RTest rTest = new RTest();
        
        Field field1 = RTest.class.getDeclaredField("field1");
        field1.set(rTest, "test111");
        System.out.println(field1.get(rTest));
        
        Field field2 = RTest.class.getDeclaredField("field2");
        field2.set(rTest, "test222");
        System.out.println(field2.get(rTest));
        
        Field field3 = RTest.class.getDeclaredField("field3");
        //不可以直接访问
        //java.lang.IllegalAccessException
        //System.out.println(field.get(rTest));
        //java.lang.IllegalAccessException
        //field.set(rTest, "test333");
        
        //setAccessible后可以访问
        field3.setAccessible(true);
        System.out.println(field3.get(rTest));
        field3.set(rTest, "test333");
        System.out.println(field3.get(rTest));
    }
}
