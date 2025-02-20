package com.njust.learn;

public class Static {

    /**
     * 有参数+有返回值
     */
    public static String add(String a, String b) {
        int result = Integer.parseInt(a) + Integer.parseInt(b);
        System.out.println("result:" + result);
        return String.valueOf(result);
    }

    /**
     * 无参数+无返回值
     */
    public static void hello() {
        throw new RuntimeException();
    }


    /**
     * 有参数+无返回值
     */
    public static void helloWithParameter(String a, String b) {
        System.out.printf("a:%s, b:%s", a, b);
    }

}
