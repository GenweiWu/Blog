package com.njust.learn.annotation;

import java.util.UUID;

public class AService {

    public String hello() {
        String id = createId("AService");
        System.out.println("id=" + id);
        return id;
    }

    private String createId(String prefix) {
        return prefix + "_" + UUID.randomUUID();
    }
}
