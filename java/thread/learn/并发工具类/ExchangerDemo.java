package com.njust.test.learn;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * <pre>
 * [17:20:53] Thread-1 准备开始交换,内容:aaa
 * [17:20:53] Thread-0 准备开始交换,内容:111
 * [17:20:53] Thread-0 交换得到的内容:aaa
 * [17:20:53] Thread-1 交换得到的内容:111
 * [17:20:55] Thread-0 准备开始交换,内容:222
 * [17:20:56] Thread-1 准备开始交换,内容:bbb
 * [17:20:56] Thread-1 交换得到的内容:222
 * [17:20:56] Thread-0 交换得到的内容:bbb
 * [17:20:59] Thread-1 准备开始交换,内容:ccc
 * [17:21:01] Thread-0 准备开始交换,内容:333
 * [17:21:01] Thread-0 交换得到的内容:ccc
 * [17:21:01] Thread-1 交换得到的内容:333
 * </pre>
 */
public class ExchangerDemo
{
    public static void main(String[] args)
    {
        Exchanger<String> stringExchanger = new Exchanger<>();
        
        new Thread(() -> {
            List<String> dataArray = Arrays.asList("111", "222", "333");
            dataArray.forEach(data -> {
                String exchange = null;
                try
                {
                    Thread.sleep((long)(Math.random() * 10000));
                    printLog("准备开始交换,内容:" + data);
                    exchange = stringExchanger.exchange(data);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                printLog("交换得到的内容:" + exchange);
            });
            
        }).start();
        new Thread(() -> {
            List<String> dataArray = Arrays.asList("aaa", "bbb", "ccc");
            dataArray.forEach(data -> {
                String exchange = null;
                try
                {
                    Thread.sleep((long)(Math.random() * 5000));
                    printLog("准备开始交换,内容:" + data);
                    exchange = stringExchanger.exchange(data);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                printLog("交换得到的内容:" + exchange);
            });
        }).start();
    }
    
    private static void printLog(String msg)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String dateStr = simpleDateFormat.format(new Date());
        System.out.println("[" + dateStr + "] " + Thread.currentThread().getName() + " " + msg);
    }
}
