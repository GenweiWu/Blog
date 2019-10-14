package com.njust.test.learn;

/**
 * 1. synchronized必须锁object，int是不行的
 * 2. synchronized使用变化的Integer也不行
 */
public class SynchronizeDemo2
{
    public static void main(String[] args)
    {
        //Integer修改值后其实已经变了
        int num = 300;
        System.out.println("identityHashCode-->" + System.identityHashCode(num));
        
        num++;
        System.out.println("identityHashCode-->" + System.identityHashCode(num));
        
        //测试,最后结果不是1000说明有问题
        Test test = new Test();
        for (int i = 0; i < 50; i++)
        {
            new Thread(() -> {
                for (int j = 0; j < 20; j++)
                {
                    test.increase();
                }
            }).start();
        }
    }
}

class Test
{
    private Integer num = 0;
    
    public void increase()
    {
        synchronized (num)
        {
            num++;
            System.out.println(Thread.currentThread().getName() + " ==> num= " + num);
        }
    }
}
