package com.njust.learn.annotation;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学习如何给自动注入写单元测试
 */
//@Service
public class AnnotationDemo {

    @Resource
    private AService aService;

    @Resource
    private List<AnnotationListener> listenerList;

    public void helloAnnotation() {
        String msg = aService.hello();

        for (AnnotationListener annotationListener : listenerList) {
            annotationListener.trigger(msg);
        }

    }

    public interface AnnotationListener {
        public default void trigger(String msg) {
            log(msg);
        }

        public void log(String msg);
    }
}
