package com.njust.learn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.mockito.AdditionalMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VerifyDemo.class)
public class VerifyDemoTest {

    private VerifyDemo verifyDemo;

    @Before
    public void setUp() {
        verifyDemo = PowerMockito.spy(new VerifyDemo());
    }

    /**
     * 普通方法，非static，非private
     */
    @Test
    public void verify() {
        verifyDemo.work(3, 5);

        Mockito.verify(verifyDemo, times(3)).noPrivateNoStatic(anyInt());
    }

    /**
     * 验证私有方法
     */
    @Test
    public void verifyPrivate_times1() throws Exception {
        verifyDemo.work(1, 1);
        PowerMockito.verifyPrivate(verifyDemo, times(1)).invoke("privateMethod", 1);
    }

    @Test
    public void verifyPrivate_times3() throws Exception {
        verifyDemo.work(1, 3);
        PowerMockito.verifyPrivate(verifyDemo, times(3)).invoke("privateMethod", any(Integer.class));
    }

    /**
     * 验证static方法
     */
    @Test
    public void verifyStatic() {
        //mock
        PowerMockito.spy(VerifyDemo.class);

        //test
        verifyDemo.work(2, 2);

        //verify:验证指定的static方法被调用次数。指定的静态方法需要放在verifyStatic后面
        PowerMockito.verifyStatic(VerifyDemo.class, times(1));
        VerifyDemo.staticMethod(2);

        PowerMockito.verifyStatic(VerifyDemo.class, times(2));
        VerifyDemo.staticMethod222(2);
    }

    @Test
    public void verifyStatic_manyTimes() {
        //mock
        PowerMockito.spy(VerifyDemo.class);

        //test
        verifyDemo.work(2, 5);

        //verify:验证指定的static方法被调用次数。指定的静态方法需要放在verifyStatic后面
        PowerMockito.verifyStatic(VerifyDemo.class, times(4));
        VerifyDemo.staticMethod(and(geq(2), leq(5)));

        PowerMockito.verifyStatic(VerifyDemo.class, times(8));
        VerifyDemo.staticMethod222(and(geq(2), leq(5)));
    }

    /**
     * 测试构造函数
     */
    @Test
    public void verifyNewTest() throws Exception {
        //看起来，verifyNew的对象必须是模拟的，不能是真的创建File
        File file = PowerMockito.mock(File.class);
        whenNew(File.class).withArguments(anyString()).thenReturn(file);

        //test
        verifyDemo.getFileName(true);
        //verify
        PowerMockito.verifyNew(File.class, times(1)).withArguments(anyString());
    }
}
