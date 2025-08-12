package com.njust.learn;

import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;

import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StaticCall.class, Static.class, LocalDate.class}) //因为这个类有静态方法
public class StaticCallTest {

    @Test
    public void staticCallAdd() {
        PowerMockito.mockStatic(Static.class);
        when(Static.add("3", "4")).thenReturn("-1");

        String actual = StaticCall.staticCallAdd("3", "4");
        Assert.assertEquals("-1", actual);
    }

    @Test
    public void callAdd() {
        StaticCall staticCall = new StaticCall();
        String actual = staticCall.callAdd("1", "2");
        Assert.assertEquals("3", actual);

        //mock
        PowerMockito.mockStatic(Static.class);
        when(Static.add("1", "2")).thenReturn("-1");
        actual = staticCall.callAdd("1", "2");
        Assert.assertEquals("-1", actual);
    }

    @Test
    public void getDate() {
        StaticCall staticCall = new StaticCall();
        LocalDate actual = staticCall.getDate();
        Assert.assertEquals(LocalDate.now().plusDays(1), actual);

        //expect要放在mock之前
        LocalDate expect = LocalDate.of(2022, 1, 2);

        //mock
        //now也要放在mock之前!!!
        LocalDate now = LocalDate.of(2022, 1, 1);

        PowerMockito.mockStatic(LocalDate.class);
        PowerMockito.when(LocalDate.now()).thenReturn(now);
        actual = staticCall.getDate();
        Assert.assertEquals(expect, actual);
    }
}
