package com.njust.learn;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class CallbackDemoTest {

    /**
     * 利用
     * <pre>{@code
     * CountDownLatch    }
     * </pre>
     * 测试异步方法
     */
    @Test
    public void calculate() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        AtomicReference<Integer> result = new AtomicReference<>(null);
        new CallbackDemo().calculate(100, 20, x -> {
            result.set(x);
            countDownLatch.countDown();
        });

        boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
        assertTrue(await);

        Integer actual = result.get();
        assertNotNull(actual);
        assertEquals((int) actual, 120);
    }

    /**
     * 模拟异步方法1：直接模拟
     * <pre>{@code calculate}</pre>
     * 方法
     * <p>
     * 把参数修改成不一样，不然不同Test之间会互相影响
     */
    @Test
    public void calculate_mock() {
        //mock
        CallbackDemo callbackDemoMock = mock(CallbackDemo.class);

        doAnswer(invocation -> {
            //0:100,  1:20,  2:Consumer<Integer>
            Consumer<Integer> consumer = invocation.getArgument(2);
            consumer.accept(666);
            return null;
        }).when(callbackDemoMock).calculate(eq(200), eq(40), any(Consumer.class));

        //test
        AtomicReference<Integer> result = new AtomicReference<>(null);
        callbackDemoMock.calculate(200, 40, result::set);

        //test: no wait here
        Integer actual = result.get();
        assertNotNull(actual);
        assertEquals((int) actual, 666);
    }

    /**
     * 模拟异步方法2：间接模拟,通过模拟方法
     * <pre>{@code doCalculate}</pre>
     * 方法
     * <p>
     */
    @Test
    public void doCalculate_mock() {
        //❌ mockito无法模拟私有方法!!!
//        //mock private method
//        when(callbackDemoMock, "doCalculate", 300, 60).thenReturn(-1);
    }
}
