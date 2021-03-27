package com.njust.test;

import org.junit.Assert;
import org.junit.Test;

public class FinallyTest
{
    /**
     * 1. finally中赋值没用
     */
    @Test
    public void test01()
    {
        int expect = 30;
        int actual = doTest01();
        Assert.assertEquals(expect, actual);
    }
    
    private int doTest01()
    {
        int num = 10;
        try
        {
            System.out.println(num / 0);
            //这里执行不到
            num = 20;
        }
        catch (Exception e)
        {
            num = 30;
            return num;
            //这里实际的语句是return 30,但是要去执行finally中的代码
        }
        finally
        {
            num = 40;
        }
        return num;
    }
    
    /**
     * finally中return则总是return
     */
    @Test
    public void test02()
    {
        int expect = 40;
        int actual = doTest02();
        Assert.assertEquals(expect, actual);
    }
    
    private int doTest02()
    {
        int num = 10;
        try
        {
            System.out.println(num / 0);
            //这里执行不到
            num = 20;
        }
        catch (Exception e)
        {
            num = 30;
            return num;
            //这里实际的语句是return 30,但是要去执行finally中的代码
        }
        finally
        {
            num = 40;
            return num;
            //这里重新生成返回路径，由于只能有一个return，所以这里直接返回40了
        }
    }
    
}
