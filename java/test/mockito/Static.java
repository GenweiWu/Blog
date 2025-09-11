package com.njust.learn;

public class Static {

    /**
     * 静态方法
     * <p>
     * 有参数+有返回值
     */
    public static String add(String a, String b) {
        int result = Integer.parseInt(a) + Integer.parseInt(b);
        System.out.println("result:" + result);
        return String.valueOf(result);
    }

    /**
     * 静态方法
     * <p>
     * 无参数+无返回值
     */
    public static void hello() {
        throw new RuntimeException();
    }


    /**
     * 静态方法
     * <p>
     * 有参数+无返回值
     */
    public static void helloWithParameter(String a, String b) {
        System.out.printf("a:%s, b:%s", a, b);
    }

    /**
     * 非静态方法
     */
    public int notStatic() {
        return 2025;
    }

}
