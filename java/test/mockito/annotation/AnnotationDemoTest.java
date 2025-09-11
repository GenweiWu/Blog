package com.njust.learn.annotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  //用于注解初始化
public class AnnotationDemoTest {

    /**
     * InjectMocks的对象是实际对象，即没有被mock
     */
    @InjectMocks
    private AnnotationDemo annotationDemo;

    @Spy
    private AService aService = new AService();

    /**
     * spy必须先创建一个实际对象
     */
    @Spy
    private List<AnnotationDemo.AnnotationListener> listenerList = new ArrayList<>();

    /**
     * 模拟的对象
     * <p>
     * verify要求的对象必须是mock或者spy的
     */
    @Mock
    private AnnotationDemo.AnnotationListener annotationListenerMock;

    /**
     * 实际的对象
     */
    private final AnnotationDemo.AnnotationListener annotationListenerReal = new AnnotationDemo.AnnotationListener() {
        @Override
        public void log(String msg) {
            actualLog = msg;
            System.out.println("actualLog=" + actualLog);
        }
    };
    private String actualLog = null;

    @BeforeEach
    public void setup() {
        listenerList.add(annotationListenerMock);
        listenerList.add(annotationListenerReal);
    }

    @Test
    public void helloAnnotation() {
        //--mock
        doCallRealMethod().when(annotationListenerMock).trigger(anyString());

        //--test
        annotationDemo.helloAnnotation();

        //--verify
        verify(annotationListenerMock).log(anyString());
        assertNotNull(actualLog);
    }

    @Test
    public void helloAnnotation_specific() throws Exception {
        //--mock
        doCallRealMethod().when(annotationListenerMock).trigger(anyString());
        //❌ 无法模拟私有方法
        //when(aService, "createId", "AService").thenReturn("123");
        //✅ 改为模拟public方法
        when(aService.hello()).thenReturn("123");

        //--test
        annotationDemo.helloAnnotation();

        //--verify
        verify(annotationListenerMock).log(anyString());
        verify(annotationListenerMock).log("123");

        assertNotNull(actualLog);
        assertEquals("123", actualLog);
    }
}
