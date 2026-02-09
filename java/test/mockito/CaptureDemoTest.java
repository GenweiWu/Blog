package com.njust.learn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  //用于注解初始化
class CaptureDemoTest {

    @Spy
    private CaptureDemo captureDemo = new CaptureDemo();

    @DisplayName("capture普通方法")
    @Test
    void capture_sayMsg() {
        //创建capture
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //调用
        captureDemo.hello(666);

        //verify
        verify(captureDemo).sayMsg(captor.capture());

        String actual = captor.getValue();
        assertEquals("hello world666", actual);
    }

    @DisplayName("capture静态方法")
    @Test
    void capture_staticMsg() {

        try (MockedStatic<CaptureDemo> mockedStatic = mockStatic(CaptureDemo.class)) {
            //创建capture
            ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

            //调用
            captureDemo.hello(555);

            //verify
            mockedStatic.verify(() -> CaptureDemo.staticMsg(captor.capture()));

            String actual = captor.getValue();
            assertEquals("static:555", actual);
        }
    }

    @DisplayName("capture多次调用")
    @Test
    void capture_repeat() {
        //创建capture
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        //调用
        captureDemo.hello(3);

        //verify
        verify(captureDemo, times(3)).sayRepeat(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals(3, allValues.size());
        assertEquals("repeat:1", allValues.get(0));
        assertEquals("repeat:2", allValues.get(1));
        assertEquals("repeat:3", allValues.get(2));
    }

    @DisplayName("capture复杂对象")
    @Test
    void capture_obj() {
        //创建capture
        ArgumentCaptor<CaptureDemo.Node> captor = ArgumentCaptor.forClass(CaptureDemo.Node.class);

        //调用
        captureDemo.hello(8);

        //verify
        verify(captureDemo).sayNode(captor.capture());

        CaptureDemo.Node actual = captor.getValue();
        assertNotNull(actual);
        assertEquals(8, actual.getIndex());
        assertEquals("node:" + 8, actual.getName());
    }
}
