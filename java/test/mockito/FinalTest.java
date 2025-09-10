package com.njust.learn;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 模拟final方法
 */
public class FinalTest {


    @Test
    public void add() {
        assertEquals(3, new Final().add(1, 2));
    }

    /**
     * 模拟final方法，有返回值
     */
    @Test
    public void mockAdd() {
        Final mock = mock(Final.class);
        when(mock.add(1, 2)).thenReturn(-1);
        assertEquals(-1, mock.add(1, 2));
    }

    @Test
    public void say() {
        PrintStream outMock = mock(PrintStream.class);
        System.setOut(outMock);

        Final aFinal = new Final();
        aFinal.say(2, 3);

        verify(outMock).println("say:5");
    }

    /**
     * say方法调用add方法，我们直接模拟say方法，让他不要调用add方法
     */
    @Test
    public void say_mockSay() {
        PrintStream outMock = mock(PrintStream.class);
        System.setOut(outMock);

        Final mock = mock(Final.class);
        doNothing().when(mock).say(2, 3);

        //此时不会调用say方法，也不会调用add方法
        verify(outMock, never()).println("say:5");
    }

    /**
     * say方法调用add方法，我们间接模拟add方法
     */
    @Test
    public void say_mockAdd() {
        PrintStream outMock = mock(PrintStream.class);
        System.setOut(outMock);

        Final mock = mock(Final.class);
        doCallRealMethod().when(mock).say(2, 3);
        when(mock.add(2, 3)).thenReturn(-666);

        mock.say(2, 3);

        verify(outMock).println("say:-666");
    }

}
