package com.njust.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * future1.thenCompose只有在future1是正常结束时才会执行
 */
public class CompletableFutureComposeTest2 {

    /**
     * 如果顺序执行中抛异常了，这段代码实现不会感知到
     */
    @Test
    public void test_compose_error() {
        StringBuilder sb = new StringBuilder();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sb.append("111-");
            int a = 3 / 0;  //1.这里会抛出异常
        });

        //2.只有前面正常执行，才会执行后面
        CompletableFuture<Void> future2 = future1.thenCompose(ignore -> {
            return CompletableFuture.runAsync(() -> {
                sb.append("222-");
            });
        });

        //3.这里更跟不会执行了
        future2.thenAccept(ignore -> {
            System.out.println("result=" + sb);
        });
    }

    /**
     * 只处理future2的异常，会导致future2不会执行+future的状态是异常状态
     *
     * <p>result error=111-</p>
     */
    @Test
    public void test_compose_error_fix() {
        StringBuilder sb = new StringBuilder();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sb.append("111-");     // ✅ 正常执行，sb = "111-"
            int a = 3 / 0;         // ❌ 抛出 ArithmeticException
        });

        //thenCompose 只有在 future1 正常完成时才会执行其函数
        //因为 future1 以异常完成，所以 thenCompose 的回调函数根本不会执行
        //future2 直接继承 future1 的异常状态（相同的 ArithmeticException）
        CompletableFuture<Void> future2 = future1.thenCompose(ignore -> {
            return CompletableFuture.runAsync(() -> {
                sb.append("222-");  //❌ 不会执行
            }).exceptionally(ex -> {
                sb.append("error2-");   //❌ 不会执行
                return null;
            });
        });

        future2.thenAccept(ignore -> {
            System.out.println("result=" + sb);  // ❌ 不会执行（因为 future2 异常完成）
        }).exceptionally(ex -> {
            System.out.println("result error=" + sb);  // ✅ 执行异常处理，输出：result error=111-
            return null;
        });
    }

    /**
     * future1和future2都处理异常了
     * <p>
     * future1 的异常被 exceptionally 妥善处理，使其从异常状态恢复为正常完成状态
     * <p>
     * <p>result=111-error1-222-</p>
     */
    @Test
    public void test_compose_error_fix2() {
        StringBuilder sb = new StringBuilder();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sb.append("111-");     // ✅ 正常执行，sb = "111-"
            int a = 3 / 0;         // ❌ 抛出 ArithmeticException
        }).exceptionally(ex -> {
            sb.append("error1-");  // ✅ 异常被捕获，sb = "111-error1-"
            return null;           // 返回 null，future1 正常完成!!!
        });

        CompletableFuture<Void> future2 = future1.thenCompose(ignore -> {
            return CompletableFuture.runAsync(() -> {
                sb.append("222-");  // ✅ 正常执行，sb = "111-error1-222-"
            }).exceptionally(ex -> {
                sb.append("error2-"); // ❌ 不会执行（因为没有异常）
                return null;
            });
        });

        future2.thenAccept(ignore -> {
            System.out.println("result=" + sb);  // ✅ 输出：result=111-error1-222-
        }).exceptionally(ex -> {
            System.out.println("result error=" + sb);  // ❌ 不会执行（因为没有异常）
            return null;
        });
    }

    /**
     * <p>result=111-error1-222-</p>
     */
    @Test
    public void test_compose_error_fix3() {
        StringBuilder sb = new StringBuilder();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sb.append("111-");     // ✅ 正常执行，sb = "111-"
            int a = 3 / 0;         // ❌ 抛出 ArithmeticException
        }).thenAccept(ignore -> {
            sb.append("ignore-");  // ❌ 不会执行（因为前一步异常）
        }).exceptionally(ex -> {
            sb.append("error1-");  // ✅ 异常被捕获，sb = "111-error1-"
            return null;           // future1 正常完成
        });

        CompletableFuture<Void> future2 = future1.thenCompose(ignore -> {
            return CompletableFuture.runAsync(() -> {
                sb.append("222-");  // ✅ 正常执行，sb = "111-error1-222-"
            }).exceptionally(ex -> {
                sb.append("error2-"); // ❌ 不会执行（因为没有异常）
                return null;
            });
        });

        future2.thenAccept(ignore -> {
            System.out.println("result=" + sb);  // ✅ 输出：result=111-error1-222-
        }).exceptionally(ex -> {
            System.out.println("result error=" + sb);  // ❌ 不会执行（因为没有异常）
            return null;
        });

        sleep(10);
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
