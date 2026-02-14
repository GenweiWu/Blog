package com.njust.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Junit5ParameterizedTest {

    @DisplayName("测试ValueSource")
    @ParameterizedTest
    @ValueSource(strings = {"aaa", "BBB"})
    public void testWithStr(String str) {
        assertNotNull(str);
    }

    @DisplayName("测试CsvSource")
    @ParameterizedTest
    @CsvSource({
            "1,3,4",
            "3,6,9"
    })
    public void testAdd(int a, int b, int expect) {
        assertEquals(expect, a + b);
    }

    @DisplayName("测试MethodSource")
    @ParameterizedTest
    @MethodSource("userDataProvider")
    public void testWithMethodSource(String dateStr, LocalDate expect) {
        YearMonth yearMonth = YearMonth.parse(dateStr, DateTimeFormatter.ofPattern("yyyy年MM月"));
        LocalDate localDate = yearMonth.atDay(1);
        assertEquals(expect, localDate);
    }

    private static Stream<Arguments> userDataProvider() {
        return Stream.of(
                Arguments.of("2020年01月", LocalDate.of(2020, 1, 1)),
                Arguments.of("2026年02月", LocalDate.of(2026, 2, 1))
        );
    }

    @DisplayName("测试EnumSource")
    @Nested
    class EnumSourceTest {
        @ParameterizedTest
        @EnumSource(Type.class)
        public void testWithEnum(Type type) {
            assertTrue(type == Type.MANUAL || type == Type.SCHEDULE);
        }

        @ParameterizedTest
        @EnumSource(value = Type.class, names = {"SCHEDULE"})
        public void testWithEnum2(Type type) {
            assertTrue(type == Type.SCHEDULE);
        }

        public enum Type {
            MANUAL,
            SCHEDULE
        }
    }


    @Nested
    public class NullOrEmptyTest {

        @ParameterizedTest
        @NullSource
        public void nullTest(String msg) {
            assertNull(msg);
        }

        @ParameterizedTest
        @EmptySource
        public void emptyTest(String msg) {
            assertEquals("", msg);
        }

        @ParameterizedTest
        @NullSource
        @EmptySource
        @ValueSource(strings = "abc")
        public void nullOrEmptyTest(String msg) {
            assertTrue(msg == null || msg.isEmpty() || msg.equals("abc"));
        }

    }

}
