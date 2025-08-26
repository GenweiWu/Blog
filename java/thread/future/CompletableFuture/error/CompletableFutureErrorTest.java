package com.njust.future.error;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 学习对比三种处理error的不同方式
 * <ol>
 *     <li>exceptionally</li>
 *     <li>handle</li>
 *     <li>whenComplete</li>
 * </ol>
 */
public class CompletableFutureErrorTest {


    /**
     * 1-0 测试下抛异常的情况
     */
    @Test(expected = ExecutionException.class)
    public void error_test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 3 / 0;
            return "Success";
        });

        future1.get();  // ❌ future1执行报错，则get方法会抛异常
    }

    /**
     * 1-1
     * <p>
     * exceptionally用来返回失败情况下的替换值 + 恢复正常状态
     */
    @Test
    public void error_test_fix1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 3 / 0;
            return "Success";
        }).exceptionally(ex -> {
            return "Default";     // ✅ 返回替换值
        });

        String actual = future1.get();
        Assert.assertEquals("Default", actual);  // ✅
    }

    /**
     * 1-2
     * handle 用来返回成功+失败情况下的替换值 + 恢复正常状态
     */
    @Test
    public void error_test_fix2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 3 / 0;
            return "Success";
        }).handle((result, ex) -> {
            if (ex != null) {
                return "Default from handle";  // ✅ 返回替换值
            } else {
                return result.toUpperCase();   // ❌ 报错了所以不执行
            }
        });

        String actual = future1.get();
        Assert.assertEquals("Default from handle", actual);  // ✅
    }

    /**
     * 1-3
     * whenComplete 仅记录成功+失败情况 + 不恢复正常状态
     */
    @Test(expected = ExecutionException.class)
    public void error_test_fix3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 3 / 0;
            return "Success";
        }).whenComplete((result, ex) -> {
            if (ex != null) {
                //只记录, 不处理, 不恢复
                System.err.println("Task failed with: " + ex.getMessage());  // ✅ 仅打印日志
            } else {
                System.out.println("Task completed with: " + result);
            }
        });

        String actual = future1.get();  // ❌ 报错了抛异常
        Assert.assertEquals("Default from handle", actual);  // ❌ 不会执行
    }

    /**
     * 2-0 测试下抛异常的情况
     */
    @Test
    public void error_test2() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 3 / 0;
            return "Success";
        });

        future1.thenAccept(x -> {
            System.out.println("x=" + x);  // ❌ 不会执行,future1异常结束
        });
    }

    /**
     * 2-1
     * <p>
     * exceptionally用来返回失败情况下的替换值 + 恢复正常状态
     */
    @Test
    public void error_test2_fix1() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 3 / 0;
            return "Success";
        }).exceptionally(ex -> {
            return "Default";     // ✅ 返回替换值
        });

        future1.thenAccept(x -> {
            System.out.println("x=" + x);  // ✅ x=Default
        });
    }

    /**
     * 2-2
     * handle 用来返回成功+失败情况下的替换值 + 恢复正常状态
     */
    @Test
    public void error_test2_fix2() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 3 / 0;
            return "Success";
        }).handle((result, ex) -> {
            if (ex != null) {
                return "Default from handle";  // ✅ 返回替换值
            } else {
                return result.toUpperCase();   // ❌ 报错了所以不执行
            }
        });

        future1.thenAccept(x -> {
            System.out.println("x=" + x);  // ✅ x=Default from handle
        });
    }

    /**
     * 2-3
     * whenComplete 仅记录成功+失败情况 + 不恢复正常状态
     */
    @Test
    public void error_test2_fix3() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 3 / 0;
            return "Success";
        }).whenComplete((result, ex) -> {
            if (ex != null) {
                //只记录, 不处理, 不恢复
                System.err.println("Task failed with: " + ex.getMessage());  // ✅ 仅打印日志
            } else {
                System.out.println("Task completed with: " + result);
            }
        });

        future1.thenAccept(x -> {
            System.out.println("x=" + x);  // ❌ 不会执行,future1异常结束
        });
    }

}
