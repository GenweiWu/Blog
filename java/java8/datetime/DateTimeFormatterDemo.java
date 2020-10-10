package com.njust.test.java8;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DateTimeFormatterDemo
{
    /**
     * <pre>
     * 2020-10-10T17:40:50.907+08:00
     * 2020-10-10 17:40:50
     * 2020-10-10 17:40:50.907
     * 2020-10-10 17:40:50.907 +0800
     * 2020-10-10TT17:40:50.907 +0800
     * </pre>
     */
    @Test
    public void test01()
    {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        
        System.out.println(offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        System.out.println(offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS Z")));
        System.out.println(offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'TT'HH:mm:ss.SSS Z")));
        
    }
}
