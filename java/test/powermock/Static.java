package com.njust.learn;

public class Static {

    public static String add(String a, String b) {
        int result = Integer.parseInt(a) + Integer.parseInt(b);
        System.out.println("result:" + result);
        return String.valueOf(result);
    }

    public static void hello() {
        throw new RuntimeException();
    }

}
