package com.njust.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 若需保证顺序执行，应使用 thenCompose 链式调用
 * （如 future1.thenCompose(s -> future2) 并确保 future2 在 future1 完成后才提交）
 */
public class CompletableFutureComposeTest {

    /**
     * 此时future1和future2是并行执行的
     * <br>
     * future1.thenCompose(s -> future2) 意味着
     * <ul>
     *     <li>必须等 future1 完全完成后，才会开始"等待" future2。</li>
     *     <li>但是，future2 本身是独立启动的，它可能很早就已经完成了（它的执行不依赖 future1）</li>
     * </ul>
     */
    @Test
    public void compose_test() {
        StringBuilder sb = new StringBuilder();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sleep(1);
            sb.append("111-");
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            sb.append("222-");
        });

        future1.thenCompose(s -> future2).thenAccept(x -> {
            //不可控
            //result=222-111-
            //result=111-222-
            System.out.println("result=" + sb);
        });

        sleep(30);
    }

    /**
     * 此时是顺序执行的
     * <p>
     * thenCompose适用返回CompletableFuture的方法
     */
    @Test
    public void compose_test_02() {
        StringBuilder sb = new StringBuilder();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sleep(10);
            sb.append("111-");
        });

        CompletableFuture<Void> future2 = future1.thenCompose(ignore -> {
            return CompletableFuture.runAsync(() -> {
                sb.append("222-");
            });
        });

        CompletableFuture<Void> future3 = future2.thenCompose(ignore -> {
            return CompletableFuture.runAsync(() -> {
                sb.append("333-");
            });
        });

        //result=111-222-333-
        future3.thenAccept(ignore -> {
            System.out.println("result=" + sb);
        });

        sleep(30);
    }


    private static void sleep(int timeInMs) {
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            TimeUnit.MILLISECONDS.sleep(timeInMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
