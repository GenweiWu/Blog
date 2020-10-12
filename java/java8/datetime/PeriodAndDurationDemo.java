package com.njust.test.java8;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

import org.junit.Test;

public class PeriodAndDurationDemo
{
    /**
     * 天数:30
     * 分钟:40
     */
    @Test
    public void test01()
    {
        //1.Period对一个LocalDate
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2020, 11, 11);
        Period period = Period.between(localDate1, localDate2);
        System.out.println("天数:" + period.getDays());
        
        //2.Duration对应LocalTIme
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.of(12, 0);
        Duration duration = Duration.between(localTime1, localTime2);
        System.out.println("分钟:" + duration.toMinutes());
    }
}
