package com.njust.temporal;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;

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
}
