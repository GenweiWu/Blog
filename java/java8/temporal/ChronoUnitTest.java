package com.njust.temporal;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChronoUnitTest {

    @Test
    public void test() {
        LocalDate localDate1 = LocalDate.of(2025, 1, 14);
        LocalDate localDate2 = LocalDate.of(2025, 3, 15);

        long days = ChronoUnit.DAYS.between(localDate1, localDate2);
        Assert.assertEquals(60, days);

        long months = ChronoUnit.MONTHS.between(localDate1, localDate2);
        Assert.assertEquals(2, months);
    }
}
