package com.njust.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class Junit5NestedTest {

    private Stack<String> stack;

    @DisplayName("当stack为空")
    @Nested
    class StackEmpty {

        @BeforeEach
        public void init() {
            stack = new Stack<>();
        }

        @Test
        public void emptyTest() {
            assertTrue(stack.isEmpty());
        }

        @Test
        public void notEmptyAfterPushTest() {
            stack.push("");
            assertFalse(stack.isEmpty());
        }
    }

    @DisplayName("当stack不为空")
    @Nested
    class StackNotEmpty {

        @BeforeEach
        public void init() {
            stack = new Stack<>();
            stack.push("aaa");
            stack.push("BBB");
        }

        @Test
        public void stackTest() {
            assertFalse(stack.isEmpty());
            assertEquals(2, stack.size());
        }

    }

}
