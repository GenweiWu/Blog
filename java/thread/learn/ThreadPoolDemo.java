package com.njust.test.learn;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 1、四种常见的线程池
 * 2、shutdown方法
 */
public class ThreadPoolDemo
{
    private static final int TASK_COUNT = 10;
    
    public static void main(String[] args)
    {
        //test1();
        test2();
    }
    
    private static void test1()
    {
        //类型1
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);
        
        //类型2
        ExecutorService threadPool = Executors.newCachedThreadPool();
        
        //类型3
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        
        for (int i = 1; i <= TASK_COUNT; i++)
        {
            final int taskNum = i;
            threadPool.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    for (int j = 0; j < 10; j++)
                    {
                        System.out.println(Thread.currentThread().getName() + " loop " + j + " for task:" + taskNum);
                        try
                        {
                            Thread.sleep(100);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    
                }
            });
        }
        
        //如果想要关闭线程池，需要调用shutdown方法
        threadPool.shutdown();
        //shutdown会执行完之前提交的任务才会关闭线程池，而shutdownNow则马上关闭线程池，即使有任务没执行完
        //threadPool.shutdownNow();
    }
    
    private static void test2()
    {
        //类型4
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        
        //延迟指定时间后，一次执行
        scheduledExecutorService.schedule(() -> {
            printLog(Thread.currentThread().getName() + " one-shot booming");
        }, 10, TimeUnit.SECONDS);
        
        //延迟指定时间后，周期执行
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            printLog(Thread.currentThread().getName() + " interval continue booming");
        }, 2, 1, TimeUnit.SECONDS);
        
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();
        printLog("shutdown called");
    }
    
    private static void printLog(String msg)
    {
        System.out.println(Calendar.getInstance().get(Calendar.SECOND) + " " + msg);
    }
    
}
