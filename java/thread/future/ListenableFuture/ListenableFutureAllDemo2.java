package com.njust.future;

import com.google.common.util.concurrent.*;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 扇入:组合多个Future任务(支持返回不同的结果对象，比如分别返回int和string)
 */
@Slf4j
public class ListenableFutureAllDemo2 {
    private static final ListenableFuture<Integer> future111;
    private static final ListenableFuture<String> future222;
    private static final ListenableFuture<List<String>> future333;
    private static final ListenableFuture<Integer> failedFuture;

    static {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        //0.由于下面都submit提交任务了，所以都会执行
        future111 = listeningExecutorService.submit(() -> {
            log.info("future111 success");
            return 1;
        });
        future222 = listeningExecutorService.submit(() -> {
            log.info("future222 success");
            return "22";
        });
        future333 = listeningExecutorService.submit(() -> {
            log.info("future333 success");
            return Arrays.asList("aa", "bb", "cc");
        });
        failedFuture = listeningExecutorService.submit(() -> {
            log.error("future444 failed");
            return 4 / 0;
        });
    }

    public static void main(String[] args) {

//        whenAllSucceed();
        whenAllComplete();
    }

    /**
     * whenAllSucceed:只要有一个任务失败，则结果是失败的
     * <p>
     * 对应 <==> allAsList
     *
     * <pre>
     * 2024-08-13 20:14:05 INFO  [pool-1-thread-1] ListenableFutureAllDemo2:30 - future111 success
     * 2024-08-13 20:14:05 INFO  [pool-1-thread-2] ListenableFutureAllDemo2:37 - future222 success
     * 2024-08-13 20:14:05 INFO  [pool-1-thread-3] ListenableFutureAllDemo2:44 - future333 success
     * 2024-08-13 20:14:05 INFO  [pool-1-thread-4] ListenableFutureAllDemo2:51 - future444 failed
     * 2024-08-13 20:14:05 INFO  [main] ListenableFutureAllDemo2:77 - begin to merge result
     * 2024-08-13 20:14:05 INFO  [main] ListenableFutureAllDemo2:89 - whenAllSucceed success:ListenableFutureAllDemo2.Result(code=1, msg=22, details=[aa, bb, cc])
     * 2024-08-13 20:14:07 ERROR [main] ListenableFutureAllDemo2:100 - whenAllSucceed failed
     * java.lang.ArithmeticException: / by zero
     *    ...
     * </pre>
     */
    private static void whenAllSucceed() {
        Callable<Result> callable = () -> {
            //1.在这里进行结果整合
            log.info("begin to merge result");
            Integer code = Futures.getDone(future111);
            String msg = Futures.getDone(future222);
            List<String> details = Futures.getDone(future333);
            return new Result(code, msg, details);
        };

        FutureCallback<Result> futureCallback = new FutureCallback<Result>() {
            @Override
            public void onSuccess(@Nullable Result result) {
                //2.成功时触发
                log.info("whenAllSucceed success:{}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                //3.只要有一个失败就会触发
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("sleep error", e);
                }
                log.error("whenAllSucceed failed", t);
            }
        };

        //全部成功时: 1+2
        ListenableFuture<Result> listenableFuture = Futures.whenAllSucceed(future111, future222, future333).call(callable);
        Futures.addCallback(listenableFuture, futureCallback);

        //存在失败时: 3
        ListenableFuture<Result> listenableFuture222 = Futures.whenAllSucceed(future111, future222, failedFuture).call(callable);
        Futures.addCallback(listenableFuture222, futureCallback);

    }

    /**
     * 2.无论任务成功还是失败，都会触发成功的回调
     * <p>
     * 但是失败的返回结果是null
     * <p>
     * 对应 <==> successAsList
     *
     * <pre>
     * 2024-08-14 10:01:22 INFO  [pool-1-thread-1] ListenableFutureAllDemo2:29 - future111 success
     * 2024-08-14 10:01:22 INFO  [pool-1-thread-2] ListenableFutureAllDemo2:33 - future222 success
     * 2024-08-14 10:01:22 ERROR [pool-1-thread-4] ListenableFutureAllDemo2:41 - future444 failed
     * 2024-08-14 10:01:22 INFO  [pool-1-thread-3] ListenableFutureAllDemo2:37 - future333 success
     * 2024-08-14 10:01:22 INFO  [main] ListenableFutureAllDemo2:118 - begin to merge result
     * 2024-08-14 10:01:22 ERROR [main] ListenableFutureAllDemo2:127 - merge failed, failedFuture failed!!!
     * 2024-08-14 10:01:22 INFO  [main] ListenableFutureAllDemo2:138 - whenAllComplete success:ListenableFutureAllDemo2.Result(code=1, msg=22, details=[aa, bb, cc])
     * </pre>
     */
    private static void whenAllComplete() {
        Callable<Result> callable = () -> {
            //1.在这里进行结果整合
            log.info("begin to merge result");
            //2.成功结束的，直接调用getDone获取返回值
            Integer code = Futures.getDone(future111);
            String msg = Futures.getDone(future222);
            List<String> details = Futures.getDone(future333);

            //3.抛异常结束的，failedFuture.idDone也是true，但是getDone方法会抛异常
            try {
                Integer code2 = Futures.getDone(failedFuture);
                log.info("merge detail, code2:{}", code2);
            } catch (ExecutionException e) {
                log.error("merge failed, failedFuture failed!!!");
            }


            return new Result(code, msg, details);
        };
        ListenableFuture<Result> listListenableFuture = Futures.whenAllComplete(future111, future222, future333, failedFuture).call(callable);

        Futures.addCallback(listListenableFuture, new FutureCallback<Result>() {
            @Override
            public void onSuccess(@Nullable Result result) {
                log.info("whenAllComplete success:{}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("whenAllComplete failed", t);
            }
        });
    }

    @ToString
    private static class Result {
        private final int code;
        private final String msg;
        private final List<String> details;

        public Result(int code, String msg, List<String> details) {
            this.code = code;
            this.msg = msg;
            this.details = details;
        }
    }
}
