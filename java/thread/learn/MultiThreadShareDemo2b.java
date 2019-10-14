package com.njust.test.learn;

/**
 * 涉及4个线程，2个对j+1操作,2个对j-1操作,各自执行50遍
 * ----
 * 方法2，通过将共享数据，传递给多个Runnable来实现
 */
public class MultiThreadShareDemo2b
{
    public static void main(String[] args)
    {
        ShareData2 shareData2 = new ShareData2();
        
        for (int i = 0; i < 2; i++)
        {
            new Thread(new Increase(shareData2)).start();
        }
        for (int i = 0; i < 2; i++)
        {
            new Thread(new Decrease(shareData2)).start();
        }
        
    }
}

class Increase implements Runnable
{
    private final ShareData2 shareData2;
    
    public Increase(ShareData2 shareData2)
    {
        this.shareData2 = shareData2;
    }
    
    @Override
    public void run()
    {
        for (int i = 0; i < 50; i++)
        {
            shareData2.increase();
            
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
    }
}

class Decrease implements Runnable
{
    private final ShareData2 shareData2;
    
    public Decrease(ShareData2 shareData2)
    {
        this.shareData2 = shareData2;
    }
    
    @Override
    public void run()
    {
        for (int i = 0; i < 50; i++)
        {
            shareData2.decrease();
            
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class ShareData2
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
