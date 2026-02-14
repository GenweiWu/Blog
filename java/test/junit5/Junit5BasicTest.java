package com.njust.junit5;

import org.junit.jupiter.api.*;

public class Junit5BasicTest {

    @BeforeAll
    public static void initAll() {
        System.out.println("== 所有测试前执行 ==");
    }

    @BeforeEach
    public void init() {
        System.out.println("每个测试前执行");
    }

    @Test
    @DisplayName("功能测试")
    public void test() {
        System.out.println("test");
    }

    @Test
    @DisplayName("功能测试")
    @Disabled
    public void testNotReady() {
    }

    @AfterEach
    public void tearDown() {
        System.out.println("每个测试后执行");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("== 所有测试后执行 ==");
    }

}
