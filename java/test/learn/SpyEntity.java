package com.njust.learn;

public class SpyEntity {

    public String say(String msg) {
        return "say:" + msg;
    }

    public static String sayInt(Integer i) {
        return "sayInt:" + i;
    }
}
