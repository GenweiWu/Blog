package com.njust.test.rawtype;

public class RequestUtil
{
    
    public static <T> MyResponse<T> sayHi(Class<T> clazz)
    {
        System.out.println("Hello " + clazz);
        return new MyResponse<>();
    }
    
    public static <T> MyResponse<T> sayHello()
    {
        return new MyResponse<>();
    }
    
}
