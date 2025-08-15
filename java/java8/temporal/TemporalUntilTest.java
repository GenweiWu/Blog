package com.njust.temporal;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class TemporalUntilTest {

    @Test
    public void test_localDate() {
        LocalDate localDate1 = LocalDate.of(2025, 1, 14);
        LocalDate localDate2 = LocalDate.of(2025, 3, 15);

        //t1:period
        Period period = localDate1.until(localDate2);
        //getDays不是转换为天数，是只取了天的部分
        int periodDays = period.getDays();
        Assert.assertNotEquals(60, periodDays);

        //t2:specific temporalUnit
        long days = localDate1.until(localDate2, ChronoUnit.DAYS);
        Assert.assertEquals(60, days);

        long months = localDate1.until(localDate2, ChronoUnit.MONTHS);
        Assert.assertEquals(2, months);
    }

}
