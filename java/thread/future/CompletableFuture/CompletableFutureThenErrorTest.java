package com.njust.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 依赖型组合方法中的异常传递
 * <ol>
 *     <li>thenApply</li>
 *     <li>thenAccept</li>
 *     <li>thenRun</li>
 *     <li>thenCompose</li>
 * </ol>
 */
public class CompletableFutureThenErrorTest {

    /**
     * 上一阶段正常执行后，才会执行 thenApply,thenAccept,thenRun方法
     */
    @Test
    public void test01_then_normal() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 123;
        });
        future.thenApply(result -> {
            System.out.println("result from apply:" + result); // ✅ result from apply:123
            return result + 1;
        }).whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("result failed:" + ex.getMessage());
            } else {
                System.out.println("result success:" + result); // ✅ result success:124
            }
        });

        future.thenAccept(result -> {
            System.out.println("result from accept:" + result); // ✅ result from accept:123
        });

        future.thenRun(() -> {
            System.out.println("result finish"); // ✅ result finish
        });
    }

    /**
     * 上一阶段执行异常后，thenApply,thenAccept,thenRun方法不会执行；但是会传递异常
     */
    @Test
    public void test01_then_error() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Source failed");
        });
        future.thenApply(result -> {
            System.out.println("result from apply:" + result); // ❌ 上一阶段报错，所以不执行; 但是异常会传递
            return result + 1;
        }).whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("result failed:" + ex.getMessage());  // ✅ result failed:java.lang.RuntimeException: Source failed
            } else {
                System.out.println("result success:" + result); // ❌ 上一阶段报错，所以不执行
            }
        });

        future.thenAccept(result -> {
            System.out.println("result from accept:" + result); // ❌ 上一阶段报错，所以不执行
        });

        future.thenRun(() -> {
            System.out.println("result finish"); // ❌ 上一阶段报错，所以不执行
        });
    }

    /**
     * 上一阶段正常执行后，才会执行thenCompose方法
     */
    @Test
    public void then02_compose_normal() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 work");  // ✅ future1 work
            return 123;
        });
        future1.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("future1 result failed:" + ex.getMessage());
            } else {
                System.out.println("future1 result success:" + result);  // ✅ future1 result success:123
            }
        });

        future1.thenCompose(ignore -> {
            CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
                System.out.println("future2 work"); // ✅ future2 work
                return 456;
            }).whenComplete((result, ex) -> {
                if (ex != null) {
                    System.err.println("future2 result failed:" + ex.getMessage());
                } else {
                    System.out.println("future2 result success:" + result); // ✅ future2 result success:456
                }
            });
            return future2;
        }).whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("result failed:" + ex.getMessage());
            } else {
                System.out.println("result success:" + result); // ✅ result success:456
            }
        });
    }

    @Test
    public void then02_compose_error() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 work");  // ✅ future1 work
            throw new RuntimeException("Source failed");
        });
        future1.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("future1 result failed:" + ex.getMessage());  //✅ future1 result failed:java.lang.RuntimeException: Source failed
            } else {
                System.out.println("future1 result success:" + result);
            }
        });

        future1.thenCompose(ignore -> {
            // ❌ 上一阶段报错，以下代码都不执行!!!
            CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
                System.out.println("future2 work"); // ❌ 上一阶段报错，所以不执行
                return 123;
            }).whenComplete((result, ex) -> {
                if (ex != null) {
                    System.err.println("future2 result failed:" + ex.getMessage()); // ❌ 上一阶段报错，所以不执行
                } else {
                    System.out.println("future2 result success:" + result); // ❌ 上一阶段报错，所以不执行
                }
            });
            return future2;
        }).whenComplete((result, ex) -> {
            // ❌ 上一阶段报错，future2不执行，但是错误会被传递
            if (ex != null) {
                System.err.println("result failed:" + ex.getMessage()); // ✅ result failed:java.lang.RuntimeException: Source failed
            } else {
                System.out.println("result success:" + result);
            }
        });
    }

}
