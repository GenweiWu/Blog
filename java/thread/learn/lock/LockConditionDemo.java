package com.njust.test.learn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionDemo
{
    private static final int COUNT = 50;
    
    public static void main(String[] args)
    {
        Manage manage = new Manage();
        
        new Thread(() -> {
            for (int i = 1; i <= COUNT; i++)
            {
                manage.subDo(i);
            }
        }).start();
        
        for (int i = 1; i <= COUNT; i++)
        {
            manage.mainDo(i);
        }
    }
    
    static class Manage
    {
        private static final int SUB_INTERVAL = 10;
        
        private static final int MAIN_INTERVAL = 50;
        
        private boolean subTurn = true;
        
        private Lock lock = new ReentrantLock();
        
        private Condition condition = lock.newCondition();
        
        public void subDo(int index)
        {
            lock.lock();
            
            try
            {
                while (!subTurn)
                {
                    try
                    {
                        //this.wait();
                        condition.await();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                
                for (int i = 1; i <= SUB_INTERVAL; i++)
                {
                    System.out.println(String.format("-> Sub[%s]: %s/%s", index, i, SUB_INTERVAL));
                }
                
                subTurn = false;
                //this.notify();
                condition.signal();
            }
            finally
            {
                lock.unlock();
            }
            
        }
        
        public void mainDo(int index)
        {
            lock.lock();
            
            try
            {
                while (subTurn)
                {
                    try
                    {
                        //this.wait();
                        condition.await();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                
                for (int i = 1; i <= MAIN_INTERVAL; i++)
                {
                    System.out.println(String.format("=====> Main[%s]: %s/%s", index, i, MAIN_INTERVAL));
                }
                
                subTurn = true;
                //this.notify();
                condition.signal();
            }
            finally
            {
                lock.unlock();
            }
            
        }
    }
}


