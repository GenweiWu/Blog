package com.njust.learn;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BypassToolTest {

    @Test
    public void getAttributeTest() {
        BypassTool bypassTool = new BypassTool();
        assertTrue(bypassTool.checkCount(0));

        //获取私有属性
        //int count = Whitebox.getInternalState(bypassTool, "count");
        Integer count = (Integer) ReflectionTestUtils.getField(bypassTool, "count");
        assertEquals(0, count);
    }

    @Test
    public void setAttributeTest() {
        BypassTool bypassTool = new BypassTool();
        bypassTool.increase();
        assertTrue(bypassTool.checkCount(1));

        //设置私有属性
        //Whitebox.setInternalState(bypassTool, "count", 100);
        ReflectionTestUtils.setField(bypassTool, "count", 100);
        assertTrue(bypassTool.checkCount(100));
    }

    @Test
    public void callPrivateMethod() {
        BypassTool bypassTool = new BypassTool();
        bypassTool.increase();
        assertTrue(bypassTool.checkCount(1));

        //Whitebox.invokeMethod(bypassTool, "increaseCount", 20);
        ReflectionTestUtils.invokeMethod(bypassTool, "increaseCount", 20);
        assertTrue(bypassTool.checkCount(21));
    }

    /**
     * 暂时没有现成方法替代
     */
    @Test
    public void callConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        BypassTool bypassTool = new BypassTool();
        assertTrue(bypassTool.checkCount(0));

        //调用私有构造函数
        //BypassTool bypassTool2 = Whitebox.invokeConstructor(BypassTool.class, 666);
        Constructor<BypassTool> constructor = BypassTool.class.getDeclaredConstructor(int.class);
        constructor.setAccessible(true);
        BypassTool bypassTool2 = constructor.newInstance(666);

        assertTrue(bypassTool2.checkCount(666));
    }

}
