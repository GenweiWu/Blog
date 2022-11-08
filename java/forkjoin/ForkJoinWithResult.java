package com.njust.forkjoin;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 以一个有返回值的大任务为例，介绍一下RecursiveTask的用法。
 * <p>
 * 大任务是：计算随机的1000个数字的和。
 * <p>
 * 小任务是：每次只能50个数值的和。
 */
public class ForkJoinWithResult extends RecursiveTask<Integer> {
    private static final int LIMIT = 50;

    private final int[] data;
    private final int min;
    private final int max;


    public ForkJoinWithResult(int[] data, int min, int max) {
        this.data = data;
        this.min = min;
        this.max = max;
    }

    @Override
    protected Integer compute() {

        if (max - min < LIMIT) {
            int sum = 0;
            for (int i = min; i <= max; i++) {
                sum += data[i];
            }
            System.out.println(Thread.currentThread().getName() + "--->" + sum);
            return sum;
        } else {
            int middle = (max - min) / 2 + min;
            ForkJoinWithResult left = new ForkJoinWithResult(data, min, middle);
            ForkJoinWithResult right = new ForkJoinWithResult(data, middle + 1, max);
            invokeAll(left, right);

            //获取计算结果
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //prepare data
        int[] data = prepareData();

        //任务
        ForkJoinWithResult forkJoinWithResult = new ForkJoinWithResult(data, 0, data.length - 1);

        //执行任务
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(forkJoinWithResult);

        //获取结果1
        Integer sum = forkJoinWithResult.get();
        System.out.println("actual sum is:" + sum);

        //关闭线程池
        forkJoinPool.shutdown();
    }

    private static int[] prepareData() {
        int SIZE = 1000;
        int[] arr = new int[SIZE];
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            arr[i] = new Random().nextInt();
            sum += arr[i];
        }
        System.out.println("expect sum is:" + sum);
        return arr;
    }
}

/**
 * -- console
 * expect sum is:-1116076102
 * ForkJoinPool-1-worker-1--->635326939
 * ForkJoinPool-1-worker-3--->297703472
 * ForkJoinPool-1-worker-1--->-1798684524
 * ForkJoinPool-1-worker-1--->-652498313
 * ForkJoinPool-1-worker-1--->-1487186967
 * ForkJoinPool-1-worker-1--->1085427380
 * ForkJoinPool-1-worker-1--->-2055041478
 * ForkJoinPool-1-worker-1--->250879889
 * ForkJoinPool-1-worker-1--->-640768240
 * ForkJoinPool-1-worker-5--->-712765010
 * ForkJoinPool-1-worker-5--->-688185243
 * ForkJoinPool-1-worker-2--->-931970445
 * ForkJoinPool-1-worker-5--->-2123086600
 * ForkJoinPool-1-worker-1--->-1288139455
 * ForkJoinPool-1-worker-3--->511042841
 * ForkJoinPool-1-worker-3--->-1758119940
 * ForkJoinPool-1-worker-3--->-180313001
 * ForkJoinPool-1-worker-3--->-40790002
 * ForkJoinPool-1-worker-4--->447861498
 * ForkJoinPool-1-worker-3--->1719056079
 * ForkJoinPool-1-worker-1--->-1330586054
 * ForkJoinPool-1-worker-1--->1669541760
 * ForkJoinPool-1-worker-5--->-1437623792
 * ForkJoinPool-1-worker-2--->2134325741
 * ForkJoinPool-1-worker-5--->685952229
 * ForkJoinPool-1-worker-5--->-283038339
 * ForkJoinPool-1-worker-1--->-581881943
 * ForkJoinPool-1-worker-3--->-1638939797
 * ForkJoinPool-1-worker-4--->1619991821
 * ForkJoinPool-1-worker-4--->1708874211
 * ForkJoinPool-1-worker-6--->1419614771
 * ForkJoinPool-1-worker-5--->32977114
 * actual sum is:-1116076102
 */
