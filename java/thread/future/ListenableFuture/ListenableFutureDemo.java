package com.njust.future;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1.通过<code>ListenableFuture.addListener</code>方法添加回调方法
 * <p>
 * 不过我感觉这个方法不太好，要自己去使用ListenableFuture.get方法
 *
 *
 * <pre>
 * 2024-08-13 15:15:11 INFO  [pool-1-thread-1] ListenableFutureDemo:22 - call running...
 * 2024-08-13 15:15:11 INFO  [main] ListenableFutureDemo:42 - main finished
 * 2024-08-13 15:15:13 INFO  [pool-1-thread-2] ListenableFutureDemo:33 - get result:6
 * </pre>
 */
@Slf4j
public class ListenableFutureDemo {

    public static void main(String[] args) {
        //1.用指定的线程池来创建任务
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        //2.添加任务
        ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("call running...");
                TimeUnit.SECONDS.sleep(2);
                return 6;
            }
        });

        //3.回调
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("get result:{}", listenableFuture.get());
                } catch (InterruptedException e) {
                    log.error("get result failed1", e);
                } catch (ExecutionException e) {
                    log.error("get result failed2", e);
                }
            }
        }, listeningExecutorService);

        log.info("main finished");
    }
}
