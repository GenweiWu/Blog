package com.njust.test.juc;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import org.junit.Assert;
import org.junit.Test;

/**
* DemoData的字段value3/value4对于AtomicIntegerFieldUpdaterDemo类是不可见的，因此通过反射是不能直接修改其值的
*/
public class AtomicIntegerFieldUpdaterDemo
{
    
    private AtomicIntegerFieldUpdater<DemoData> getUpdater(String fieldName)
    {
        return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
    }
    
    @Test
    public void test01()
    {
        DemoData data = new DemoData();
        int actual = getUpdater("value1").getAndSet(data, 10);
        Assert.assertEquals(1, actual);
        
        actual = getUpdater("value2").incrementAndGet(data);
        Assert.assertEquals(3, actual);
    }
    
    @Test
    public void testProtected()
    {
        DemoData data = new DemoData();
        try
        {
            int value3 = getUpdater("value3").decrementAndGet(data);
            Assert.fail();
        }
        catch (Exception e)
        {
            System.err.println(e);
            //java.lang.RuntimeException: java.lang.IllegalAccessException: Class com.njust.test.juc.AtomicIntegerFieldUpdaterDemo can not access a protected member of class com.njust.test.juc.DemoData using an instance of com.njust.test.juc.DemoData
        }
        
    }
    
    @Test
    public void testPrivate()
    {
        DemoData data = new DemoData();
        try
        {
            getUpdater("value4").compareAndSet(data, 4, 5);
            Assert.fail();
        }
        catch (Exception e)
        {
            System.err.println(e);
            //java.lang.RuntimeException: java.lang.IllegalAccessException: Class com.njust.test.juc.AtomicIntegerFieldUpdaterDemo can not access a member of class com.njust.test.juc.DemoData with modifiers "private volatile"
        }
        
    }
    
}

class DemoData
{
    public volatile int value1 = 1;
    
    volatile int value2 = 2;
    
    protected volatile int value3 = 3;
    
    private volatile int value4 = 4;
}
