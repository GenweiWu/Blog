package com.njust.test.learn;

/**
 * 实现线程有两种方法
 * 1、方法1是继承Thread类覆盖run方法
 * 2、方法2是new Thread(Runnable)的方式
 */
public class ThreadDemo
{
    public static void main(String[] args)
    {
        //3、这个线程的实际输出对应的是thread，原因可以去看下Thread的run方法的实现
        //
        //        @Override
        //        public void run() {
        //        if (target != null) {
        //            target.run();
        //        }
        //    }
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("runnable: " + Thread.currentThread().getName());
            }
        })
        {
            @Override
            public void run()
            {
                System.out.println("thread: " + Thread.currentThread().getName());
            }
        }.start();
        
    }
}
