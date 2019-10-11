package com.njust.test.learn;

public class SynchronizeDemo
{
    public static void main(String[] args)
    {
        Printer printer = new Printer();
        
        new Thread(() -> {
            while (true)
            {
                printer.print("1111111");
            }
        }).start();
        
        new Thread(() -> {
            while (true)
            {
                printer.print("2222222");
            }
        }).start();
        
    }
    
}

class Printer
{
    /**
     * 2. synchronized加在非静态方法上，等同于synchronize(this)
     * synchronized加在静态方法上，等同于synchronize(A.class)
     */
    public void print(String name)
    {
        synchronized (this)
        {
            for (int i = 0; i < name.length(); i++)
            {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
    
    /**
     * 1. 为了互斥，synchronized加在本地变量或方法参数上是没用的
     * <p>
     * --------------------
     * <pre>
     *  2222222
     * 22221111111
     * 1111111
     * 1111111
     * 1222
     * 2222222
     *  </pre>
     */
    public void print111(String name)
    {
        synchronized (name)
        {
            for (int i = 0; i < name.length(); i++)
            {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}
