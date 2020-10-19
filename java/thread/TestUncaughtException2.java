package com.njust.test.multiple;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/*
 *对于线程池中的异常处理
 * <p>
 
 */
public class TestUncaughtException2
{
    /**
     * 1.线程池启动的线程中发生运行时异常的话，当通过future.get()方法时，才会得到run方法中的异常
     * <pre>
     * error1:java.lang.ArithmeticException: / by zero
     * error2:java.lang.NullPointerException
     * </pre>
     */
    @Test
    public void test01()
    {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = fixedThreadPool.submit(() -> {
            int a = 1;
            int b = 0;
            return a / b;
        });
        
        Future<Boolean> future2 = fixedThreadPool.submit(() -> {
            Map<String, String> map = new HashMap<>();
            return map.get("111").contains("xxx");
        });
        
        try
        {
            Integer result1 = future1.get();
            System.out.println("result1:" + result1);
        }
        catch (Exception e)
        {
            System.err.println("error1:" + e.getMessage());
        }
        
        try
        {
            Boolean result2 = future2.get();
            System.out.println("result2:" + result2);
        }
        catch (Exception e)
        {
            System.err.println("error2:" + e.getMessage());
        }
        
    }
    
}
