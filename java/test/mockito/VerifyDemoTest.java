package com.njust.learn;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class VerifyDemoTest {

    /**
     * 普通方法，非static，非private
     */
    @Test
    public void verify_noPrivateNoStatic() {
        VerifyDemo verifyDemo = mock(VerifyDemo.class);
        doCallRealMethod().when(verifyDemo).work(3, 5);

        verifyDemo.work(3, 5);

        verify(verifyDemo, times(3)).noPrivateNoStatic(and(geq(3), leq(5)));
        verify(verifyDemo, times(1)).noPrivateNoStatic(3);
        verify(verifyDemo, times(1)).noPrivateNoStatic(4);
        verify(verifyDemo, times(1)).noPrivateNoStatic(5);
    }

    /**
     * 不支持验证私有方法
     */
    @Test
    public void verify_private() {
        //verifyPrivate(verifyDemo, times(1)).invoke("privateMethod", 1);

        //只能间接验证
        PrintStream outMock = mock(PrintStream.class);
        System.setOut(outMock);

        VerifyDemo verifyDemo = new VerifyDemo();
        verifyDemo.work(1, 2);

        verify(outMock, times(10)).println(anyString());
        verify(outMock).println("privateMethod:1");
        verify(outMock).println("privateMethod:2");
    }


    /**
     * 验证static方法
     */
    @Test
    public void verify_staticMethod() {
        VerifyDemo verifyDemo = new VerifyDemo();

        try (MockedStatic<VerifyDemo> mockedVerifyDemo = mockStatic(VerifyDemo.class)) {
            //mock
            mockedVerifyDemo.when(() -> VerifyDemo.staticMethod(anyInt())).thenCallRealMethod();

            //test
            verifyDemo.work(1, 1);

            //verify
            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod(1));
            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod222(1), times(2));
        }
    }

    @Test
    public void verify_staticMethod_manyTimes() {
        VerifyDemo verifyDemo = new VerifyDemo();

        try (MockedStatic<VerifyDemo> mockedVerifyDemo = mockStatic(VerifyDemo.class)) {
            //mock
            mockedVerifyDemo.when(() -> VerifyDemo.staticMethod(anyInt())).thenCallRealMethod();

            //test
            verifyDemo.work(1, 3);

            //verify
            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod(anyInt()), times(3));
            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod(1));
            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod(2));
            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod(3));

            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod222(anyInt()), times(6));
            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod222(1), times(2));
            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod222(2), times(2));
            mockedVerifyDemo.verify(() -> VerifyDemo.staticMethod222(3), times(2));
        }
    }

    /**
     * 测试构造函数
     * <p>
     * {@code verifyNew}没有mockito的同样版本，但是可以用{@code constructed}尝试代替
     */
    @Test
    public void verify_newNode() {
        //1
        VerifyDemo verifyDemo = new VerifyDemo();
        assertNull(verifyDemo.newNode(false));
        assertNotNull(verifyDemo.newNode(true));

        //2
        try (MockedConstruction<VerifyDemo.VerifyNode> mockedConstruction = mockConstruction(VerifyDemo.VerifyNode.class, (mock, context) -> {
            when(mock.getNodeName()).thenReturn("hello");
        })) {
            VerifyDemo verifyDemo2 = new VerifyDemo();
            //-1
            assertNull(verifyDemo2.newNode(false));
            //verifyNew替换
            assertEquals(0, mockedConstruction.constructed().size());

            //-2
            assertEquals("hello", verifyDemo2.newNode(true));
            //verifyNew替换
            assertEquals(1, mockedConstruction.constructed().size());
        }

        //3
        assertNull(verifyDemo.newNode(false));
        assertNotNull(verifyDemo.newNode(true));
    }

    /**
     * 不要去模拟jdk的基础类，会导致mockito本身报错
     * <p>
     * java.lang.NoClassDefFoundError: com/njust/learn/VerifyDemo
     * </p>
     */
    @Test
    public void verify_newFile() {

        assertThrows(Error.class, () -> {
            try (MockedConstruction<File> mockedConstruction = mockConstruction(File.class, (mock, context) -> {
                when(mock.getName()).thenReturn("hello");
            })) {
                VerifyDemo verifyDemo2 = new VerifyDemo();
                assertNull(verifyDemo2.newFile(false));
                //verifyNew替换
                assertEquals(0, mockedConstruction.constructed().size());

                assertEquals("hello", verifyDemo2.newFile(true));
                //verifyNew替换
                assertEquals(1, mockedConstruction.constructed().size());
            }
        });
    }
}
