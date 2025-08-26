package com.njust.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 用于<b>组合两个独立的 CompletableFuture</b> ，且两个阶段都成功才会执行
 *
 * <ol>
 *     <li>thenCombine: (T,U)->V</li>
 *     <li>thenAcceptBoth: (T,U)->void</li>
 *     <li>runAfterBoth: ()->void</li>
 * </ol>
 */
public class CompletableFutureBothTest {

    @Test
    public void testThenCombine() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 run"); // ✅ 执行
            return 10;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 run"); // ✅ 执行
            return "20";
        });

        future1.thenCombine(future2, (result1, result2) -> {
            System.out.println("combine result1:" + result1 + " ,result2:" + result2); // ✅ combine result1:10 ,result2:20
            return result1 * Integer.parseInt(result2);
        }).thenAccept(r -> {
            System.out.println("result=" + r); // ✅ result=200
        });
    }

    @Test
    public void testThenAcceptBoth() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 run"); // ✅ 执行
            return 10;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 run"); // ✅ 执行
            return "20";
        });

        future1.thenAcceptBoth(future2, (result1, result2) -> {
            System.out.println("combine result1:" + result1 + " ,result2:" + result2); // ✅ combine result1:10 ,result2:20
        }).thenAccept(ignore -> {
            System.out.println("result=" + ignore); // ✅ result=null
        });
    }

    @Test
    public void testRunAfterBoth() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 run"); // ✅ 执行
            return 10;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 run"); // ✅ 执行
            return "20";
        });

        future1.runAfterBoth(future2, () -> {
            System.out.println("combine finish"); // ✅ 执行
        });
    }

}
