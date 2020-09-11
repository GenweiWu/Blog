package com.njust.test.kemu2;

class Parent
{
    //1.父类静态
    static String name = getName();
    
    static
    {
        System.out.println("父类的静态代码块");
    }
    
    private static String getName()
    {
        System.out.println("父类的静态成员变量");
        return null;
    }
    
    //2.父类非静态
    String country = getCountry();
    
    {
        System.out.println("父类代码块");
    }
    
    private String getCountry()
    {
        System.out.println("父类的成员变量");
        return null;
    }
    
    public Parent()
    {
        System.out.println("父类的构造函数");
    }
}

class Son2 extends Parent
{
    //3.子类静态
    static String name = getName();
    
    static
    {
        System.out.println("子类的静态代码块");
    }
    
    private static String getName()
    {
        System.out.println("子类的静态成员变量");
        return null;
    }
    
    //4.子类非静态
    String country = getCountry();
    
    {
        System.out.println("子类代码块");
    }
    
    private String getCountry()
    {
        System.out.println("子类的成员变量");
        return null;
    }
    
    public Son2()
    {
        super();
        System.out.println("子类的构造函数");
    }
}

/**
 * <pre>
 * 父类的静态成员变量
 * 父类的静态代码块
 * ----------------------
 * 子类的静态成员变量
 * 子类的静态代码块
 * ----------------------
 * 父类的成员变量
 * 父类代码块
 * ----------------------
 * 父类的构造函数
 * ----------------------
 * 子类的成员变量
 * 子类代码块
 * ----------------------
 * 子类的构造函数
 * ----------------------
 * </pre>
 */
public class ClassLoadTest2
{
    public static void main(String[] args)
    {
        new Son2();
    }
}

