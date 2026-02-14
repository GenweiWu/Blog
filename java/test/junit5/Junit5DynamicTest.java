package com.njust.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Junit5DynamicTest {

    @DisplayName("非动态测试")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void myNotDynamicTest(int i) {
        new Student(i);
    }

    @DisplayName("动态测试-使用Stream.of创建")
    @TestFactory
    public Stream<DynamicTest> myDynamicTest111() {
        return Stream.of(
                DynamicTest.dynamicTest("测试合法的", () -> new Student(10)),
                DynamicTest.dynamicTest("测试非法的", () -> {
                    assertThrows(IllegalArgumentException.class, () -> new Student(0));
                })
        );
    }

    @DisplayName("动态测试-从集合生成")
    @TestFactory
    public Stream<DynamicTest> myDynamicTest222() {
        List<Integer> list = List.of(-2, -1, 0);
        return list.stream().map(i -> DynamicTest.dynamicTest(
                "测试非法的" + i, () -> {
                    assertThrows(IllegalArgumentException.class, () -> new Student(i));
                }));
    }

    @DisplayName("动态测试-使用IntStream.range()333创建")
    @TestFactory
    public Stream<DynamicTest> myDynamicTest() {
        return IntStream.rangeClosed(1, 10).mapToObj(i ->
                DynamicTest.dynamicTest("" + i, () -> new Student(i))
        );
    }

    record Student(int age) {
        Student {
            if (age <= 0) {
                throw new IllegalArgumentException();
            }
        }
    }
}
