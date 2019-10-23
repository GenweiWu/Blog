package com.njust.test.learn;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockCacheDemo
{
    private Map<String, Object> cacheMap = new HashMap<>();
    
    public static void main(String[] args)
    {
        LockCacheDemo lockCacheDemo = new LockCacheDemo();
        
        String[] array = {"11", "22", "11", "44", "11"};
        List<String> list = Arrays.asList(array);
        for (String s : list)
        {
            final String key = "key." + s;
            new Thread(() -> {
                while (true)
                {
                    lockCacheDemo.loadData(key);
                    //printLog(String.format("read data:%s with key:%s", key, data));
                }
            }).start();
        }
    }
    
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    /**
     * 获取缓存数据
     * 1.如果是第一次进来，要从db加载
     * 2.如果非首次进来，就是读取的缓存
     */
    private Object loadData(String key)
    {
        readWriteLock.readLock().lock();
        
        Object data = null;
        try
        {
            printLog("<--- try to loadData with key:" + key);
            sleep(1000);
            data = cacheMap.get(key);
            
            if (data == null)
            {
                try
                {
                    //1.double check:因为可能两个读者同时进来了，一个先获得写锁，另一个后获得写锁，可能重新readFromDB
                    data = cacheMap.get(key);
                    
                    //2.要获取写锁，必须先释放读锁
                    readWriteLock.readLock().unlock();
                    readWriteLock.writeLock().lock();
                    
                    if (data == null)
                    {
                        data = readFromDB(key); //模拟从数据库加载
                        cacheMap.put(key, data);
                    }
                    /*else
                    {
                        printLog(".......double check save your time, with key" + key);
                    }*/
                }
                finally
                {
                    //拥有写锁要获取读锁，可以先获取读锁(会发生降级)
                    readWriteLock.readLock().lock();
                    readWriteLock.writeLock().unlock();
                }
            }
        }
        finally
        {
            readWriteLock.readLock().unlock();
        }
        
        printLog("---> end loadData:" + data + " with key:" + key);
        return data;
    }
    
    private Object readFromDB(String key)
    {
        String data = "readFromDB." + key;
        sleep(2000);
        printLog("=====> readFromDB with key" + key);
        return data;
    }
    
    private void sleep(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    private void printLog(String msg)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String dateStr = simpleDateFormat.format(new Date());
        System.out.println("[" + dateStr + "] " + Thread.currentThread().getName() + " " + msg);
    }
    
}
