package com.njust.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * CompletableFuture的业务代码和回调分别由哪个线程执行?
 */
@Slf4j
public class CompletableFutureThreadTest {

    private static void sleep(int timeInMs) {
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            TimeUnit.MILLISECONDS.sleep(timeInMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 1.同步方法(即不带Async)
     *
     * <ol>
     *     <li>(0)</li>
     *     <li>(1)</li>
     *     <li>(2)</li>
     *     <li>(3)</li>
     * </ol>
     *
     * <pre>
     * 2024-08-28 09:34:19 INFO  [pool-1-thread-1] CompletableFutureThreadTest:35 - supplyAsync thread:pool-1-thread-1
     * 2024-08-28 09:34:19 INFO  [pool-1-thread-1] CompletableFutureThreadTest:43 - thenAccept fast thread:pool-1-thread-1
     * 2024-08-28 09:34:19 INFO  [main] CompletableFutureThreadTest:51 - thenAccept slow thread:main
     * </pre>
     */
    @Test
    public void thenAccept() {
        //(0)指定线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                //(1)supplyAsync由指定线程池中线程执行
                log.info("supplyAsync thread:{}", Thread.currentThread().getName());
                return 111;
            }
        }, executorService);

        //(2)此时，如果completableFuture任务还在执行，则由(1)的执行业务的线程继续执行
        completableFuture.thenAccept(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                log.info("thenAccept fast thread:{}", Thread.currentThread().getName());
            }
        });

        sleep(500);
        //(3)此时，如果completableFuture任务已执行完，则由当前线程main执行
        completableFuture.thenAccept(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                log.info("thenAccept slow thread:{}", Thread.currentThread().getName());
            }
        });

        sleep(200);
    }

    /**
     * 2.异步方法(即带Async)
     *
     * <ol>
     *     <li>(0)</li>
     *     <li>(1)</li>
     *     <li>(2)</li>
     *     <li>(3)</li>
     * </ol>
     *
     * <pre>
     * 2024-08-28 09:58:57 INFO  [pool-1-thread-1] CompletableFutureThreadTest:91 - supplyAsync thread:pool-1-thread-1
     * 2024-08-28 09:58:57 INFO  [pool-2-thread-1] CompletableFutureThreadTest:105 - thenAcceptAsync with threadPool thread:pool-2-thread-1
     * 2024-08-28 09:58:57 INFO  [ForkJoinPool.commonPool-worker-1] CompletableFutureThreadTest:99 - thenAcceptAsync thread:ForkJoinPool.commonPool-worker-1
     * </pre>
     */
    @Test
    public void acceptAsync() {
        //(0)指定线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                //(1)acceptAsync由指定线程池中线程执行
                sleep(200);
                log.info("supplyAsync thread:{}", Thread.currentThread().getName());
                return 222;
            }
        }, executorService);

        //(2)不指定线程池，则使用ForkJoinPool中的共用线程池CommonPool(大小为CPU核数-1)
        cf.thenAcceptAsync(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                log.info("thenAcceptAsync thread:{}", Thread.currentThread().getName());
            }
        });

        //(3)指定线程池，则使用指定线程池执行回调
        cf.thenAcceptAsync(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                log.info("thenAcceptAsync with threadPool thread:{}", Thread.currentThread().getName());
            }
        }, Executors.newCachedThreadPool());

        sleep(500);
    }
}
