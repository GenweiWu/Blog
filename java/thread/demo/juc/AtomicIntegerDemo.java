package com.njust.test.juc;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

public class AtomicIntegerDemo
{
    @Test
    public void test01()
    {
        AtomicInteger atomicInteger = new AtomicInteger(4);
        Assert.assertEquals(4, atomicInteger.get());
        
        Assert.assertEquals(12, atomicInteger.addAndGet(8));
        
        Assert.assertEquals(12, atomicInteger.getAndAdd(6));
        Assert.assertEquals(18, atomicInteger.get());
    }
}
