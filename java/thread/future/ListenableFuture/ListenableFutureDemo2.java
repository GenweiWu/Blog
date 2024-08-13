package com.njust.future;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1.使用Futures.addCallback方法添加回调
 *
 * <pre>
 * 2024-08-13 15:46:17 INFO  [pool-1-thread-1] ListenableFutureDemo2:21 - call running begin...
 * 2024-08-13 15:46:17 INFO  [main] ListenableFutureDemo2:43 - main finished
 * 2024-08-13 15:46:18 INFO  [pool-1-thread-1] ListenableFutureDemo2:23 - call running end...
 * 2024-08-13 15:46:18 INFO  [pool-1-thread-2] ListenableFutureDemo2:32 - onSuccess:8
 * </pre>
 */
@Slf4j
public class ListenableFutureDemo2 {

    public static void main(String[] args) {
        //1
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        //2
        ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("call running begin...");
                TimeUnit.SECONDS.sleep(1);
                log.info("call running end...");
                return 8;
            }
        });

        //3.NEW
        FutureCallback<Integer> futureCallback = new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer result) {
                log.info("onSuccess:{}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("failed", t);
            }
        };

        //为啥不像Future一样阻塞呢?因为放到线程池中去执行回调了
        Futures.addCallback(listenableFuture, futureCallback, listeningExecutorService);

        log.info("main finished");
    }
}
