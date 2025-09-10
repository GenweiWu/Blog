package com.njust.learn;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * 学习whenNew用法
 */
public class NewObjectUtilTest {

    private static File FILE;

    @BeforeAll
    public static void setup() throws IOException {
        FILE = Files.createFile(Paths.get("D:/xxx.tmp")).toFile();
    }

    @AfterAll
    public static void tearDown() throws IOException {
        Files.deleteIfExists(FILE.toPath());
    }

    @Test
    public void getFileName() {
        NewObjectUtil newObjectUtil = new NewObjectUtil();
        assertNull(newObjectUtil.getFileName("xxx"));
        assertEquals("xxx.tmp", newObjectUtil.getFileName("D:/xxx.tmp"));
    }

    /**
     * 直接模拟
     * {@code new NewObjectUtil()}
     */
    @Test
    public void mockNewObjectUtil() {
        NewObjectUtil newObjectUtil = new NewObjectUtil();
        assertEquals("xxx.tmp", newObjectUtil.getFileName("D:/xxx.tmp"));

        //mock
        try (MockedConstruction<NewObjectUtil> ignored = mockConstruction(NewObjectUtil.class)) {
            NewObjectUtil newObjectUtil2 = new NewObjectUtil();
            when(newObjectUtil2.getFileName(anyString())).thenReturn("haha");

            assertEquals("haha", newObjectUtil2.getFileName("D:/xxx.tmp"));
        }

        assertEquals("xxx.tmp", newObjectUtil.getFileName("D:/xxx.tmp"));
    }

    /**
     * 间接模拟
     * {@code new File("xx")}
     *
     * <p>
     * <font color="red">java.lang.StackOverflowError但是不建议模拟标准库的方法</font>
     */
    @Test
    public void getFileName_mock() {
        NewObjectUtil newObjectUtil = new NewObjectUtil();
        assertEquals("xxx.tmp", newObjectUtil.getFileName("D:/xxx.tmp"));

        //mock
        try (MockedConstruction<File> ignored = mockConstruction(File.class, (mock, context) -> {
            when(mock.isFile()).thenReturn(true);
            when(mock.getName()).thenReturn("hello");
        })) {
            NewObjectUtil newObjectUtil2 = new NewObjectUtil();
            assertEquals("hello", newObjectUtil2.getFileName("D:/xxx.tmp"));
        }

        assertEquals("xxx.tmp", newObjectUtil.getFileName("D:/xxx.tmp"));
    }
}
