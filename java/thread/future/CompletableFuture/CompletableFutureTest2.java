package com.njust.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest2 {

    /**
     * 没有保存返回的 Future
     */
    @Test
    public void test() {

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
    public void test_002() {

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

    /**
     * future2和 future3是并行执行的
     * <p>
     * 输出可能是111222333 或111333222
     */
    @Test
    public void test_003() {

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("111");
        });

        //future2
        future1.thenRun(() -> {
            System.out.println("222");
        });

        //future3
        future1.thenRun(() -> {
            System.out.println("333");
        });

    }
}
