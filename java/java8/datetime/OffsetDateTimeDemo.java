package com.njust.test.java8;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class OffsetDateTimeDemo
{
    /**
     * 创建 OffsetDateTime
     * <p>
     * 中国的下午16:11，对于UTC时间就是早上8:11
     * <pre>
     *  2020-10-10T16:11:38.410+08:00
     *  2020-10-10T08:11:38.411Z
     *  2020-10-10T08:11:38.411Z
     *  2020-10-10T16:11:38.411+07:00
     *  </pre>
     */
    @Test
    public void createOffsetDateTime_01()
    {
        ////1.默认当前时区
        OffsetDateTime now = OffsetDateTime.now();
        System.out.println(now);
        
        //ZoneId.getAvailableZoneIds().forEach(System.out::println);
        
        ////2.指定特定时区
        now = OffsetDateTime.now(ZoneId.of("UTC"));
        System.out.println(now);
        
        ////3.指定特定时区
        now = OffsetDateTime.now(Clock.systemUTC());
        System.out.println(now);
        
        ////4.这里表示用当前的时间，但是时区是别的时区，这个时间就不是当前时间了
        //ZoneOffset.getAvailableZoneIds().forEach(System.out::println);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(+7));
        System.out.println(offsetDateTime);
    }
    
    /**
     * 字符串转换为 OffsetDateTime
     */
    @Test
    public void createOffsetDateTime_02()
    {
        //1.使用默认格式化进行解析
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2020-03-19T01:50:16.394Z");
        System.out.println(offsetDateTime);
        
        OffsetDateTime offsetDateTime1 = OffsetDateTime.parse("2020-10-09T17:29:45.440+08:00");
        System.out.println(offsetDateTime1);
        
        OffsetDateTime offsetDateTime2 =
            OffsetDateTime.parse("2017-07-07T21:36:10.598+05:30", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        System.out.println(offsetDateTime2);
    }
    
    /**
     * get LocalDateTime, LocalDate, LocalTime, ZonedDateTime, Instant and OffsetTime from OffsetDateTime
     *
     * <pre>
     * 2020-10-10T17:01:58.539+08:00
     * -----------
     * 2020-10-10T15:01:58.540+06:00
     * 2020-10-10T15:01:58.540
     * 2020-10-10
     * 15:01:58.540
     * -----------
     * 2020-10-10T15:01:58.540+06:00
     * 2020-10-10T09:01:58.540Z
     * 15:01:58.540+06:00
     * </pre>
     */
    @Test
    public void test02()
    {
        System.out.println(OffsetDateTime.now());
        System.out.println("-----------");
        
        //ZoneId.getAvailableZoneIds().forEach(System.out::println);
        OffsetDateTime offsetDateTime = OffsetDateTime.now(ZoneId.of("+6"));
        System.out.println(offsetDateTime);
        
        //1. => LocalDateTime
        LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();
        System.out.println(localDateTime);
        //2. => LocalDate
        LocalDate localDate = offsetDateTime.toLocalDate();
        System.out.println(localDate);
        //3. => LocalTime
        LocalTime localTime = offsetDateTime.toLocalTime();
        System.out.println(localTime);
        System.out.println("-----------");
        
        //4. => ZonedDateTime
        ZonedDateTime zonedDateTime = offsetDateTime.toZonedDateTime();
        System.out.println(zonedDateTime);
        
        //5. => Instant, 基于1970-01-01T00:00:00Z
        Instant instant = offsetDateTime.toInstant();
        System.out.println(instant);
        
        //6. => OffsetTime
        OffsetTime offsetTime = offsetDateTime.toOffsetTime();
        System.out.println(offsetTime);
    }
    
    /**
     * OffsetDateTime转换时区
     * <pre>
     * 2020-03-19T01:50:16.394Z
     * 2020-03-19T09:50:16.394+08:00
     * 2020-03-19T01:50:16.394+08:00
     * </pre>
     */
    @Test
    public void timezoneConvert()
    {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2020-03-19T01:50:16.394Z");
        System.out.println(offsetDateTime);
        
        //1.保持instant不变，即将时间转换成目标时区的时间(一般用这个)
        OffsetDateTime offsetDateTimeInChina = offsetDateTime.withOffsetSameInstant(ZoneOffset.of("+8"));
        //2.保持localDateTime不变，只改变时区，此时两者已经不表示同一个instant了
        OffsetDateTime offsetDateTimeInChina2 = offsetDateTime.withOffsetSameLocal(ZoneOffset.of("+8"));
        System.out.println(offsetDateTimeInChina);
        System.out.println(offsetDateTimeInChina2);
    }
}
