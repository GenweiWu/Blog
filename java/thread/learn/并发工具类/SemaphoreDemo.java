package com.njust.test.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <pre>
 * pool-1-thread-1 已进入: <---
 * pool-1-thread-2 已进入: <---
 * pool-1-thread-3 已进入: <---
 * pool-1-thread-2 已退出: --->
 * 当前可用个数(接近的):1
 * pool-1-thread-5 已进入: <---
 * pool-1-thread-3 已退出: --->
 * pool-1-thread-4 已进入: <---
 * 当前可用个数(接近的):0
 * pool-1-thread-1 已退出: --->
 * 当前可用个数(接近的):1
 * pool-1-thread-5 已退出: --->
 * 当前可用个数(接近的):2
 * pool-1-thread-4 已退出: --->
 * 当前可用个数(接近的):3
 * </pre>
 */
public class SemaphoreDemo
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        Semaphore semaphore = new Semaphore(3);
        
        for (int i = 0; i < 5; i++)
        {
            executorService.execute(() -> {
                
                //获取锁
                try
                {
                    semaphore.acquire();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 已进入: <---");
                
                try
                {
                    Thread.sleep((long)(Math.random() * 1000));
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                
                //释放锁
                semaphore.release();
                System.out.println(
                    Thread.currentThread().getName() + " 已退出: ---> ");
                //因为不在semaphore的acquire和release形成的锁范围内，availablePermits的值可能不对
                System.out.println("当前可用个数(接近的):" + semaphore.availablePermits());
                
            });
        }
        
        executorService.shutdown();
    }
}
