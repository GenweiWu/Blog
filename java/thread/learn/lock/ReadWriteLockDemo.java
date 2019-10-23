package com.njust.test.learn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo
{
    public static void main(String[] args)
    {
        ShareData shareData = new ShareData();
        
        for (int i = 0; i < 3; i++)
        {
            new Thread(() -> {
                while (true)
                {
                    shareData.readData();
                }
            }).start();
        }
        for (int i = 0; i < 3; i++)
        {
            new Thread(() -> {
                while (true)
                {
                    shareData.writeData("" + new Random().nextInt(10000));
                }
            }).start();
        }
        
    }
    
    private static class ShareData
    {
        private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        
        private String data;
        
        private void readData()
        {
            //读lock
            readWriteLock.readLock().lock();
            
            printLog(" ===> try to read");
            try
            {
                Thread.sleep(2000);
                printLog(" <=== end to read:" + this.data);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                //读unlock
                readWriteLock.readLock().unlock();
            }
        }
        
        private void writeData(String data)
        {
            //写lock
            readWriteLock.writeLock().lock();
            
            printLog(" ===> try to write");
            try
            {
                Thread.sleep(2000);
                this.data = data;
                printLog("<=== end to write:" + data);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                //写lock
                readWriteLock.writeLock().unlock();
            }
            
        }
        
        private void printLog(String msg)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            String dateStr = simpleDateFormat.format(new Date());
            System.out.println("[" + dateStr + "] " + Thread.currentThread().getName() + " " + msg);
        }
        
    }
}
