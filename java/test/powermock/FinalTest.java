package com.njust.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

/**
 * 模拟final方法
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Final.class)
public class FinalTest {

    @Mock
    private Final aFinal;

    @Test
    public void add() {
        PowerMockito.when(aFinal.add(1, 2)).thenCallRealMethod();
        assertEquals(3, aFinal.add(1, 2));

        PowerMockito.when(aFinal.add(1, 2)).thenReturn(-1);
        assertEquals(-1, aFinal.add(1, 2));
    }

    /**
     * say方法调用add方法，我们直接模拟say方法，让他不要调用add方法
     */
    @Test
    public void say() {
        PowerMockito.doNothing().when(aFinal).say(2, 3);

        //此时不会调用say方法，也不会调用add方法
        aFinal.say(2, 3);
    }

    /**
     * say方法调用add方法，我们间接模拟add方法
     * <pre>
     *     say:-7
     * </pre>
     */
    @Test
    public void say_mockAdd() {
        PowerMockito.when(aFinal.add(3, 4)).thenReturn(-7);
        PowerMockito.doCallRealMethod().when(aFinal).say(3, 4);

        aFinal.say(3, 4);
    }

    @Test
    public void mock_finalMethod_inJar() {
        // W_TODO: 2024/2/4
    }
}
