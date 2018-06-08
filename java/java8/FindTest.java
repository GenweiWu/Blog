package com.njust.test.java8;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 总结：
 * 1.findFirst肯定会返回第一个匹配
 * 2.findAny大概率也会返回第一个匹配，但是不确定！
 * 3.两者都会在获取结果后，不再继续循环了
 */
public class FindTest
{
    @Test
    public void findFirst()
    {
        List<Integer> nums = Arrays.asList(1, 5, 9, 7, 3);
        Integer target = nums.stream().filter(n -> {
            System.out.println(n);
            return n > 5;
        }).findFirst().orElse(-1);
        System.out.println("target:" + target);
        //        1
        //        5
        //        9
        //        target:9
    }

    @Test
    public void findAny()
    {
        List<Integer> nums = Arrays.asList(1, 5, 9, 7, 3);
        Integer target = nums.stream().filter(n -> {
            System.out.println(n);
            return n > 5;
        }).findAny().orElse(-1);
        System.out.println("target:" + target);
        //        1
        //        5
        //        9
        //        target:9
    }
}
