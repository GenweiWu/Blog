package com.njust.future;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 扇入:组合多个Future任务(这些任务需要返回相同的结果:比如都返回int)
 */
@Slf4j
public class ListenableFutureAllDemo {
    private static ListenableFuture<Integer> successFuture111;
    private static ListenableFuture<Integer> successFuture222;
    private static ListenableFuture<Integer> failedFuture111;

    static {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        //0.由于下面都submit提交任务了，所以都会执行
        successFuture111 = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("success 1");
                return 1;
            }
        });
        successFuture222 = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("success 22");
                return 22;
            }
        });
        failedFuture111 = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.error("failed 111");
                return 33 / 0;
            }
        });
    }

    public static void main(String[] args) {

//        allAsList();
        successAsList();
    }

    /**
     * allAsList:只要有一个任务失败，则结果是失败的
     *
     * <pre>
     * 2024-08-13 19:15:37 INFO  [pool-1-thread-2] ListenableFutureAllDemo:37 - success 22
     * 2024-08-13 19:15:37 ERROR [pool-1-thread-3] ListenableFutureAllDemo:44 - failed 111
     * 2024-08-13 19:15:37 INFO  [pool-1-thread-1] ListenableFutureAllDemo:30 - success 1
     * 2024-08-13 19:15:39 ERROR [main] ListenableFutureAllDemo:73 - allAsList failed
     * java.lang.ArithmeticException: / by zero
     * 	...
     * 2024-08-13 19:15:39 INFO  [main] ListenableFutureAllDemo:82 - allAsList222 success:[1, 22]
     * </pre>
     */
    private static void allAsList() {
        //1.此时有一个失败，则会触发失败
        ListenableFuture<List<Integer>> listListenableFuture = Futures.allAsList(successFuture111, failedFuture111);
        Futures.addCallback(listListenableFuture, new FutureCallback<List<Integer>>() {
            @Override
            public void onSuccess(@Nullable List<Integer> result) {
                log.info("allAsList success:{}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("sleep error", e);
                }
                log.error("allAsList failed", t);
            }
        });

        //2.此时都是成功，所以触发成功
        ListenableFuture<List<Integer>> listListenableFuture222 = Futures.allAsList(successFuture111, successFuture222);
        Futures.addCallback(listListenableFuture222, new FutureCallback<List<Integer>>() {
            @Override
            public void onSuccess(@Nullable List<Integer> result) {
                log.info("allAsList222 success:{}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("sleep222 error", e);
                }
                log.error("allAsList222 failed", t);
            }
        });
    }

    /**
     * 2.无论任务成功还是失败，都会触发成功的回调
     * <p>
     * 但是失败的返回结果是null
     *
     * <pre>
     * 2024-08-13 19:22:56 ERROR [pool-1-thread-3] ListenableFutureAllDemo:45 - failed 111
     * 2024-08-13 19:22:56 INFO  [pool-1-thread-2] ListenableFutureAllDemo:38 - success 22
     * 2024-08-13 19:22:56 INFO  [pool-1-thread-1] ListenableFutureAllDemo:31 - success 1
     * 2024-08-13 19:22:56 INFO  [main] ListenableFutureAllDemo:62 - allAsList success:[1, null]
     * </pre>
     */
    private static void successAsList() {
        ListenableFuture<List<Integer>> listListenableFuture = Futures.successfulAsList(successFuture111, failedFuture111);
        Futures.addCallback(listListenableFuture, new FutureCallback<List<Integer>>() {
            @Override
            public void onSuccess(@Nullable List<Integer> result) {
                log.info("allAsList success:{}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("sleep error", e);
                }
                log.error("allAsList failed", t);
            }
        });
    }

}
