package com.njust.test.java8;

import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTest {


    /**
     * 获取本周第一个，最后一天
     * <br>
     * 即获取本周周一和周日
     * <p>
     * 此处样例为：[20230911,20230917]
     */
    @Test
    public void testWeek() {
        LocalDate today = LocalDate.of(2023, Month.SEPTEMBER, 14);
        testWeekRange(today);

        //today就是第一天
        today = LocalDate.of(2023, Month.SEPTEMBER, 11);
        testWeekRange(today);

        //today就是最后一天
        today = LocalDate.of(2023, Month.SEPTEMBER, 17);
        testWeekRange(today);
    }

    private static void testWeekRange(LocalDate today) {
        LocalDate firstDayOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDayOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        Assert.assertEquals(LocalDate.of(2023, Month.SEPTEMBER, 11), firstDayOfWeek);
        Assert.assertEquals(LocalDate.of(2023, Month.SEPTEMBER, 17), lastDayOfWeek);
    }

    /**
     * 获取本月的第一天，最后一天
     * <p>
     * 此处样例为：[20230901,20230930]
     */
    @Test
    public void testMonth() {
        LocalDate today = LocalDate.of(2023, Month.SEPTEMBER, 14);
        testMonthRange(today);

        //本月第一天
        today = LocalDate.of(2023, Month.SEPTEMBER, 14);
        testMonthRange(today);

        //本月最后一天
        today = LocalDate.of(2023, Month.SEPTEMBER, 14);
        testMonthRange(today);
    }

    private static void testMonthRange(LocalDate today) {
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        Assert.assertEquals(LocalDate.of(2023, Month.SEPTEMBER, 1), firstDayOfMonth);
        Assert.assertEquals(LocalDate.of(2023, Month.SEPTEMBER, 30), lastDayOfMonth);
    }

    /**
     * 获取当前日期的前7天(不包括当天)
     * <p>
     * 样例为: 20230914的前7天是[20230907,20230913]
     */
    @Test
    public void testRange() {
        LocalDate today = LocalDate.parse("2023-09-14");

        //[-7,-1]
        LocalDate startDate = today.minusDays(7);
        LocalDate endDate = today.minusDays(1);
        Assert.assertEquals(LocalDate.parse("2023-09-07"), startDate);
        Assert.assertEquals(LocalDate.parse("2023-09-13"), endDate);
    }
}
