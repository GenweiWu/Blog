package com.njust.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 创建completableFuture的几种方式
 * <ol>
 * <li>runAsync 异步执行，无返回值</li>
 * <li>supplyAsync 异步执行，有返回值</li>
 * <li>anyOf 任意一个任务执行完成，就进行下一步动作</li>
 * <li>allOf 所有任务都执行完成，才进行下一步任务</li>
 * </ol>
 */
@Slf4j
public class CompletableFutureTest {

    private static void sleep() {
        sleep(200);
    }

    private static void sleep(int timeInMs) {
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            TimeUnit.MILLISECONDS.sleep(timeInMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 1.没返回值用runAsync
     */
    @Test
    public void withoutResult() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                //加随机数，为了能触发成功和失败的场景
                double random = Math.random();
                if (random < 0.3) {
                    throw new RuntimeException("==> run failed!");
                }
                log.info("==> run result is:{}", random);
            }
        }, Executors.newSingleThreadExecutor());

        //执行成功
        completableFuture.thenAccept(new Consumer<Void>() {
            @Override
            public void accept(Void unused) {
                log.info("run finished");
            }
        });

        //执行失败
        completableFuture.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                log.error("run failed", throwable);
                return null;
            }
        });
    }

    /**
     * 2.有返回值用supplyAsync
     */
    @Test
    public void withResult() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                String result = "hello";
                log.info("==> supply {}", result);
                return result;
            }
        }, Executors.newSingleThreadExecutor());

        completableFuture.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info("supply success got result:{}", s);
            }
        });

        sleep();
    }

    /**
     * 3.串行执行 thenApplyAsync
     */
    @Test
    public void serial() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            log.info("==> task1 run");
            return 111;
        }, executorService);
        CompletableFuture<String> task2 = task1.thenApplyAsync(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                String s = "<<<" + integer + ">>>";
                log.info("==> task2 run:{}", s);
                return s;
            }
        });

        task2.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info("==> task3 print:{}", s);
            }
        });

        sleep();
    }

    /**
     * 4.并行执行 anyOf只要一个执行成功
     * <p>
     * anyOf并行执行时，task1a和task1b都会执行，但是只要拿到其中一个的结果就会继续往下执行
     * <p>
     *
     * <pre>
     *     task1a --|           | task2a |
     *              | -anyOf->  |        | -anyOf --> (finalResult)
     *     task1b --|           | task2b |
     * </pre>
     *
     * <p>
     *
     *
     * <pre>
     * 2024-08-27 11:23:53 INFO  [pool-1-thread-2] CompletableFutureTest:141 - ==> task1b run
     * 2024-08-27 11:23:53 INFO  [pool-1-thread-1] CompletableFutureTest:135 - ==> task1a run
     * 2024-08-27 11:23:53 INFO  [pool-1-thread-2] CompletableFutureTest:142 - ==> task1b finish
     * 2024-08-27 11:23:53 INFO  [ForkJoinPool.commonPool-worker-2] CompletableFutureTest:163 - ==> task2b begin
     * 2024-08-27 11:23:53 INFO  [ForkJoinPool.commonPool-worker-1] CompletableFutureTest:152 - ==> task2a begin
     * 2024-08-27 11:23:53 INFO  [ForkJoinPool.commonPool-worker-2] CompletableFutureTest:166 - ==> task2b got:[2]_122
     * 2024-08-27 11:23:53 INFO  [ForkJoinPool.commonPool-worker-2] CompletableFutureTest:175 - final result:[2]_122
     * 2024-08-27 11:23:53 INFO  [pool-1-thread-1] CompletableFutureTest:137 - ==> task1a finish
     * 2024-08-27 11:23:53 INFO  [ForkJoinPool.commonPool-worker-1] CompletableFutureTest:156 - ==> task2a got:[1]_122
     * </pre>
     */
    @Test
    public void parallelAnyOf() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> task1a = CompletableFuture.supplyAsync(() -> {
            log.info("==> task1a run");
            sleep(50);
            log.info("==> task1a finish");
            return 111;
        }, executorService);
        CompletableFuture<Integer> task1b = CompletableFuture.supplyAsync(() -> {
            log.info("==> task1b run");
            log.info("==> task1b finish");
            return 122;
        }, executorService);

        //合并task1a和task1b
        CompletableFuture<Object> cfTask111 = CompletableFuture.anyOf(task1a, task1b);

        CompletableFuture<String> task2a = cfTask111.thenApplyAsync(new Function<Object, String>() {
            @Override
            public String apply(Object code) {
                log.info("==> task2a begin");
                sleep(60);

                String msg = "[1]_" + (Integer) code;
                log.info("==> task2a got:{}", msg);
                return msg;
            }
        });
        CompletableFuture<String> task2b = cfTask111.thenApplyAsync(new Function<Object, String>() {
            @Override
            public String apply(Object code) {
                log.info("==> task2b begin");

                String msg = "[2]_" + (Integer) code;
                log.info("==> task2b got:{}", msg);
                return msg;
            }
        });

        CompletableFuture<Object> task222 = CompletableFuture.anyOf(task2a, task2b);
        task222.thenAccept(new Consumer<Object>() {
            @Override
            public void accept(Object s) {
                log.info("final result:{}", s);
            }
        });

        sleep();
    }

    /**
     * 并行执行  allOf所有任务都要成功
     *
     * <pre>
     *     task1a --|           | task2a |
     *              | -allOf->  |        | -allOf --> (finalResult)
     *     task1b --|           | task2b |
     * </pre>
     *
     * <pre>
     *  2024-08-27 14:49:49 INFO  [pool-1-thread-1] CompletableFutureTest:217 - ==> task1a run
     * 2024-08-27 14:49:49 INFO  [pool-1-thread-2] CompletableFutureTest:223 - ==> task1b run
     * 2024-08-27 14:49:49 INFO  [pool-1-thread-2] CompletableFutureTest:224 - ==> task1b finish
     * 2024-08-27 14:49:49 INFO  [pool-1-thread-1] CompletableFutureTest:219 - ==> task1a finish
     * 2024-08-27 14:49:49 INFO  [ForkJoinPool.commonPool-worker-2] CompletableFutureTest:245 - ==> task2b begin
     * 2024-08-27 14:49:49 INFO  [ForkJoinPool.commonPool-worker-1] CompletableFutureTest:234 - ==> task2a begin
     * 2024-08-27 14:49:49 INFO  [ForkJoinPool.commonPool-worker-2] CompletableFutureTest:248 - ==> task2b got:[2]_111_122
     * 2024-08-27 14:49:49 INFO  [ForkJoinPool.commonPool-worker-1] CompletableFutureTest:238 - ==> task2a got:[1]_111_122
     * 2024-08-27 14:49:49 INFO  [ForkJoinPool.commonPool-worker-1] CompletableFutureTest:258 - final result:[1]_111_122 and [2]_111_122
     * </pre>
     */
    @Test
    public void parallelAllOf() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        //1-------------
        CompletableFuture<Integer> task1a = CompletableFuture.supplyAsync(() -> {
            log.info("==> task1a run");
            sleep(50);
            log.info("==> task1a finish");
            return 111;
        }, executorService);
        CompletableFuture<Integer> task1b = CompletableFuture.supplyAsync(() -> {
            log.info("==> task1b run");
            log.info("==> task1b finish");
            return 122;
        }, executorService);
        //合并task1a和task1b
        CompletableFuture<Void> cfTask111 = CompletableFuture.allOf(task1a, task1b);

        //2----------
        CompletableFuture<String> task2a = cfTask111.thenApplyAsync(new Function<Void, String>() {
            @Override
            public String apply(Void unused) {
                log.info("==> task2a begin");
                sleep(60);

                String msg = "[1]_" + (Integer) task1a.join() + "_" + (Integer) task1b.join();
                log.info("==> task2a got:{}", msg);
                return msg;
            }
        });
        CompletableFuture<String> task2b = cfTask111.thenApplyAsync(new Function<Void, String>() {
            @Override
            public String apply(Void unused) {
                log.info("==> task2b begin");

                String msg = "[2]_" + (Integer) task1a.join() + "_" + (Integer) task1b.join();
                log.info("==> task2b got:{}", msg);
                return msg;
            }
        });
        CompletableFuture<Void> task222 = CompletableFuture.allOf(task2a, task2b);
        task222.thenAccept(new Consumer<Void>() {
            @Override
            public void accept(Void unused) {
                String msg2a = task2a.join();
                String msg2b = task2b.join();
                log.info("final result:{} and {}", msg2a, msg2b);
            }
        });

        sleep();
    }

}
