package com.njust.concurrent;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步生产者-消费者（任务提交与处理解耦）
 * <p>
 * completableFuture结合ArrayBlockingQueue使用
 * </p>
 */
public class CompletableFutureWithABQ {

    //1. 定义一个任务消息体
    static class Task {
        final String id;
        final String data;
        final CompletableFuture<String> resultFuture;

        public Task(String id, String data, CompletableFuture<String> resultFuture) {
            this.id = id;
            this.data = data;
            this.resultFuture = resultFuture;
        }
    }

    //2.阻塞队列
    private static final BlockingQueue<Task> taskQueue = new ArrayBlockingQueue<>(10);

    //3.1消费者线程池
    private static final ExecutorService consumerExecutor = Executors.newFixedThreadPool(2);

    static {
        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    Task task = taskQueue.take();
                    System.out.printf("[consumer][%s] begin task: %s%n", Thread.currentThread().getName(), task.id);

                    CompletableFuture.runAsync(() -> {
                        // 模拟耗时处理
                        try {
                            Thread.sleep(1000 + new Random().nextInt(1000));
                            String result = "Processed: " + task.data.toUpperCase();
                            task.resultFuture.complete(result);
                        } catch (InterruptedException e) {
                            System.out.println("consumer run failed");
                        }
                    }, consumerExecutor);

                } catch (InterruptedException e) {
                    System.out.println("consumer failed");
                }
            }
        }, "Consumer-Thread");

        consumerThread.setDaemon(true);
        consumerThread.start();
    }

    //4.生产者
    public static CompletableFuture<String> submitTask(String data) {
        CompletableFuture<String> resultFuture = new CompletableFuture<>();

        String taskId = "Task-" + System.currentTimeMillis() + "-" + new Random().nextInt(1000);
        Task task = new Task(taskId, data, resultFuture);

        taskQueue.add(task);
        System.out.printf("task add: %s %n", taskId);

        return resultFuture;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            int taskNum = counter.getAndIncrement();

            String data = "ReqData" + taskNum;
            CompletableFuture<String> future = submitTask(data);
            future.thenAccept(result -> System.out.printf("callback got result:%s for data:%s%n", result, data));
        }

        // 等待一段时间看结果
        Thread.sleep(10000);
        consumerExecutor.shutdown();
    }

}
