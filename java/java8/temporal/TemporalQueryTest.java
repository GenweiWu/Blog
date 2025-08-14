package com.njust.temporal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TemporalQueryTest {

    /**
     * <pre>
     * 使用 {@code TemporalQueries} 中现成的{@code TemporalQuery}
     * </pre>
     */
    @Test
    public void test_simple() {
        LocalDateTime localDateTime = LocalDateTime.of(2025, 6, 18, 13, 30, 59);

        //query LocalDate
        LocalDate actualLocalDate = localDateTime.query(TemporalQueries.localDate());
        Assert.assertEquals(LocalDate.of(2025, 6, 18), actualLocalDate);

        //query LocalTime
        LocalTime actualLocalTime = localDateTime.query(TemporalQueries.localTime());
        Assert.assertEquals(LocalTime.of(13, 30, 59), actualLocalTime);
    }

    /**
     * <pre>
     * 自定义 {@code TemporalQuery}
     * </pre>
     */
    @Test
    public void test_custom() {
        //custom query
        TemporalQuery<Month> monthQuery = Month::from;

        //1.test
        LocalDateTime localDateTime = LocalDateTime.of(2025, 6, 18, 13, 30, 59);
        Month month = localDateTime.query(monthQuery);
        Assert.assertEquals(Month.of(6), month);

        //2.也可以直接用
        Month from = Month.from(localDateTime);
        Assert.assertEquals(Month.of(6), from);

        //3.当然也可以
        Assert.assertEquals(Month.of(6), localDateTime.getMonth());
    }

    /**
     * 实现一个TemporalQuery，用来判断当前是否在工作时间内
     */
    @Test
    public void test_custom_02() {
        TemporalQuery<Boolean> workingHourQuery = temporal -> {
            LocalTime localTime = LocalTime.from(temporal);

            //[9:00 , 17:00)
            return !localTime.isBefore(LocalTime.of(9, 0))
                    && localTime.isBefore(LocalTime.of(17, 0));
        };

        //t1
        LocalDateTime localDateTime = LocalDateTime.of(2025, 8, 14, 16, 5, 0);
        Assert.assertTrue(localDateTime.query(workingHourQuery));

        //t2
        Assert.assertTrue(LocalTime.of(9, 0, 0).query(workingHourQuery));
        Assert.assertFalse(LocalTime.of(8, 59, 59).query(workingHourQuery));

        //t3
        LocalDate localDate = LocalDate.of(2025, 8, 14);
        try {
            localDate.query(workingHourQuery);
            Assert.fail();
        } catch (DateTimeException e) {
            //expect here
        }
    }

    /**
     * 实现一个TemporalQuery，返回当月所有的周一
     */
    @Test
    public void test_custom_03() {
        List<LocalDate> expect = Arrays.asList(
                LocalDate.of(2025, 8, 4),
                LocalDate.of(2025, 8, 11),
                LocalDate.of(2025, 8, 18),
                LocalDate.of(2025, 8, 25));

        //t1
        LocalDate localDate = LocalDate.of(2025, 8, 14);
        List<LocalDate> result = localDate.query(new AllMondayInSameMonthQuery());
        Assert.assertEquals(expect, result);

        //t2
        LocalDateTime localDateTime = LocalDateTime.of(2025, 8, 14, 6, 6, 6);
        result = localDateTime.query(new AllMondayInSameMonthQuery());
        Assert.assertEquals(expect, result);

        //t3: not supported
        LocalTime localTime = LocalTime.of(6, 6, 6);
        result = localTime.query(new AllMondayInSameMonthQuery());
        Assert.assertTrue(result.isEmpty());
    }

    static class AllMondayInSameMonthQuery implements TemporalQuery<List<LocalDate>> {
        @Override
        public List<LocalDate> queryFrom(TemporalAccessor temporal) {
            if (temporal.isSupported(ChronoField.DAY_OF_MONTH)) {
                LocalDate localDate = temporal.query(TemporalQueries.localDate());

                List<LocalDate> all = new ArrayList<>();
                ValueRange range = temporal.range(ChronoField.DAY_OF_MONTH);
                for (long i = range.getMinimum(); i <= range.getMaximum(); i++) {
                    LocalDate temp = localDate.with(ChronoField.DAY_OF_MONTH, i);
                    if (temp.getDayOfWeek() == DayOfWeek.MONDAY) {
                        all.add(temp);
                    }
                }

                return all;
            }

            System.err.println("not supported temporal:" + temporal);
            return Collections.emptyList();
        }
    }
}
