package com.njust.test.learn;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo
{
    public static void main(String[] args)
    {
        //simple1();
        simple2();
    }
    
    /**
     * 2. 支持周期性执行的定时器
     * <p>
     * 注：程序仅为测试，不会自动结束
     * ------------------------------------------
     * <pre>
     * booming...
     * tick: 55
     * booming...
     * tick: 56
     * booming...
     * tick: 57
     * booming...
     * tick: 58
     * booming...
     * </pre>
     */
    private static void simple2()
    {
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("booming...");
            }
        }, 100, 1000);
        
        while (true)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("tick: " + Calendar.getInstance().get(Calendar.SECOND));
        }
    }
    
    /**
     * 1. 最简单的定时器，延迟多久后，开始执行TimerTask的内容
     * <p>
     * 1.1 注意：执行完后，不会主动退出，除非timer.cancel()
     * ------------------------------------------------------------------
     * <pre>
     * go!49
     * schedule here!
     *
     * Process finished with exit code 0
     * </pre>
     */
    private static void simple1()
    {
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("schedule here!");
            }
        }, 1000);
        System.out.println("go!" + Calendar.getInstance().get(Calendar.SECOND));
        
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        timer.cancel();
        
    }
}
