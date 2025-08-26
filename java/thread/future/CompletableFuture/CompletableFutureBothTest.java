package com.njust.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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

    /**
     * thenCombine: (T,U)->V
     */
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
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("error result=" + ex.getCause().getMessage());
            } else {
                System.out.println("result=" + r); // ✅ result=200
            }
        });
    }

    /**
     * 任何一个Future 异常完成，组合操作就不会执行+但是会异常传递
     *
     * <pre>
     * future1 run
     * future2 begin
     * future2 end
     * error result=dave error
     * </pre>
     */
    @Test
    public void testThenCombine_error() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 run"); // ✅ 执行
            throw new RuntimeException("dave error");
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 begin"); // ✅ 执行
            sleep(200);
            System.out.println("future2 end"); // ✅ 执行
            return "20";
        });

        future1.thenCombine(future2, (result1, result2) -> {
            System.out.println("combine result1:" + result1 + " ,result2:" + result2); // ❌不会执行，但是会把异常传递下去
            return result1 * Integer.parseInt(result2);
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("error result=" + ex.getCause().getMessage()); //✅ error result=dave error
            } else {
                System.out.println("result=" + r); // ❌不会执行
            }
        });

        sleep(500);
    }

    /**
     * thenAcceptBoth: (T,U)->void
     */
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
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("error result=" + ex.getCause().getMessage());
            } else {
                System.out.println("result=" + r); // ✅ result=null
            }
        });
    }

    /**
     * 任何一个Future 异常完成，组合操作就不会执行+但是会异常传递
     *
     * <pre>
     * future1 run
     * future2 run
     * error result=dave error
     * </pre>
     */
    @Test
    public void testThenAcceptBoth_error() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 run"); // ✅ 执行
            throw new RuntimeException("dave error");
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 run"); // ✅ 执行
            return "20";
        });

        future1.thenAcceptBoth(future2, (result1, result2) -> {
            System.out.println("combine result1:" + result1 + " ,result2:" + result2); // ❌不会执行，但是会把异常传递下去
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("error result=" + ex.getCause().getMessage()); // ✅ error result=dave error
            } else {
                System.out.println("result=" + r);  // ❌不会执行
            }
        });
    }

    /**
     * runAfterBoth: ()->void
     */
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
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("error result=" + ex.getCause().getMessage());
            } else {
                System.out.println("result=" + r);  // ✅ result=null
            }
        });
    }

    /**
     * 任何一个Future 异常完成，组合操作就不会执行+但是会异常传递
     *
     * <pre>
     * future1 run
     * future2 run
     * error result=dave error
     * </pre>
     */
    @Test
    public void testRunAfterBoth_error() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 run"); // ✅ 执行
            throw new RuntimeException("dave error");
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 run"); // ✅ 执行
            return "20";
        });

        future1.runAfterBoth(future2, () -> {
            System.out.println("combine finish"); // ❌不会执行，但是会把异常传递下去
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("error result=" + ex.getCause().getMessage()); // ✅ error result=dave error
            } else {
                System.out.println("result=" + r);   // ❌不会执行
            }
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
