package com.njust.test.learn;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <pre>
 * Thread-3: 即将到底集合点1，当前已有1人达到
 * Thread-2: 即将到底集合点1，当前已有2人达到
 * Thread-0: 即将到底集合点1，当前已有3人达到
 * Thread-1: 即将到底集合点1，当前已有4人达到
 * Thread-0: 即将到底集合点2，当前已有1人达到
 * Thread-3: 即将到底集合点2，当前已有2人达到
 * Thread-1: 即将到底集合点2，当前已有3人达到
 * Thread-2: 即将到底集合点2，当前已有4人达到
 * Thread-0: 即将到底集合点3，当前已有1人达到
 * Thread-2: 即将到底集合点3，当前已有2人达到
 * Thread-1: 即将到底集合点3，当前已有3人达到
 * Thread-3: 即将到底集合点3，当前已有4人达到
 * </pre>
 */
public class CyclicBarrierDemo
{
    private static final int COUNT = 4;
    
    public static void main(String[] args)
    {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(COUNT);
        
        for (int i = 0; i < COUNT; i++)
        {
            new Thread(() -> {
                try
                {
                    Thread.sleep((long)(Math.random() * 10000));
                    //getNumberWaiting返回当前被阻塞的个数，或者理解成已经到达集合点的人数
                    printLog(String.format(" 即将到底集合点1，当前已有%s人达到", cyclicBarrier.getNumberWaiting() + 1));
                    cyclicBarrier.await();
                    
                    Thread.sleep((long)(Math.random() * 10000));
                    printLog(String.format(" 即将到底集合点2，当前已有%s人达到", cyclicBarrier.getNumberWaiting() + 1));
                    cyclicBarrier.await();
                    
                    Thread.sleep((long)(Math.random() * 10000));
                    printLog(String.format(" 即将到底集合点3，当前已有%s人达到", cyclicBarrier.getNumberWaiting() + 1));
                    cyclicBarrier.await();
                    
                }
                catch (InterruptedException | BrokenBarrierException e)
                {
                    e.printStackTrace();
                }
                
            }).start();
        }
    }
    
    private static void printLog(String msg)
    {
        System.out.println(Thread.currentThread().getName() + ":" + msg);
    }
}
