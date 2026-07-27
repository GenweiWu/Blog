package com.njust.java8.datetime;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class PeriodAndDurationTest {

    /**
     * Period的精度是"年月日"
     */
    @Nested
    class PeriodTest {

        @Test
        public void testPeriod_create() {
            // 1. 直接指定年月日
            Period p1 = Period.of(1, 2, 15);
            Period p2 = Period.ofYears(3);
            Period p3 = Period.ofMonths(2);
            Period p4 = Period.ofDays(15);
            assertNotNull(p1);
            assertNotNull(p2);
            assertNotNull(p3);
            assertNotNull(p4);

            // 2. 两个日期之间计算-between
            LocalDate start = LocalDate.of(2026, 7, 1);
            LocalDate end = LocalDate.of(2026, 7, 24);
            //between包括start, 不包括end
            Period between = Period.between(start, end);
            final Period expect = Period.of(0, 0, 23);
            assertEquals(expect, between);

            // 3. 两个日期之间计算-until
            Period until = start.until(end);
            assertEquals(expect, until);
        }

        @Test
        public void testPeriod_parse() {
            Period p1 = Period.of(1, 2, 15);
            Period p2 = Period.parse("P1Y2M15D");

            assertEquals(p2, p1);
            assertEquals("P1Y2M15D", p1.toString());
        }

        /**
         * Period.between() 只看年月日差值，不考虑实际天数。
         * <p>
         * 比如 2024-01-25 到 2024-02-28 结果是 P1M3D（1月25→2月28，月份差1，日差3），而不是"34天"
         */
        @Test
        public void testPeriod_actualDays() {
            LocalDate start = LocalDate.of(2024, 1, 25);
            LocalDate end = LocalDate.of(2024, 2, 28);

            int expectDays = 34;

            //1: Period.between().getDays()（它只代表去掉整月后剩下的零头天数）
            Period between = Period.between(start, end);
            assertEquals("P1M3D", between.toString());
            assertNotEquals(expectDays, between.getDays());
            assertEquals(3, between.getDays());

            //2
            long actual = ChronoUnit.DAYS.between(start, end);
            assertEquals(expectDays, actual);
        }
    }


    /**
     * Duration对应的时分秒
     */
    @Nested
    class DurationTest {

        @Test
        public void testDuration_create() {
            //1. 计算两个时间点之间的差距
            LocalDateTime start = LocalDateTime.of(2026, 7, 27, 9, 0, 0);
            LocalDateTime end = LocalDateTime.of(2026, 7, 27, 11, 30, 45);
            Duration duration = Duration.between(start, end);

            //2.直接指定特定时长
            Duration duration1 = Duration.ofSeconds(3);
            assertEquals("PT3S", duration1.toString());

            //3.解析 ISO-8601 标准字符串（PT2H30M 表示 2 小时 30 分钟）
            Duration durationExpect = Duration.parse("PT2H30M45S");
            assertEquals(durationExpect, duration);
        }

        /**
         * to获取的是总的累计值
         */
        @Test
        public void testDuration_to() {
            Duration duration = Duration.parse("PT2H30M45S");

            long minutes = duration.toMinutes();
            assertEquals(2 * 60 + 30, minutes); //(总分钟数)

            long seconds = duration.toSeconds();
            assertEquals((2 * 60 + 30) * 60 + 45, seconds); //(总秒数)

            long seconds2 = duration.getSeconds();
            assertEquals((2 * 60 + 30) * 60 + 45, seconds2); //(总秒数)

            int secondsPart = duration.toSecondsPart();
            assertEquals(45, secondsPart); //这个就不是总秒数了
        }
    }

}
