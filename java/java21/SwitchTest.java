package com.njust.test.java21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwitchTest {

    @Test
    public void test() {
        assertEquals("111", convert_jdk8(1));
        assertEquals("222", convert_jdk8(2));
        assertEquals("10", convert_jdk8(9));
        assertEquals("124", convert_jdk8(123));

        assertEquals("111", convert_jdk21(1));
        assertEquals("222", convert_jdk21(2));
        assertEquals("10", convert_jdk21(9));
        assertEquals("124", convert_jdk21(123));
    }

    public String convert_jdk8(int num) {

        switch (num) {
            case 1:
                return "111";
            case 2:
                return "222";
            case 10:
                num = num - 1;
                return "" + num;
            default:
                num = num + 1;
                return "" + num;
        }
    }

    public String convert_jdk21(int num) {

        return switch (num) {
            case 1 -> "111";
            case 2 -> "222";
            case 10 -> {
                num = num - 1;
                yield "" + num;
            }
            default -> {
                num = num + 1;
                yield "" + num;
            }
        };
    }

}
