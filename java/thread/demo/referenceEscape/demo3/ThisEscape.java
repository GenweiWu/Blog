package com.njust.test.multiple.EscapeTest3;

import java.lang.reflect.Field;

/**
 * 关注ThisEscape的构造方法，正是在构造过程中(构造包括1+2)，但是1就已经将EventListener被暴露给eventSource了，
 * 同时由于内部类会有一个指向外部类的this$0的引用，就间接导致了ThisEscape也被发布了
 * <p>
 * 这里的问题是，ThisEscape被发布时，num=999还没有被赋值(即构造过程还在继续) ，导致读取的num值是错误的
 * -------------------------------------
 * iWantNum=-1
 * num=999
 */
public class ThisEscape
{
    
    private int num = -1;
    
    public ThisEscape(EventSource eventSource)
    {
        //1、eventListener已经被发布了，由于内部类会有一个指向封装实例的引用，间接导致ThisEscape也被发布
        eventSource.registerListener(new EventListener()
        {
            @Override
            public void onEvent()
            {
            }
        });
        
        //2、而此时构造函数还没有结束
        this.num = 999;
    }
    
    public static void main(String[] args)
    {
        ThisEscape thisEscape = new ThisEscape(new EventSource()
        {
            @Override
            public void registerListener(EventListener eventListener)
            {
                try
                {
                    //3、这里可以通过"内部类eventListener"操作其"外部类ThisEscape"
                    //eventListener.this$0.num;
                    Field this$0 = eventListener.getClass().getDeclaredField("this$0");
                    ThisEscape wantedEscape = (ThisEscape)this$0.get(eventListener);
                    System.out.println("iWantNum=" + wantedEscape.num);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
            }
        });
        
        System.out.println("num=" + thisEscape.num);
        
    }
}
