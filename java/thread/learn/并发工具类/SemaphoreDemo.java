package com.njust.test.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        Semaphore semaphore = new Semaphore(5);
        
        for (int i = 0; i < 10; i++)
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
