package com.njust.test.learn;

/**
 * 多个线程分享共享的对象或数据，如果做的事情是一样的，则可以直接通过同一个Runnable来共享数据
 * -------------------
 * 卖票系统，100张票，通过10个线程一起卖票
 */
public class MultiThreadShareDemo
{
    public static void main(String[] args)
    {
        Seller seller = new Seller();
        for (int i = 0; i < 10; i++)
        {
            new Thread(seller).start();
        }
        
    }
    
}

class Seller implements Runnable
{
    private int count = 100;
    
    @Override
    public void run()
    {
        while (true)
        {
            //对count的读写都必须放在同步代码块中
            synchronized (this)
            {
                if (count == 0)
                {
                    break;
                }
                
                --count;
                System.out.println(Thread.currentThread().getName() + " sell 1 ticket, left " + count);
            }
            
            //sleep一下，否则一个线程就自己卖光了
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
}
