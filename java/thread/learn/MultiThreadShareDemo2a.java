package com.njust.test.learn;

/**
 * 涉及4个线程，2个对j+1操作,2个对j-1操作,各自执行50遍
 * <p>
 * 方法1，通过让多个内部类，共享外部类的数据，来实现共享
 */
public class MultiThreadShareDemo2a
{
    private static final int COUNT = 500;
    
    public static void main(String[] args)
    {
        ShareData1 shareData1 = new ShareData1();
        
        for (int i = 0; i < 2; i++)
        {
            new Thread(() -> {
                for (int k = 0; k < COUNT; k++)
                {
                    shareData1.increase();
                    
                    //这样能看到加减间隔的效果
                    sleep();
                }
            }).start();
        }
        
        for (int i = 0; i < 2; i++)
        {
            new Thread(() -> {
                for (int k = 0; k < COUNT; k++)
                {
                    shareData1.decrease();
                    
                    sleep();
                }
            }).start();
        }
        
    }
    
    private static void sleep()
    {
        try
        {
            Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
}

class ShareData1
{
    private int j = 0;
    
    public synchronized void decrease()
    {
        --j;
        System.out.println(Thread.currentThread().getName() + " -1 = " + j);
    }
    
    public synchronized void increase()
    {
        ++j;
        System.out.println(Thread.currentThread().getName() + " +1 = " + j);
    }
}

