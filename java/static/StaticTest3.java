package com.njust.test.kemu2;

class StaticTest3
{
    public static void main(String[] args)
    {
        //        init father
        //        34
        //      System.out.println(Father.m);
        //------------------------------------
        
        //        init father
        //        init child
        //        35
        //System.out.println(Child.m);
        //------------------------------------
        
        //        init father
        //        init child
        new Child();
    }
}

class Father
{
    public static int m = 34;
    
    static
    {
        System.out.println("init father");
    }
}

class Child extends Father
{
    //注意这里的顺序
    public static int m = 36;
    
    static
    {
        m = 35;
        System.out.println("init child");
    }
}
