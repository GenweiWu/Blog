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
 * read1:test1
 * read2:test2
 * read3:default3
 * read3:test3
 */
public class ReflectionTest2
{
    public static void main(String[] args)
        throws Exception
    {
        RTest rTest = new RTest();
        
        Field field = RTest.class.getField("field1");
        field.set(rTest, "test1");
        System.out.println("read1:" + field.get(rTest));
        
        field = RTest.class.getDeclaredField("field2");
        field.set(rTest, "test2");
        System.out.println("read2:" + field.get(rTest));
        
        field = RTest.class.getDeclaredField("field3");
        //不可以直接访问
        //java.lang.IllegalAccessException
        //System.out.println(field.get(rTest));
        //java.lang.IllegalAccessException
        //field.set(rTest, "test3");
        
        //setAccessible后可以访问
        field.setAccessible(true);
        System.out.println("read3:" + field.get(rTest));
        field.set(rTest, "test3");
        System.out.println("read3:" + field.get(rTest));
    }
}
