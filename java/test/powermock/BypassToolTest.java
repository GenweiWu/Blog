package com.njust.learn;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

public class BypassToolTest {

    @Test
    public void getAttributeTest() {
        BypassTool bypassTool = new BypassTool();
        Assert.assertTrue(bypassTool.checkCount(0));

        //获取私有属性
        int count = Whitebox.getInternalState(bypassTool, "count");
        Assert.assertEquals(0, count);
    }

    @Test
    public void setAttributeTest() {
        BypassTool bypassTool = new BypassTool();
        bypassTool.increase();
        Assert.assertTrue(bypassTool.checkCount(1));

        //设置私有属性
        Whitebox.setInternalState(bypassTool, "count", 100);
        Assert.assertTrue(bypassTool.checkCount(100));
    }

    @Test
    public void callPrivateMethod() throws Exception {
        BypassTool bypassTool = new BypassTool();
        bypassTool.increase();
        Assert.assertTrue(bypassTool.checkCount(1));

        Whitebox.invokeMethod(bypassTool, "increaseCount", 20);
        Assert.assertTrue(bypassTool.checkCount(21));
    }

    @Test
    public void callConstructor() throws Exception {
        BypassTool bypassTool = new BypassTool();
        Assert.assertTrue(bypassTool.checkCount(0));

        //调用私有构造函数
        BypassTool bypassTool2 = Whitebox.invokeConstructor(BypassTool.class, 666);
        Assert.assertTrue(bypassTool2.checkCount(666));
    }

}
