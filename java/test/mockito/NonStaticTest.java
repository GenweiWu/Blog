package com.njust.learn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;

/**
 * mockito可以模拟public方法，不支持模拟private方法
 */
@ExtendWith(MockitoExtension.class)  //用于注解校验
public class NonStaticTest {

    @Mock
    private NonStatic nonStatic;

    /**
     * 模拟普通方法
     */
    @Test
    public void sayAge_exception() {
        //void方法要使用do-when的方法
        doCallRealMethod().when(nonStatic).sayAge(-1);

        assertThrows(IllegalArgumentException.class, () -> {
            nonStatic.sayAge(-1);
        });
    }

    /**
     * 1.主要是为了对比mock可以规避异常：即不会再调用真实方法
     */
    @Test
    public void sayAge_mockIgnoreException() {
        //void方法要使用do-when的方法
        doNothing().when(nonStatic).sayAge(-2);
        nonStatic.sayAge(-2);
    }

    /**
     * 2.直接模拟调用了内部私有方法的公有方法
     * <p>
     * 此时会模拟对应方法, 不会调用真实方法
     */
    @Test
    public void sayAge222() {
        //mock的对象，默认就是啥也不干的
        //doNothing().when(nonStatic).sayAge222(-3);
        nonStatic.sayAge(-3);
    }

    /**
     * 3.公有方法调用了私有方法，模拟私有方法
     * <p>
     * <font color="red">mockito不支持模拟私有方法</>
     */
    @Test
    public void sayAge222_mockPrivateMethod() throws Exception {
        //不支持模拟私有方法
        //doCallRealMethod().when(nonStatic, "returnAge", -4);

        doCallRealMethod().when(nonStatic).sayAge222(-4);

        assertThrows(IllegalArgumentException.class, () -> {
            nonStatic.sayAge222(-4);
        });
    }
}
