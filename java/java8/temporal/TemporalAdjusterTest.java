package com.njust.temporal;

import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjusterTest {

    /**
     * 使用现成的
     * <pre> {@code TemporalAdjusters}
     * </pre>
     */
    @Test
    public void test_simple() {
        LocalDate localDate = LocalDate.of(2025, 2, 14);

        //firstDayOfMonth
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        Assert.assertEquals(LocalDate.of(2025, 2, 1), firstDayOfMonth);

        //lastDayOfMonth
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        Assert.assertEquals(LocalDate.of(2025, 2, 28), lastDayOfMonth);

        //firstDayOfYear
        LocalDate firstDayOfYear = localDate.with(TemporalAdjusters.firstDayOfYear());
        Assert.assertEquals(LocalDate.of(2025, 1, 1), firstDayOfYear);

        //lastDayOfYear
        LocalDate lastDayOfYear = localDate.with(TemporalAdjusters.lastDayOfYear());
        Assert.assertEquals(LocalDate.of(2025, 12, 31), lastDayOfYear);

        //firstInMonth
        LocalDate firstInMonth = localDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        Assert.assertEquals(LocalDate.of(2025, 2, 3), firstInMonth);

        //lastInMonth
        LocalDate lastInMonth = localDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
        Assert.assertEquals(LocalDate.of(2025, 2, 24), lastInMonth);
    }

    @Test
    public void test_ofDateAdjuster() {
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(date -> {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            int dayToAdd;
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dayOfWeek == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            } else {
                dayToAdd = 1;
            }
            return date.plusDays(dayToAdd);
        });


        LocalDate expect = LocalDate.of(2025, 2, 17);
        Assert.assertEquals(expect, LocalDate.of(2025, 2, 14).with(nextWorkingDay));
        Assert.assertEquals(expect, LocalDate.of(2025, 2, 15).with(nextWorkingDay));
        Assert.assertEquals(expect, LocalDate.of(2025, 2, 16).with(nextWorkingDay));

        expect = LocalDate.of(2025, 2, 18);
        Assert.assertEquals(expect, LocalDate.of(2025, 2, 17).with(nextWorkingDay));
    }

    @Test
    public void test_customTemporalAdjuster() {
        LocalDate localDate = LocalDate.of(2025, 2, 17);
        LocalDate expect = LocalDate.of(2025, 5, 11);
        Assert.assertEquals(expect, localDate.with(new MothersDay()));

        localDate = LocalDate.of(2022, 2, 17);
        expect = LocalDate.of(2022, 5, 8);
        Assert.assertEquals(expect, localDate.with(new MothersDay()));
    }

    static class MothersDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            Temporal month = temporal.with(ChronoField.MONTH_OF_YEAR, 5).with(ChronoField.DAY_OF_MONTH, 1);
            return month.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                    .with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        }
    }
}
