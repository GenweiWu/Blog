package com.njust.learn;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StaticTest {

    /**
     * 不mock时
     */
    @Test
    public void add() {
        assertEquals("3", Static.add("1", "2"));
    }

    /**
     * 1. mock某个参数，则该参数的返回就是模拟的return
     * 2. 其他没模拟的，也不会走真实方法了，而是对象的默认值(比如int=0,String=null)
     */
    @Test
    public void mockAdd() {
        //❌模拟范围外，不被模拟
        assertEquals("7", Static.add("3", "4"));

        try (MockedStatic<Static> mockStatic = mockStatic(Static.class)) {
            mockStatic.when(() -> Static.add("3", "4")).thenReturn("-1");

            //✅
            assertEquals("-1", Static.add("3", "4"));

            //this is test for real: 没模拟的变成null了
            assertNull(Static.add("14", "13"));
        }

        //❌模拟范围外，不被模拟
        assertEquals("27", Static.add("14", "13"));
    }

    /**
     * 测试没有返回值的静态方法
     */
    @Test
    public void hello() {
        //mock
        try (MockedStatic<Static> mockStatic = mockStatic(Static.class)) {
            mockStatic.when(Static::hello).thenCallRealMethod();

            assertThrows(RuntimeException.class, Static::hello);
        }
    }

    @Test
    public void mockHello() {
        assertThrows(RuntimeException.class, Static::hello);

        //mock
        try (MockedStatic<Static> ignored = mockStatic(Static.class)) {
            //没有thenDoNothing方法，不过mock的类默认行为就是doNothing()
            //doNothing is a default behaviour of a void method called on a mock.
            //mockStatic.when(Static::hello).thenDoNothing();
            Static.hello();
        }

        assertThrows(RuntimeException.class, Static::hello);
    }

    @Test
    public void helloWithParameter() {
        //mock
        PrintStream outMock = Mockito.mock(PrintStream.class);
        System.setOut(outMock);

        //test
        Static.helloWithParameter("aaa", "bbb");

        //verify
        verify(outMock).printf("a:%s, b:%s", "aaa", "bbb");
    }

    @Test
    public void mockHelloWithParameter() {
        PrintStream outMock = mock(PrintStream.class);
        System.setOut(outMock);

        //mock-doThing
        try (MockedStatic<Static> ignored = mockStatic(Static.class)) {
            //doNoThing

            Static.helloWithParameter("ccc", "ddd");
            verify(outMock, never()).printf("a:%s, b:%s", "ccc", "ddd");
        }

        //mock-call
        try (MockedStatic<Static> mockStatic = mockStatic(Static.class)) {
            mockStatic.when(() -> Static.helloWithParameter("ccc", "ddd")).thenCallRealMethod();

            Static.helloWithParameter("ccc", "ddd");
            verify(outMock).printf("a:%s, b:%s", "ccc", "ddd");
        }
        clearInvocations(outMock);  //不清理的话，就是累计，则后面的次数则为2

        //mock-call
        Static.helloWithParameter("ccc", "ddd");
        verify(outMock).printf("a:%s, b:%s", "ccc", "ddd");
    }
}
