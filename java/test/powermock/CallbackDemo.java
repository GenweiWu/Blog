package com.njust.learn;

import java.util.function.Consumer;

public class CallbackDemo {

    /**
     * 测试异步方法的模拟
     */
    public void calculate(int a, int b, Consumer<Integer> consumer) {
        System.out.println(String.format("calculate begin with a:%s and b:%s", a, b));
        new Thread(() -> {
            int result = doCalculate(a, b);
            consumer.accept(result);
        }).start();
    }

    private int doCalculate(int a, int b) {
        //模拟计算慢
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return a + b;
    }


}
