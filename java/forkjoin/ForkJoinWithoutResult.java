package com.zte.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 一个没有返回值的大任务为例，介绍一下RecursiveAction的用法。
 * <p>
 * 大任务是：打印0-50的数值。
 * <p>
 * 小任务是：每次只能打印10个数值。
 */
public class ForkJoinWithoutResult extends RecursiveAction {

    private static final int LIMIT = 10;

    private final int min;
    private final int max;

    public ForkJoinWithoutResult(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected void compute() {

        if (max - min < LIMIT) {
            for (int i = min; i <= max; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + i);
            }
        } else {
            int middle = (max - min) / 2 + min;
            ForkJoinWithoutResult left = new ForkJoinWithoutResult(min, middle);
            ForkJoinWithoutResult right = new ForkJoinWithoutResult(middle + 1, max);

            invokeAll(left, right);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //不指定参数：则根据Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //提交任务
        ForkJoinWithoutResult forkJoinWithoutResult = new ForkJoinWithoutResult(0, 50);
        forkJoinPool.submit(forkJoinWithoutResult);

        //等待所有任务结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);

        //关闭线程池
        forkJoinPool.shutdown();
    }
}

/**
 * -- console
 *
 * ForkJoinPool-1-worker-1--->0
 * ForkJoinPool-1-worker-3--->13
 * ForkJoinPool-1-worker-3--->14
 * ForkJoinPool-1-worker-3--->15
 * ForkJoinPool-1-worker-2--->26
 * ForkJoinPool-1-worker-2--->27
 * ForkJoinPool-1-worker-2--->28
 * ForkJoinPool-1-worker-2--->29
 * ForkJoinPool-1-worker-2--->30
 * ForkJoinPool-1-worker-2--->31
 * ForkJoinPool-1-worker-2--->32
 * ForkJoinPool-1-worker-2--->33
 * ForkJoinPool-1-worker-2--->34
 * ForkJoinPool-1-worker-2--->35
 * ForkJoinPool-1-worker-4--->39
 * ForkJoinPool-1-worker-1--->1
 * ForkJoinPool-1-worker-1--->2
 * ForkJoinPool-1-worker-1--->3
 * ForkJoinPool-1-worker-1--->4
 * ForkJoinPool-1-worker-1--->5
 * ForkJoinPool-1-worker-1--->6
 * ForkJoinPool-1-worker-3--->16
 * ForkJoinPool-1-worker-3--->17
 * ForkJoinPool-1-worker-3--->18
 * ForkJoinPool-1-worker-3--->19
 * ForkJoinPool-1-worker-1--->7
 * ForkJoinPool-1-worker-5--->45
 * ForkJoinPool-1-worker-5--->46
 * ForkJoinPool-1-worker-5--->47
 * ForkJoinPool-1-worker-5--->48
 * ForkJoinPool-1-worker-5--->49
 * ForkJoinPool-1-worker-5--->50
 * ForkJoinPool-1-worker-6--->20
 * ForkJoinPool-1-worker-2--->36
 * ForkJoinPool-1-worker-2--->37
 * ForkJoinPool-1-worker-2--->38
 * ForkJoinPool-1-worker-6--->21
 * ForkJoinPool-1-worker-6--->22
 * ForkJoinPool-1-worker-6--->23
 * ForkJoinPool-1-worker-4--->40
 * ForkJoinPool-1-worker-4--->41
 * ForkJoinPool-1-worker-4--->42
 * ForkJoinPool-1-worker-4--->43
 * ForkJoinPool-1-worker-4--->44
 * ForkJoinPool-1-worker-1--->8
 * ForkJoinPool-1-worker-1--->9
 * ForkJoinPool-1-worker-1--->10
 * ForkJoinPool-1-worker-6--->24
 * ForkJoinPool-1-worker-1--->11
 * ForkJoinPool-1-worker-6--->25
 * ForkJoinPool-1-worker-1--->12
 *
 */
