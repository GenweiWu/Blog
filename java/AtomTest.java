package com.njust.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

/**
 * 利用AtomicInteger生成文件名称，发现出现覆盖的问题，参见#test03
 */
public class AtomTest
{
    private AtomicInteger count = new AtomicInteger();

    /**
     * 1、使用同一个AtomicInteger，而不是每次new一个出来，不会重复
     */
    @Test
    public void test01()
    {
        int num1 = count.getAndIncrement();
        int num2 = count.getAndIncrement();
        Assert.assertNotEquals(num1, num2);
    }

    /**
     * 2、每次new一个AtomicInteger出来，如果初始因子一样，结果就是一样的
     */
    @Test
    public void test02()
    {
        int num1 = getIndex();
        int num2 = getIndex();
        Assert.assertEquals(num1, num2);
    }

    private int getIndex()
    {
        AtomicInteger counter = new AtomicInteger();
        return counter.getAndIncrement();
    }

    /**
     * 3、每次new一个AtomicInteger出来，如果初始因子看似不一样但是还是一样呢，那结果就是还是一样的
     */
    @Test
    public void test03()
    {
        int num1 = getIndex222();
        int num2 = getIndex222();
        //调用快的时候，就是一样的
        Assert.assertEquals(num1, num2);
    }

    private int getIndex222()
    {
        AtomicInteger counter = new AtomicInteger((int)(System.currentTimeMillis() / 1000L));
        return counter.getAndIncrement();
    }
}
