package com.njust.learn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 测试抽象类的模拟
 */
public abstract class Abstract {

    public String helloThere() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss");
        String timeStr = LocalDateTime.now().format(pattern);
        System.out.println("helloThere:" + timeStr);

        return normalMethod(timeStr);
    }

    public abstract String normalMethod(String msg);

    //abstract不能是static的
    //    public abstract static String staticMethod(String msg);

    //abstract方法不能是final的
    //    public abstract final String finalMethod(String msg);

    //abstract方法不能是private的
    //    private abstract String privateMethod(String msg);
}
