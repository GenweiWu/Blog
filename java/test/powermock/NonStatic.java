package com.njust.learn;

public class NonStatic {

    public void sayAge(int age) {
        System.out.println("sayAge is called:" + age);
        if (age <= 0) {
            throw new IllegalArgumentException("age is negative");
        }

        System.out.println("age=" + age);
    }

    private String returnAge(int age) {
        System.out.println("returnAge is called:" + age);
        if (age <= 0) {
            throw new IllegalArgumentException("age is negative");
        }
        return "age=" + age;
    }

    public void sayAge222(int age) {
        System.out.println("sayAge222 is called:" + age);
        String returnAge = returnAge(age);
        System.out.println(returnAge);
    }
}
