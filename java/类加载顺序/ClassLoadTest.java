package com.njust.test.kemu2;

/**
 * 无父类时，类的加载：
 * 1. 加载顺序：静态成员变量、静态代码块 >  成员变量，代码块 > 构造函数
 * 2. 成员变量和代码块的加载顺序，取决于写的顺序
 */
class Son
{
    //1.静态成员变量、静态代码块
    private static String name111 = getName("name111");
    
    static
    {
        System.out.println("静态代码块");
    }
    
    private static String name222 = getName("name222");
    
    //2.成员变量、代码块
    private String country111 = getCountry("country111");
    
    {
        System.out.println("代码块");
    }
    
    private String country222 = getCountry("country222");
    
    //3.构造函数
    public Son()
    {
        System.out.println("构造函数");
    }
    
    private static String getName(String name)
    {
        System.out.println("静态成员变量:" + name);
        return null;
    }
    
    private String getCountry(String country)
    {
        System.out.println("成员变量:" + country);
        return null;
    }
    
}

/**
 * <pre>
 *
 * 静态成员变量:name111
 * 静态代码块
 * 静态成员变量:name222
 * ----------------------------------
 * 成员变量:country111
 * 代码块
 * 成员变量:country222
 * -----------------------------------
 * 构造函数
 * </pre>
 */
public class ClassLoadTest
{
    public static void main(String[] args)
    {
        new Son();
        
    }
}

