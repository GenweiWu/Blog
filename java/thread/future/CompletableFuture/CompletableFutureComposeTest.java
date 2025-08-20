package com.njust.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureComposeTest {

    /**
     * 任务1,2都没有返回值
     * <br>
     * future1.thenCompose(s -> future2) 意味着
     * <ul>
     *     <li>必须等 future1 完全完成后，才会开始"等待" future2。</li>
     *     <li>但是，future2 本身是独立启动的，它可能很早就已经完成了（它的执行不依赖 future1）</li>
     * </ul>
     */
    @Test
    public void compose_void() {
        StringBuilder sb = new StringBuilder();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sleep(10);
            sb.append("111-");
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            sb.append("222-");
        });

        future1.thenCompose(s -> future2).thenAccept(x -> {
            //result=222-111-
            System.out.println("result=" + sb);
        });

        sleep(30);
    }

    /**
     * 任务1,2有返回值
     * <p>
     * 没有保存返回的 Future
     * </p>
     */
    @Test
    public void compose_void_002() {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "111"; // 异步任务，最终会完成并返回 "111"
        });

        // 这里创建了一个新的 CompletableFuture，但没有赋值给任何变量
        future1.thenApply(x -> {
            return x + "222"; // 这个转换的结果被丢弃了！
        });

        // 这里又创建了一个新的 CompletableFuture，同样没有保存
        future1.thenAccept(x -> {
            System.out.println("x=" + x); // 这里会打印 "x=111"
        });
    }

    /**
     * 复用之前的Future才能串行执行
     */
    @Test
    public void compose_void_002b() {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "111"; // 步骤1：异步产生结果 "111"
        });

        // 链式调用：每个方法都返回一个新的 CompletableFuture
        future1.thenApply(x -> {          // 步骤2：接收 "111"，返回 "111222"
            return x + "222";
        }).thenAccept(x -> {              // 步骤3：接收 "111222"，进行打印
            System.out.println("x=" + x);
        });

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
