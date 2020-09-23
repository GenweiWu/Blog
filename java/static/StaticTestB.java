package com.njust.test.kemu2;

class StaticTestB
{
    public static void main(String[] args)
    {
        //i am a const
        System.out.println(Const.NAME);
    }
}

class Const
{
    static
    {
        System.out.println("init Const class");
    }
    
    public static final String NAME = "i am a const";
}
