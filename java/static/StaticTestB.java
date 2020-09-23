package com.njust.test.kemu2;

class StaticTestB
{
    public static void main(String[] args)
    {
        System.out.println(Const.NAME);
    }
}

class Const
{
    public static final String NAME = "i am a const";
    
    static
    {
        System.out.println("init Const class");
    }
}
