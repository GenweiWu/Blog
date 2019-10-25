package com.njust.test.learn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目要求：
 * 3个线程依次打印：12 A a 34 B b
 * 即线程1打印12,34,56,...5152
 * 线程2打印A,B,C...Z
 * 线程3打印a,b,c...z
 * 而且要有序进行
 */
public class LockConditionDemo2
{
    public static void main(String[] args)
    {
        PrintManage printManage = new PrintManage();
        new Thread(printManage::print1).start();
        new Thread(printManage::print2).start();
        new Thread(printManage::print3).start();
    }
    
    static class PrintManage
    {
        private Lock lock = new ReentrantLock();
        
        private Condition condition1 = lock.newCondition();
        
        private Condition condition2 = lock.newCondition();
        
        private Condition condition3 = lock.newCondition();
        
        private int turn = 1;
        
        void print1()
        {
            for (int i = 1; i <= 26; i++)
            {
                lock.lock();
                try
                {
                    while (turn != 1)
                    {
                        try
                        {
                            condition1.await();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    
                    System.out.print((2 * i - 1) + "," + 2 * i);
                    turn = 2;
                    condition2.signal();
                    
                }
                finally
                {
                    lock.unlock();
                }
            }
        }
        
        void print2()
        {
            for (char i = 'A'; i <= 'Z'; i++)
            {
                lock.lock();
                try
                {
                    while (turn != 2)
                    {
                        try
                        {
                            condition2.await();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    
                    System.out.print(i);
                    turn = 3;
                    condition3.signal();
                    
                }
                finally
                {
                    lock.unlock();
                }
            }
        }
        
        void print3()
        {
            for (char i = 'a'; i <= 'z'; i++)
            {
                lock.lock();
                try
                {
                    while (turn != 3)
                    {
                        try
                        {
                            condition3.await();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    
                    System.out.println(i);
                    turn = 1;
                    condition1.signal();
                }
                finally
                {
                    lock.unlock();
                }
            }
        }
    }
}
