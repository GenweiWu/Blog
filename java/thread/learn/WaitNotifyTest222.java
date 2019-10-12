package com.njust.test.learn;

/**
 * 启动三个线程，分别打印A B C，现在写一个程序 循环打印ABCABCABC....
 */
public class WaitNotifyTest222
{
    private static final int count = 10;
    
    public static void main(String[] args)
    {
        PrintManage printManage = new PrintManage();
        
        new Thread(() -> {
            for (int i = 0; i < count; i++)
            {
                printManage.printA();
            }
        }).start();
        
        new Thread(() -> {
            for (int i = 0; i < count; i++)
            {
                printManage.printB();
            }
        }).start();
        
        new Thread(() -> {
            for (int i = 0; i < count; i++)
            {
                printManage.printC();
            }
        }).start();
        
    }
}

/**
 * 1. 使用wait要求先持有改对象的锁，比如搭配synchronized使用
 * 2. 不能用turn去进行wait和notify，因为synchronized(turn)要求turn是final的
 * <pre>
 * Exception in thread "Thread-1" java.lang.IllegalMonitorStateException
 * 	at java.lang.Object.notify(Native Method)
 * 	at com.njust.test.learn.PrintManage.printA(WaitNotifyTest222.java:58)
 * 	at com.njust.test.learn.WaitNotifyTest222.lambda$main$0(WaitNotifyTest222.java:17)
 * 	at java.lang.Thread.run(Thread.java:745)
 * 	</pre>
 */
class PrintManage
{
    private String turn = "A";
    
    public void printA()
    {
        synchronized (this)
        {
            
            while (!turn.equals("A"))
            {
                try
                {
                    //System.out.println("A wait");
                    this.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            
            System.out.println("A");
            turn = "B";
            this.notifyAll();
        }
    }
    
    public void printB()
    {
        synchronized (this)
        {
            while (!turn.equals("B"))
            {
                try
                {
                    //System.out.println("B wait");
                    this.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println("B");
            turn = "C";
            this.notifyAll();
        }
    }
    
    public void printC()
    {
        synchronized (this)
        {
            while (!turn.equals("C"))
            {
                try
                {
                    //System.out.println("C wait");
                    this.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println("C");
            turn = "A";
            this.notifyAll();
        }
    }
}

