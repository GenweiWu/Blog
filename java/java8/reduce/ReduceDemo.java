package com.njust.test.java8;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * reduce主要有两种方法，
 * <ul>
 *     <li>
 *      1.不带初始值
 *      Optional<T> reduce(BinaryOperator<T> accumulator)
 *     </li>
 *      <li>
 *     2.带有初始值
 *     T reduce(T identity, BinaryOperator<T> accumulator);
 *     </li>
 * </ul>
 */
public class ReduceDemo
{
    @Test
    public void test01_a()
    {
        //reduce用于合并流的元素，并产生单个值
        int[] nums = {4, 5, 6};
        int result = Arrays.stream(nums).reduce((n1, n2) -> {
            System.out.printf("n1:%s, n2:%s%n", n1, n2);
            return n1 + n2;
        }).orElse(-1);
        Assert.assertEquals(15, result);
    }
    
    @Test
    public void test01_b()
    {
        //只有一个时，也能得出结果
        int[] nums = {6};
        int result = Arrays.stream(nums).reduce((n1, n2) -> {
            System.out.printf("n1:%s, n2:%s%n", n1, n2);
            return n1 + n2;
        }).orElse(-1);
        Assert.assertEquals(6, result);
    }
    
    @Test
    public void test01_c()
    {
        //0个时，返回orElse的参数
        int[] nums = {};
        int result = Arrays.stream(nums).reduce((n1, n2) -> {
            System.out.printf("n1:%s, n2:%s%n", n1, n2);
            return n1 + n2;
        }).orElse(-1);
        Assert.assertEquals(-1, result);
    }
    
    @Test
    public void test02_a()
    {
        int[] nums = {4, 5, 6};
        int result = Arrays.stream(nums).reduce(0, (n1, n2) -> {
            System.out.printf("n1:%s, n2:%s%n", n1, n2);
            return n1 + n2;
        });
        Assert.assertEquals(15, result);
    }
    
    @Test
    public void test02_b()
    {
        int[] nums = {6};
        int result = Arrays.stream(nums).reduce(0, (n1, n2) -> {
            System.out.printf("n1:%s, n2:%s%n", n1, n2);
            return n1 + n2;
        });
        Assert.assertEquals(6, result);
    }
    
    @Test
    public void test02_c()
    {
        //0个时，返回初始值
        int[] nums = {};
        int result = Arrays.stream(nums).reduce(0, (n1, n2) -> {
            System.out.printf("n1:%s, n2:%s%n", n1, n2);
            return n1 + n2;
        });
        Assert.assertEquals(0, result);
    }
    
}
