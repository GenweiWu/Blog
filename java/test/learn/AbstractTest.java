package com.njust.learn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Abstract.class)
public class AbstractTest {

    private Abstract anAbstract;

    @Before
    public void setUp() {
        anAbstract = PowerMockito.mock(Abstract.class);
    }

    /**
     * mock抽象类的普通方法
     */
    @Test
    public void add() {
        PowerMockito.when(anAbstract.add(1, 2)).thenReturn(-33);

        int add = anAbstract.add(1, 2);
        Assert.assertEquals(-33, anAbstract.add(1, 2));
    }

    /**
     * mock抽象类的静态方法
     */
    @Test
    public void staticMethod() {
        PowerMockito.mockStatic(Abstract.class);
        PowerMockito.when(Abstract.staticMethod("hello")).thenReturn("beYourself");

        String result = Abstract.staticMethod("hello");
        Assert.assertEquals("beYourself", result);
    }

    @Test
    public void finalMethod() {
        PowerMockito.when(anAbstract.finalMethod("finalHello")).thenReturn("onYourOwn");

        String result = anAbstract.finalMethod("finalHello");
        Assert.assertEquals("onYourOwn", result);
    }

    @Test
    public void privateMethod() throws Exception {
        PowerMockito.doReturn("struggle").when(anAbstract, "privateMethod", "privateHello");

        String result = Whitebox.invokeMethod(anAbstract, "privateMethod", "privateHello");
        Assert.assertEquals("struggle", result);
    }
}
