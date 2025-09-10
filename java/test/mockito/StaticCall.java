package com.njust.learn;

import java.time.LocalDate;

public class StaticCall {

    /**
     * 静态方法
     */
    public static String staticCallAdd(String a, String b) {
        return Static.add(a, b);
    }

    /**
     * 非静态方法
     */
    public String callAdd(String a, String b) {
        return Static.add(a, b);
    }

    public LocalDate getDate() {
        LocalDate now = LocalDate.now();
        return now.plusDays(1);
    }
}
