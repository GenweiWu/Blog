package com.njust.test.multiple.EscapeTest1;

/**
 * 以下结果概率性出现，出现bad说明程序出问题了
 * -------------------
 * num=999
 * ok
 * -------------------
 * num=0
 * bad...
 * -------------------
 */
public class Publisher
{
    public static volatile Publisher published;
    
    int num;
    
    Publisher(int number)
    {
        published = this;
        
        //yield or sleep a while,注意这里是为了加大失误的概率
        Thread.yield();
        // Initialization
        this.num = number;
        // ...
    }
    
    public static void main(String[] args)
    {
        new Thread(() -> {
            new Publisher(999);
        }).start();
        
        while (true)
        {
            
            if (Publisher.published != null)
            {
                System.out.println("num=" + Publisher.published.num);
                if (Publisher.published.num == 999)
                {
                    System.out.println("ok");
                }
                else
                {
                    System.out.println("bad...");
                }
                System.exit(0);
            }
        }
        
        //
    }
}




