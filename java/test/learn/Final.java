package com.njust.learn;

public final class Final {

    public final int add(int a, int b) {
        System.out.println("add:" + a + "," + b);
        return a + b;
    }

    public final void say(int a, int b) {
        int result = add(a, b);
        System.out.println("say:" + result);
    }

}
