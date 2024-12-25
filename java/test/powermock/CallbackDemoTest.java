package com.njust.learn;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CallbackDemo.class)
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

        countDownLatch.await(2, TimeUnit.SECONDS);

        Integer actual = result.get();
        Assert.assertNotNull(actual);
        Assert.assertEquals((int) actual, 120);
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
        CallbackDemo callbackDemoMock = PowerMockito.mock(CallbackDemo.class);

        doAnswer(invocation -> {
            //0:100,  1:20,  2:Consumer<Integer>
            Consumer<Integer> consumer = invocation.getArgument(2);
            consumer.accept(666);
            return null;
        }).when(callbackDemoMock).calculate(eq(200), eq(40), any(Consumer.class));

        //test
        AtomicReference<Integer> result = new AtomicReference<>(null);
        callbackDemoMock.calculate(200, 40, x -> {
            result.set(x);
        });

        //test: no wait here
        Integer actual = result.get();
        Assert.assertNotNull(actual);
        Assert.assertEquals((int) actual, 666);
    }

    /**
     * 模拟异步方法2：间接模拟,通过模拟方法
     * <pre>{@code doCalculate}</pre>
     * 方法
     * <p>
     */
    @Test
    public void doCalculate_mock() throws Exception {
        //mock
        CallbackDemo callbackDemoMock = PowerMockito.mock(CallbackDemo.class);
        //mock private method
        when(callbackDemoMock, "doCalculate", 300, 60).thenReturn(-1);
        //mock以后，不指定参数when-then的话,调用的不是真实方法，也没有模拟方法
        doCallRealMethod().when(callbackDemoMock).calculate(eq(300), eq(60), any(Consumer.class));

        //test
        AtomicReference<Integer> result = new AtomicReference<>(null);
        callbackDemoMock.calculate(300, 60, x -> {
            result.set(x);
        });

        Thread.sleep(3000);

        //test: no wait here
        Integer actual = result.get();
        Assert.assertNotNull(actual);
        Assert.assertEquals((int) actual, -1);
    }
}
