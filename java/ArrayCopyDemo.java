package com.njust.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * System.arraycopy(Object src,  int  srcPos, Object dest, int destPos, int length);
 */
public class ArrayCopyDemo
{
    @Test
    public void test01()
    {
        int[] src = {1, 2, 3, 4, 5};
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, 5);
        
        Assert.assertArrayEquals(src, dest);
    }
    
    @Test
    public void test02()
    {
        int[] src = {1, 2, 3, 4, 5};
        
        int[] dest = new int[3];
        System.arraycopy(src, 0, dest, 0, 3);
        Assert.assertArrayEquals(new int[] {1, 2, 3}, dest);
        
        System.arraycopy(src, 0, dest, 0, 3);
        Assert.assertArrayEquals(new int[] {1, 2, 3}, dest);
        
        System.arraycopy(src, 1, dest, 0, 3);
        Assert.assertArrayEquals(new int[] {2, 3, 4}, dest);
        
        System.arraycopy(src, 2, dest, 0, 3);
        Assert.assertArrayEquals(new int[] {3, 4, 5}, dest);
    }
}
