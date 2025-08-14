package com.njust.temporal;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ValueRange;

public class ValueRangeTest {

    @Test
    public void test_localDate() {
        LocalDate localDate = LocalDate.of(2025, 6, 18);

        Assert.assertEquals(ValueRange.of(1, 30), localDate.range(ChronoField.DAY_OF_MONTH));
        Assert.assertEquals(ValueRange.of(1, 365), localDate.range(ChronoField.DAY_OF_YEAR));
        Assert.assertEquals(ValueRange.of(1, 7), localDate.range(ChronoField.DAY_OF_WEEK));
    }

    @Test
    public void test_dayOfMonth() {
        Assert.assertEquals(ValueRange.of(1, 28), LocalDate.of(2025, 2, 18).range(ChronoField.DAY_OF_MONTH));
        Assert.assertEquals(ValueRange.of(1, 29), LocalDate.of(2024, 2, 18).range(ChronoField.DAY_OF_MONTH));
    }

    @Test
    public void test_localDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2025, 6, 18, 13, 30, 59);

        Assert.assertEquals(ValueRange.of(0, 23), localDateTime.range(ChronoField.HOUR_OF_DAY));
    }
}
