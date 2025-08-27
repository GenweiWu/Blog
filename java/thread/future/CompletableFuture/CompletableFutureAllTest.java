package com.njust.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAllTest {


    /**
     * allOf - "所有都完成"
     *
     * <ul>
     *    <li>只有当所有给定的 Future 都正常完成时，返回的 Future 才正常完成（结果为 null）</li>
     *    <li>返回一个 {@code CompletableFuture<Void>}。这意味着你无法直接从 allOf 返回的 Future 中获取各个任务的结果。你需要手动从原始的 Future 对象中获取结果</li>
     * </ul>
     *
     * <pre>
     * task1
     * task2 begin
     * task3 begin
     * task2 end
     * task3 end
     * all begin
     * all end, sum=123
     * result=123
     * </pre>
     */
    @Test
    public void test_allOf() {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1"); // ✅ task1/task2/task3 并发
            return "100";
        });
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 begin"); // ✅ task1/task2/task3 并发
            sleep(300);
            System.out.println("task2 end"); // ✅
            return 20;
        });
        CompletableFuture<Double> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task3 begin"); // ✅ task1/task2/task3 并发
            sleep(600);
            System.out.println("task3 end"); // ✅
            return 3.45d;
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(task1, task2, task3);

        future.thenApply(ignore -> {
            System.out.println("all begin"); // ✅

            //这里任务都执行完成了,join实际上不会等待
            int r1 = Integer.parseInt(task1.join());
            Integer r2 = task2.join();
            int r3 = task3.join().intValue();

            int sum = r1 + r2 + r3;
            System.out.println("all end, sum=" + sum); // ✅ all end, sum=123
            return sum;
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("error result:" + ex.getCause().getMessage());
            } else {
                System.out.println("result=" + r); // ✅ result=123
            }
        });

        sleep(1000);
    }

    /**
     * allOf - "所有都完成"
     *
     * <ul>
     *    <li>如果任何一个 Future 异常完成，allOf 返回的 Future 会立即异常完成</li>
     *    <li>但 allOf 会等待所有其他 Future 完成</li>
     * </ul>
     *
     * <pre>
     * task1
     * task2 begin
     * task3 begin
     * task2 end
     * task3 end
     * error result:dave error
     * </pre>
     */
    @Test
    public void test_allOf_error() {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1"); // ✅ task1/task2/task3 并发
            throw new RuntimeException("dave error");
        });
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 begin"); // ✅ task1/task2/task3 并发
            sleep(300);
            System.out.println("task2 end"); // ✅
            return 20;
        });
        CompletableFuture<Double> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task3 begin"); // ✅ task1/task2/task3 并发
            sleep(600);
            System.out.println("task3 end"); // ✅
            return 3.45d;
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(task1, task2, task3);

        future.thenApply(ignore -> {
            System.out.println("all begin"); // ❌ 不会执行，因为上一阶段有任务失败了

            //这里任务都执行完成了,join实际上不会等待
            int r1 = Integer.parseInt(task1.join());
            Integer r2 = task2.join();
            int r3 = task3.join().intValue();

            int sum = r1 + r2 + r3;
            System.out.println("all end, sum=" + sum); // ❌ 不会执行，因为上一阶段有任务失败了
            return sum;
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("error result:" + ex.getCause().getMessage());  // ✅ error result:dave error
            } else {
                System.out.println("result=" + r);
            }
        });

        sleep(1000);
    }

    /**
     * anyOf - "任意一个完成"
     *
     * <ol>
     *     <li>结果类型为 Object，需转换</li>
     *     <li>以第一个完成的 Future 的状态完成</li>
     *     <li>其他future会继续执行</li>
     * </ol>
     *
     * <pre>
     * task1 begin
     * task1 end
     * task2 begin
     * task3 begin
     * anyOf result=100
     * final result=100
     * task2 end
     * task3 end
     * ===END===
     * </pre>
     */
    @Test
    public void test_anyOf_normal01() {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 begin"); // ✅
            System.out.println("task1 end"); // ✅ 这个任务先完成
            return "100";
        });
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 begin"); // ✅
            sleep(300);
            System.out.println("task2 end"); // ✅ 这个任务后完成+会继续执行
            return 20;
        });
        CompletableFuture<Double> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task3 begin"); // ✅
            sleep(600);
            System.out.println("task3 end"); // ✅ 这个任务后完成+会继续执行
            return 3.45d;
        });

        CompletableFuture<Object> firstResponse = CompletableFuture.anyOf(task1, task2, task3);

        firstResponse.thenApply(resultObj -> {
            int result;
            if (resultObj instanceof String) {
                result = Integer.parseInt((String) resultObj);
            } else if (resultObj instanceof Double) {
                result = ((Double) resultObj).intValue();
            } else {
                result = (int) resultObj;
            }
            System.out.println("anyOf result=" + result); // ✅ anyOf result=100
            return result;
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("final result:" + ex.getCause().getMessage()); // ✅ final result=100
            } else {
                System.out.println("final result=" + r);
            }
        });

        sleep(1000);
        System.out.println("===END==="); // ✅
    }

    /**
     * anyOf - "任意一个完成"
     *
     * <ol>
     *     <li>结果类型为 Object，需转换</li>
     *     <li>以第一个完成的 Future 的状态完成</li>
     *     <li>其他future会继续执行</li>
     * </ol>
     *
     * <pre>
     * task1 begin
     * task2 begin
     * task3 begin
     * task3 end
     * anyOf result=3
     * final result=3
     * task2 end
     * task1 end
     * ===END===
     * </pre>
     */
    @Test
    public void test_anyOf_normal02() {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 begin"); // ✅
            sleep(600);
            System.out.println("task1 end"); // ✅ 这个任务后完成+会继续执行
            return "100";
        });
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 begin"); // ✅
            sleep(300);
            System.out.println("task2 end"); // ✅ 这个任务后完成+会继续执行
            return 20;
        });
        CompletableFuture<Double> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task3 begin"); // ✅ 这个任务先完成
            System.out.println("task3 end"); // ✅
            return 3.45d;
        });

        CompletableFuture<Object> firstResponse = CompletableFuture.anyOf(task1, task2, task3);

        firstResponse.thenApply(resultObj -> {
            int result;
            if (resultObj instanceof String) {
                result = Integer.parseInt((String) resultObj);
            } else if (resultObj instanceof Double) {
                result = ((Double) resultObj).intValue();
            } else {
                result = (int) resultObj;
            }
            System.out.println("anyOf result=" + result); // ✅ anyOf result=3
            return result;
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("final result:" + ex.getCause().getMessage()); // ✅ final result=3
            } else {
                System.out.println("final result=" + r);
            }
        });

        sleep(1000);
        System.out.println("===END==="); // ✅
    }

    /**
     * anyOf - "任意一个完成"
     *
     * <ol>
     *     <li>以第一个完成的 Future 的状态完成。</li>
     *     <li>不会等待其他 Future 完成</li>
     *     <li>其他future会继续执行</li>
     * </ol>
     *
     * <pre>
     * task1 begin
     * task1 end
     * task2 begin
     * task3 begin
     * final result:task1 error
     * task2 end
     * task3 end
     * ===END===
     * </pre>
     */
    @Test
    public void test_anyOf_error01() {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 begin"); // ✅
            System.out.println("task1 end"); // ✅ 这个任务先完成
            throw new RuntimeException("task1 error");
        });
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 begin"); // ✅
            sleep(300);
            System.out.println("task2 end"); // ✅
            return 20;
        });
        CompletableFuture<Double> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task3 begin"); // ✅
            sleep(600);
            System.out.println("task3 end"); // ✅
            return 3.45d;
        });

        CompletableFuture<Object> firstResponse = CompletableFuture.anyOf(task1, task2, task3);

        firstResponse.thenApply(resultObj -> {
            int result;
            if (resultObj instanceof String) {
                result = Integer.parseInt((String) resultObj);
            } else if (resultObj instanceof Double) {
                result = ((Double) resultObj).intValue();
            } else {
                result = (int) resultObj;
            }
            System.out.println("anyOf result=" + result); // ❌ 不会执行，任务结果是异常
            return result;
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("final result:" + ex.getCause().getMessage());  // ✅ final result:task1 error
            } else {
                System.out.println("final result=" + r);
            }
        });

        sleep(1000);
        System.out.println("===END==="); // ✅
    }

    /**
     * anyOf - "任意一个完成"
     *
     * <ol>
     *     <li>以第一个完成的 Future 的状态完成。</li>
     *     <li>不会等待其他 Future 完成</li>
     *     <li>其他future会继续执行</li>
     * </ol>
     *
     * <pre>
     * task1 begin
     * task2 begin
     * task3 begin
     * task3 end
     * anyOf result=3
     * final result=3
     * task2 end
     * task1 end
     * ===END===
     * </pre>
     */
    @Test
    public void test_anyOf_error02() {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 begin"); // ✅
            sleep(600);
            System.out.println("task1 end"); // ✅
            throw new RuntimeException("task1 error"); // ✅ 这里虽然抛异常了，但是完成得慢，所以anyOff结果是成功
        });
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2 begin"); // ✅
            sleep(300);
            System.out.println("task2 end"); // ✅
            return 20;
        });
        CompletableFuture<Double> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task3 begin"); // ✅
            System.out.println("task3 end"); // ✅ 这个任务先完成
            return 3.45d;
        });

        CompletableFuture<Object> firstResponse = CompletableFuture.anyOf(task1, task2, task3);

        firstResponse.thenApply(resultObj -> {
            int result;
            if (resultObj instanceof String) {
                result = Integer.parseInt((String) resultObj);
            } else if (resultObj instanceof Double) {
                result = ((Double) resultObj).intValue();
            } else {
                result = (int) resultObj;
            }
            System.out.println("anyOf result=" + result); // ✅ anyOf result=3
            return result;
        }).whenComplete((r, ex) -> {
            if (ex != null) {
                System.err.println("final result:" + ex.getCause().getMessage());
            } else {
                System.out.println("final result=" + r); // ✅ final result=3
            }
        });

        sleep(1000);
        System.out.println("===END==="); // ✅
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
