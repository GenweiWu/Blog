package com.njust.learn.annotation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AService.class)
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
    private AnnotationDemo.AnnotationListener annotationListenerReal = new AnnotationDemo.AnnotationListener() {
        @Override
        public void log(String msg) {
            actualLog = msg;
            System.out.println("actualLog=" + actualLog);
        }
    };
    private String actualLog = null;

    @Before
    public void setup() {
        //使用注解要加上这行
        MockitoAnnotations.initMocks(this);

        listenerList.add(annotationListenerMock);
        listenerList.add(annotationListenerReal);
    }

    @Test
    public void helloAnnotation() {
        PowerMockito.doCallRealMethod().when(annotationListenerMock).trigger(anyString());

        annotationDemo.helloAnnotation();

        Mockito.verify(annotationListenerMock).log(anyString());
        Assert.assertNotNull(actualLog);
    }

    @Test
    public void helloAnnotation_specific() throws Exception {
        PowerMockito.doCallRealMethod().when(annotationListenerMock).trigger(anyString());
        PowerMockito.when(aService, "createId", "AService").thenReturn("123");

        annotationDemo.helloAnnotation();

        Mockito.verify(annotationListenerMock).log(anyString());
        Mockito.verify(annotationListenerMock).log("123");

        Assert.assertNotNull(actualLog);
        Assert.assertEquals("123", actualLog);
    }
}
