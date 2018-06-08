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
}
