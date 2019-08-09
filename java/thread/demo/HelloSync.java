package com.njust.test.multiple;

public class HelloSync implements Runnable
{
    
    @Override
    public void run()
    {
        this.hello();
    }
    
    private synchronized void hello()
    {
        System.out.println("hello");
        //在同步方法hello中调用另一个同步方法world，如果内置锁不是可重入的，则会阻塞
        this.world();
    }
    
    private synchronized void world()
    {
        System.out.println("world");
    }
    
    public static void main(String[] args)
        throws InterruptedException
    {
        HelloSync helloSync = new HelloSync();
        Thread thread1 = new Thread(helloSync);
        Thread thread2 = new Thread(helloSync);
        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
        System.out.println("finish.");
    }
}

/**
output:
*
hello
world
hello
world
finish.
*
