package com.njust.test.learn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 1.模拟裁判吹口哨，然后所有运动员开始跑；
 * 2.模拟所有运动员跑完后，裁判统计成绩信息
 */
public class CountDownLatchDemo
{
    private static final int RUNNER_COUNT = 5;
    
    public static void main(String[] args)
    {
        
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(RUNNER_COUNT);
        
        for (int i = 0; i < RUNNER_COUNT; i++)
        {
            final int index = i + 1;
            new Thread(() -> {
                try
                {
                    printLog("运动员" + index + "准备出发");
                    startLatch.await();
                    printLog("运动员" + index + "已经出发");
                    
                    Thread.sleep((long)(Math.random() * 10000));
                    printLog("运动员" + index + "已经达到");
                    finishLatch.countDown();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }).start();
        }
        
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        printLog("裁判准备发命令");
        startLatch.countDown();
        printLog("裁判准备已经发出命令,正在等待结果");
        
        try
        {
            finishLatch.await();
            printLog("所有人跑完了,裁判发布结果");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
    }
    
    private static void printLog(String msg)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String dateStr = simpleDateFormat.format(new Date());
        System.out.println("[" + dateStr + "] " + Thread.currentThread().getName() + " " + msg);
    }
}

//output:
//    [19:58:45] Thread-0 运动员1准备出发
//    [19:58:45] Thread-4 运动员5准备出发
//    [19:58:45] Thread-3 运动员4准备出发
//    [19:58:45] Thread-2 运动员3准备出发
//    [19:58:45] Thread-1 运动员2准备出发
//    [19:58:50] main 裁判准备发命令
//    [19:58:50] main 裁判准备已经发出命令,正在等待结果
//    [19:58:50] Thread-4 运动员5已经出发
//    [19:58:50] Thread-0 运动员1已经出发
//    [19:58:50] Thread-2 运动员3已经出发
//    [19:58:50] Thread-1 运动员2已经出发
//    [19:58:50] Thread-3 运动员4已经出发
//    [19:58:52] Thread-2 运动员3已经达到
//    [19:58:52] Thread-3 运动员4已经达到
//    [19:58:54] Thread-1 运动员2已经达到
//    [19:58:59] Thread-0 运动员1已经达到
//    [19:59:00] Thread-4 运动员5已经达到
//    [19:59:00] main 所有人跑完了,裁判发布结果
