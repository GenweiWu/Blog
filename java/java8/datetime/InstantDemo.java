package com.njust.test.java8;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.Test;

public class InstantDemo
{
    /**
     * instant总是UTC的
     * <pre>
     * 2020-10-12T02:07:40.332Z
     * 2020-10-12T02:07:40.367Z
     * 2020-03-19T01:50:16.394Z
     * 2020-10-09T09:29:45.440Z
     * </pre>
     */
    @Test
    public void createInstant()
    {
        //1.Instant.now总是相同的
        Instant instant1 = Instant.now();
        System.out.println(instant1);
        
        Instant instant2 = Instant.now(Clock.system(ZoneId.of("+8")));
        System.out.println(instant2);
        
        //可以将OffsetDateTime转换成Instant
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2020-03-19T01:50:16.394Z");
        Instant instant3 = offsetDateTime.toInstant();
        System.out.println(instant3);
        
        OffsetDateTime offsetDateTime1 = OffsetDateTime.parse("2020-10-09T17:29:45.440+08:00");
        Instant instant4 = offsetDateTime1.toInstant();
        System.out.println(instant4);
    }
    
    /**
     * <pre>
     * 2020-10-10T16:59:38.699
     * 2020-10-10T08:59:38.700Z
     * 2020-10-10T14:59:38.700+06:00
     * 2020-10-10T08:59:38.700Z
     * </pre>
     */
    @Test
    public void instant2OffsetDateTime()
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
