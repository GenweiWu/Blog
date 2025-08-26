package com.njust.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * completableFuture处理正常结果
 * <ol>
 *     <li>thenApply: T->U</li>
 *     <li>thenAccept: T->void</li>
 *     <li>thenRun: ()->void</li>
 * </ol>
 */
@Slf4j
public class CompletableFutureThenTest {

    /**
     * thenApply 上一阶段正常结束后执行
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 666; // ✅ 执行
        });

        CompletableFuture<Integer> future2 = future1.thenApply(result -> {
            return result + 1; // ✅ 执行
        });

        Integer actual = future2.get();
        Assert.assertEquals(667, (int) actual); // ✅ 执行
    }

    /**
     * thenAccept 上一阶段正常结束后执行
     */
    @Test
    public void test2() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 666; // ✅ 执行
        });

        future1.thenAccept(result -> {
            System.out.println("result=" + result); // ✅ result=666
        });

    }

    /**
     * thenRun 上一阶段正常结束后执行
     */
    @Test
    public void test3() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 666; // ✅ 执行
        });

        future1.thenRun(() -> {
            System.out.println("run finish"); // ✅ run finish
        });

    }

}
