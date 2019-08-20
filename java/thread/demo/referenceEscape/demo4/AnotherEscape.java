package com.njust.test.multiple.EscapeTest4;

/**
 * 关注AnotherEscape的构造函数，在构造函数中"启动"thread线程，则run方法会可以马上操作AnotherEscape，
 * 而此时AnotherEscape可能还在构造函数中,所以导致iWantNum的值不对
 * <p>
 * 可以
 * ------------------------------------------------------------------------------------------
 * iWantNum=-1
 * num=999
 */
public class AnotherEscape
{
    private Thread thread;
    
    private int num = -1;
    
    public AnotherEscape()
    {
        thread = new Thread()
        {
            @Override
            public void run()
            {
                //2、start线程导致run方法运行，这里可以读取AnotherEscape的变量
                //而此时AnotherEscape还没初始化完成，所以可能出错
                System.out.println("iWantNum=" + num);
            }
        };
        //可以在构造函数中定义线程，但不要在构造函数中启动它
        thread.start();
        
        //yield or sleep
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //1、构造函数还没结束
        this.num = 999;
    }
    
    public static void main(String[] args)
    {
        AnotherEscape anotherEscape = new AnotherEscape();
        System.out.println("num=" + anotherEscape.num);
    }
}
