package com.njust.test.learn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock lock =new ReentrantLock();
 * lock.lock();
 * ...
 * lock.unlock();
 */
public class LockDemo
{
    public static void main(String[] args)
    {
        Printer printer = new Printer();
        
        new Thread(() -> {
            while (true)
            {
                printer.print("1111111");
            }
        }).start();
        
        new Thread(() -> {
            while (true)
            {
                printer.print("2222222");
            }
        }).start();
        
    }
    
    static class Printer
    {
        private Lock lock = new ReentrantLock();
        
        public void print(String name)
        {
            lock.lock();
            try
            {
                for (int i = 0; i < name.length(); i++)
                {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
            finally
            {
                lock.unlock();
            }
        }
        
    }
}


