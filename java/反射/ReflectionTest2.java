package com.njust.test.kemu2;

import java.lang.reflect.Field;

class RTest
{
    public String field1;
    
    String field2;
    
    private String field3;
}

public class ReflectionTest2
{
    public static void main(String[] args)
        throws Exception
    {
        RTest rTest = new RTest();
        
        Field field = RTest.class.getField("field1");
        field.set(rTest, "test1");
        System.out.println(field.get(rTest));
        
        field = RTest.class.getDeclaredField("field2");
        field.set(rTest, "test2");
        System.out.println(field.get(rTest));
        
        field = RTest.class.getDeclaredField("field3");
        //java.lang.IllegalAccessException
        //System.out.println(field.get(rTest));
        //java.lang.IllegalAccessException
        //field.set(rTest, "test3");
    }
}
