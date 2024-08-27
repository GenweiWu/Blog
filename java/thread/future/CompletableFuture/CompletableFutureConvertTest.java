package com.njust.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * 将方法转化为CompletableFuture形式
 * <ul>
 *     <li>正常结束:complete(T)</li>
 *     <li>报错时:completeExceptionally(E)</li>
 * </ul>
 */
@Slf4j
public class CompletableFutureConvertTest {

    private BiConsumer<String, Throwable> sayHelloCallback;

    @Before
    public void setUp() {
        sayHelloCallback = new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String result, Throwable e) {
                if (e != null) {
                    log.error("say hello failed", e);
                    return;
                }

                log.info("say hello success:{}", result);
            }
        };
    }

    @Test
    public void testSuccess() {
        CompletableFuture<String> cf = toCompletableFuture(new Hello() {
            @Override
            public String sayHello() {
                return "dave";
            }
        });
        cf.whenComplete(sayHelloCallback);
    }

    @Test
    public void testException() {
        CompletableFuture<String> cf = toCompletableFuture(new Hello() {
            @Override
            public String sayHello() {
                throw new NullPointerException();
            }
        });
        cf.whenComplete(sayHelloCallback);
    }

    /**
     * 1.可以将其他的调用转换为CompletableFuture形式的
     */
    private CompletableFuture<String> toCompletableFuture(Hello hello) {
        CompletableFuture<String> cf = new CompletableFuture<>();

        try {
            String result = hello.sayHello();
            //正常结束
            cf.complete(result);
        } catch (Exception e) {
            //存在异常时
            cf.completeExceptionally(e);
        }

        return cf;
    }

    private static interface Hello {
        public String sayHello();
    }
}
