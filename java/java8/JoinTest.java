package com.njust.test.java8;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class JoinTest
{
    @Test
    public void test()
    {
        String[] split = "1.2.3.4.5".split("\\.");
        String joinResult = Stream.of(split).collect(Collectors.joining("-"));
        System.out.println(joinResult);
        //1-2-3-4-5
    }
    
    @Test
    public void test2()
    {
        String[] split = {"aa", "bb", "cc"};
        
        //java8之前
        String sum = "";
        for (String s : split)
        {
            sum = sum + s + "-";
        }
        sum = sum.substring(0, sum.length() - 1);
        System.out.println(sum); //aa-bb-cc
        
        //java8
        String sum222 = Stream.of(split).collect(Collectors.joining("-"));
        System.out.println(sum222); //aa-bb-cc
    }
}
