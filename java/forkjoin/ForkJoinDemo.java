package com.njust.forkjoin;

import java.util.Random;
import java.util.stream.IntStream;

public class ForkJoinDemo {


    public static void main(String[] args) {
        //test1();
        test2();
    }

    private static void test2() {
        //计算随机的1000个数字的和。
        int[] data = prepareData();
        int sum = IntStream.of(data).parallel().reduce(0, Integer::sum);
        System.out.println("expect sum is:" + sum);
    }

    private static void test1() {
        //打印0-50的数值。
        IntStream.rangeClosed(0, 50).parallel().forEach(x -> {
            System.out.println(Thread.currentThread().getName() + "-->" + x);
        });
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
 * main-->32
 * main-->33
 * main-->31
 * ForkJoinPool.commonPool-worker-2-->45
 * ForkJoinPool.commonPool-worker-2-->46
 * main-->36
 * ForkJoinPool.commonPool-worker-1-->15
 * ForkJoinPool.commonPool-worker-1-->16
 * ForkJoinPool.commonPool-worker-1-->17
 * ForkJoinPool.commonPool-worker-1-->13
 * ForkJoinPool.commonPool-worker-1-->14
 * main-->37
 * main-->34
 * main-->35
 * main-->28
 * main-->29
 * main-->30
 * main-->26
 * main-->27
 * main-->25
 * main-->7
 * main-->8
 * main-->6
 * main-->10
 * main-->11
 * main-->9
 * main-->3
 * main-->4
 * main-->5
 * ForkJoinPool.commonPool-worker-2-->44
 * main-->1
 * main-->2
 * main-->0
 * main-->21
 * main-->22
 * main-->23
 * main-->24
 * ForkJoinPool.commonPool-worker-1-->12
 * ForkJoinPool.commonPool-worker-4-->41
 * ForkJoinPool.commonPool-worker-4-->42
 * ForkJoinPool.commonPool-worker-1-->39
 * ForkJoinPool.commonPool-worker-1-->40
 * main-->19
 * main-->20
 * ForkJoinPool.commonPool-worker-5-->47
 * ForkJoinPool.commonPool-worker-5-->48
 * ForkJoinPool.commonPool-worker-2-->49
 * ForkJoinPool.commonPool-worker-1-->18
 * ForkJoinPool.commonPool-worker-3-->38
 * ForkJoinPool.commonPool-worker-4-->43
 * ForkJoinPool.commonPool-worker-2-->50
 */

/**
 * expect sum is:-931528797
 * expect sum is:-931528797
 */
