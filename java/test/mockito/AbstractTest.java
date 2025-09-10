package com.njust.learn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  //用于注解初始化
public class AbstractTest {

    @Mock
    private Abstract anAbstract;

    /**
     * mock抽象方法，返回固定值
     */
    @Test
    public void add() {
        doCallRealMethod().when(anAbstract).helloThere();
        //mock abstract method
        when(anAbstract.normalMethod(anyString())).thenReturn("xxx");

        assertEquals("xxx", anAbstract.helloThere());
    }

    @Test
    public void add_returnCalculate() {
        mockStatic(LocalDateTime.class);

        LocalDateTime localDateTime = mock(LocalDateTime.class);
        final String expect = "20250910_102244";
        when(localDateTime.format(any(DateTimeFormatter.class))).thenReturn(expect);
        when(LocalDateTime.now()).thenReturn(localDateTime);

        //mock
        doCallRealMethod().when(anAbstract).helloThere();
        //mock abstract method
        when(anAbstract.normalMethod(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                String msg = invocation.getArgument(0);
                System.out.println("msg:" + msg);
                return msg;
            }
        });

        assertEquals(expect, anAbstract.helloThere());

    }


}
