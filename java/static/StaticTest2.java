package com.njust.test.kemu2;

class StaticTest2
{
    public static void main(String[] args)
    {
        //        init father
        //        34
        //       System.out.println(Father.m);
        //------------------------------------
        
        //        init father
        //        init child
        //        36
        //System.out.println(Child.m);
        //------------------------------------
        
        //        init father
        //        init child
        // new Child();
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
    
    static
    {
        m = 35;
        System.out.println("init child");
    }
    
    public static int m = 36;
}
