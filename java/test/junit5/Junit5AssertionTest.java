package com.njust.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Junit5AssertionTest {

    @DisplayName("一次性验证多个条件")
    @Test
    public void testAllAssertion() {
        User user = new User("dave", 35);

        assertAll("用户信息验证",
                () -> assertNotNull(user),
                () -> assertEquals("dave", user.name),
                () -> assertEquals(35, user.age)
        );
    }

    @DisplayName("捕获并验证异常")
    @Test
    public void testException() {
        assertThrows(IllegalArgumentException.class, () -> new User("dave", -1));
    }

    @DisplayName("超时断言")
    @Test
    public void testTimeout() {
        assertTimeout(Duration.ofSeconds(2), () -> {
            Thread.sleep(1000); // 模拟业务操作
        });
    }

    record User(String name, int age) {
        public User {
            if (age < 0) {
                throw new IllegalArgumentException("age is invalid");
            }
        }
    }
}
