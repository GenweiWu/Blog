package com.njust.test.learn;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程范围内变量共享：
 * 指的是同一个线程，但涉及多个步骤(经常是不同类的方法)，如何共享数据
 * 典型场景是web应用读取session中的登录信息(可能调用多个方法，但是并没有将session信息作为参数传来传去)
 * ----------------------------------
 * <pre>
 * Thread-0 put data:-1404320850
 * Thread-1 put data:605851871
 * Thread-0 A read data:-1404320850
 * Thread-1 A read data:605851871
 * Thread-0 B read data:-1404320850
 * Thread-1 B read data:605851871
 * </pre>
 */
public class ThreadScopeShareData
{
    //private static int data;
    
    private static Map<Thread, Integer> dataMap = new HashMap<>();
    
    public static void main(String[] args)
    {
        for (int i = 0; i < 2; i++)
        {
            new Thread(() -> {
                int data = new Random().nextInt();
                dataMap.put(Thread.currentThread(), data);
                System.out.println(Thread.currentThread().getName() + " put data:" + data);
                
                new A().get();
                new B().get();
            }).start();
        }
    }
    
    static class A
    {
        public void get()
        {
            Integer data = dataMap.get(Thread.currentThread());
            System.out.println(Thread.currentThread().getName() + " A read data:" + data);
        }
    }
    
    static class B
    {
        public void get()
        {
            Integer data = dataMap.get(Thread.currentThread());
            System.out.println(Thread.currentThread().getName() + " B read data:" + data);
        }
    }
    
}


