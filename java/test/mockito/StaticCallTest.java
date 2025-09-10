package com.njust.learn;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


public class StaticCallTest {

    @Test
    public void staticCallAdd() {
        //范围外，正常结果
        assertEquals("7", StaticCall.staticCallAdd("3", "4"));

        try (MockedStatic<Static> mockStatic = mockStatic(Static.class)) {
            mockStatic.when(() -> Static.add("3", "4")).thenReturn("-1");

            //模拟结果
            assertEquals("-1", StaticCall.staticCallAdd("3", "4"));
        }

        //范围外，正常结果
        assertEquals("7", StaticCall.staticCallAdd("3", "4"));
    }

    @Test
    public void callAdd() {
        StaticCall staticCall = new StaticCall();
        assertEquals("3", staticCall.callAdd("1", "2"));

        try (MockedStatic<Static> mockStatic = mockStatic(Static.class)) {
            mockStatic.when(() -> Static.add("1", "2")).thenReturn("-1");

            //模拟结果
            assertEquals("-1", staticCall.callAdd("1", "2"));
        }

        assertEquals("3", staticCall.callAdd("1", "2"));
    }

    @Test
    public void getDate() {
        StaticCall staticCall = new StaticCall();
        assertEquals(LocalDate.now().plusDays(1), staticCall.getDate());

        //expect要放在mock之前
        LocalDate expect = LocalDate.of(2022, 1, 2);
        //now也要放在mock之前!!!
        LocalDate mockToday = LocalDate.of(2022, 1, 1);

        try (MockedStatic<LocalDate> mockStatic = mockStatic(LocalDate.class)) {
            mockStatic.when(LocalDate::now).thenReturn(mockToday);
            //✅ 这里需要模拟plusDays，不然返回的null
            when(mockToday.plusDays(1)).thenCallRealMethod();
            assertEquals(expect, staticCall.getDate());
        }

        assertEquals(LocalDate.now().plusDays(1), staticCall.getDate());
    }
}
