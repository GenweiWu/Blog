package com.njust.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Static.class) //因为这个类有静态方法
public class StaticTest {

    /**
     * 不mock时
     */
    @Test
    public void add() {
        String add = Static.add("1", "2");
        assertEquals("3", add);
    }

    /**
     * 1. mock某个参数，则该参数的返回就是模拟的return
     * 2. 其他没模拟的，也不会走真实方法了，而是对象的默认值(比如int=0,String=null)
     */
    @Test
    public void mockAdd() {
        PowerMockito.mockStatic(Static.class);
        PowerMockito.when(Static.add("3", "4")).thenReturn("-1");

        //this is test for mock
        assertEquals("-1", Static.add("3", "4"));

        //this is test for real: 没模拟的变成null了
        String result = Static.add("14", "13");
        assertNotEquals("27", result);
        assertNotEquals("-1", result);
        assertNull(result);
    }

    /**
     * spy可以实现部分模拟
     * 1. when.thenReturn的会返回模拟的值；但是也会先调用真实方法
     * 2. 没有模拟的参数会调用真实方法
     * 3.
     */
    @Test
    public void spyAdd() {
        PowerMockito.spy(Static.class);
        PowerMockito.when(Static.add("3", "4")).thenReturn("-1");

        //this is test for mock：1.会先调用真实方法
        assertEquals("-1", Static.add("3", "4"));

        //this is test for real: 2.没模拟的调用真实方法了
        assertEquals("27", Static.add("14", "13"));

        //3.这样模拟，不会去调用真实方法了！！
        PowerMockito.stub(PowerMockito.method(Static.class, "add")).toReturn("-2");
        assertEquals("-2", Static.add("24", "23"));
        //3.2 这里也变成-2了
        assertEquals("-2", Static.add("14", "13"));
    }
}
