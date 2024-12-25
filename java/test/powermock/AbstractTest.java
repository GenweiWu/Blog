package com.njust.learn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Abstract.class, LocalDateTime.class})
public class AbstractTest {

    private Abstract anAbstract;

    @Before
    public void setUp() {
        anAbstract = PowerMockito.mock(Abstract.class);
    }

    /**
     * mock抽象方法，返回固定值
     */
    @Test
    public void add() {
        PowerMockito.doCallRealMethod().when(anAbstract).helloThere();
        //mock abstract method
        PowerMockito.when(anAbstract.normalMethod(anyString())).thenReturn("xxx");

        Assert.assertEquals("xxx", anAbstract.helloThere());
    }

    @Test
    public void add_returnCalculate() {
        PowerMockito.mockStatic(LocalDateTime.class);

        LocalDateTime localDateTime = PowerMockito.mock(LocalDateTime.class);
        final String expect = "20240311_172700";
        PowerMockito.when(localDateTime.format(any(DateTimeFormatter.class))).thenReturn(expect);
        PowerMockito.when(LocalDateTime.now()).thenReturn(localDateTime);

        //mock
        PowerMockito.doCallRealMethod().when(anAbstract).helloThere();
        //mock abstract method
        PowerMockito.when(anAbstract.normalMethod(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                String msg = invocation.getArgument(0);
                return msg;
            }
        });

        Assert.assertEquals(anAbstract.helloThere(), expect);

    }


}
