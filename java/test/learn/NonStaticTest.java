package com.njust.learn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(NonStatic.class) //因为这个类有私有方法
public class NonStaticTest {

    private NonStatic nonStatic;

    @Before
    public void setup() {
        nonStatic = PowerMockito.mock(NonStatic.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sayAge_exception() {
        //void方法要使用do-when的方法
        PowerMockito.doCallRealMethod().when(nonStatic).sayAge(-1);
        nonStatic.sayAge(-1);
    }

    /**
     * 1.主要是为了对比mock可以规避异常：即不会再调用真实方法
     */
    @Test
    public void sayAge_mockIgnoreException() {
        //void方法要使用do-when的方法
        PowerMockito.doNothing().when(nonStatic).sayAge(-2);
        nonStatic.sayAge(-2);
        //没具体模拟的参数，也不会调用真实方法的
        nonStatic.sayAge(-3);
    }

    /**
     * 2.直接模拟调用了内部私有方法的公有方法
     * <p>
     * 此时会模拟对应方法, 不会调用真实方法
     */
    @Test
    public void sayAge222() {
        PowerMockito.doNothing().when(nonStatic).sayAge222(-3);
        nonStatic.sayAge(-3);
    }

    /**
     * 3.公有方法调用了私有方法，模拟私有方法
     */
    @Test
    public void sayAge222_mockPrivateMethod() throws Exception {
        PowerMockito.doCallRealMethod().when(nonStatic).sayAge222(-4);
        PowerMockito.doCallRealMethod().when(nonStatic, "returnAge", -4);
        try {
            nonStatic.sayAge222(-4);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            //success
        }

        //如果还是-4可能报错，我不知道为啥，但是改成doReturn就行了
        PowerMockito.doCallRealMethod().when(nonStatic).sayAge222(-4);
        //java.lang.IllegalArgumentException: age is negative
        //PowerMockito.when(nonStatic, "returnAge", -4).thenReturn("age:100");
        PowerMockito.doReturn("age:100").when(nonStatic, "returnAge", -4);
        nonStatic.sayAge222(-4);
    }
}
