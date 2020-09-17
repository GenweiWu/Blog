package com.njust.test.multiple;

/*
 *使用setUncaughtExceptionHandler方法来捕获线程中未try-catch的异常
 */
public class TestUncaughtException
{
    public static void main(String[] args)
    {
        Thread thread = new Thread(
            () -> {
                int a = 1;
                int b = 0;
                System.out.println(a / b);
            });
        
        //1.在start之前设置
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                System.out.println("uncaughtException:" + e);
            }
        });
        
        thread.start();
        
        System.out.println("finish");
        
    }
    
}
