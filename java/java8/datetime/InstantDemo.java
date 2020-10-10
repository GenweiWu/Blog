package com.njust.test.java8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

public class InstantDemo
{
    /**
     * <pre>
     * 2020-10-10T16:59:38.699
     * 2020-10-10T08:59:38.700Z
     * 2020-10-10T14:59:38.700+06:00
     * 2020-10-10T08:59:38.700Z
     * </pre>
     */
    @Test
    public void test01()
    {
        System.out.println(LocalDateTime.now());
        
        //1.默认是UTC时间的
        Instant instant = Instant.now();
        System.out.println(instant);
        
        OffsetDateTime offsetDateTime1 = instant.atOffset(ZoneOffset.of("+6"));
        System.out.println(offsetDateTime1);
        
        OffsetDateTime offsetDateTime2 = instant.atOffset(ZoneOffset.UTC);
        System.out.println(offsetDateTime2);
    }
}
