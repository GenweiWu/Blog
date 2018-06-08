package com.njust.test.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * 总结：
 * 可以理解为max是排序后的最右边，min是排序后的最左边
 * 所以升序排序，则max为最大，min为最小；
 * 但是如果是降序排列，min才是最大，max反而是最小
 */
public class MaxTest
{
    /**
     * 如果compare之后是升序，则max和min是预期的结果
     */
    @Test
    public void sortAsc()
    {
        List<Integer> intArray = Arrays.asList(1, 6, 4, 9);
        System.out.println(intArray);

        //(n1, n2) -> n1 - n2
        Comparator<Integer> comparator = Comparator.comparingInt(n -> n);
        //max
        Integer maxResult = intArray.stream().max(comparator).orElse(-1);
        System.out.println("max:" + maxResult);
        //min
        Integer minResult = intArray.stream().min(comparator).orElse(-1);
        System.out.println("min:" + minResult);

        //sort
        intArray.sort(comparator);
        System.out.println(intArray);
    }

    /**
     * 如果compare之后是降序，则max和min不是预期的结果；
     * 可以理解为max是排序后的最右边，min是排序后的最左边
     */
    @Test
    public void sortDesc()
    {
        List<Integer> intArray = Arrays.asList(1, 6, 4, 9);
        System.out.println(intArray);

        //区别：(n1, n2) -> n2 - n1
        Comparator<Integer> comparator = (n1, n2) -> n2 - n1;
        //max
        Integer maxResult = intArray.stream().max(comparator).orElse(-1);
        System.out.println("max:" + maxResult);
        //min
        Integer minResult = intArray.stream().min(comparator).orElse(-1);
        System.out.println("min:" + minResult);

        //sort
        intArray.sort(comparator);
        System.out.println(intArray);
    }
}
