package com.njust.test.learn;

/**
 * interrupt可以打断wait状态
 * -------------
 * <pre>
 *  main trigger interrupt
 * interrupt here!!!
 *  </pre>
 */
public class WaitNotifyDemo333
{
    
    public static void main(String[] args)
    {
        
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock)
            {
                try
                {
                    lock.wait();
                    System.out.println("wait end");
                }
                catch (InterruptedException e)
                {
                    System.out.println("interrupt here!!!");
                }
            }
        });
        thread.start();
        
        thread.interrupt();
        System.out.println("main trigger interrupt");
    }
    
}
