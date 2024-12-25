package com.njust.learn;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

/**
 * 学习whenNew用法
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(NewObjectUtil.class)
public class NewObjectUtilTest {

    @Test
    public void getFileName() {
        NewObjectUtil newObjectUtil = new NewObjectUtil();
        Assert.assertNull(newObjectUtil.getFileName("xxx"));
        Assert.assertEquals(newObjectUtil.getFileName("D:/arthas.tmp"), "arthas.tmp");
    }

    @Test
    public void getFileName_mock() throws Exception {
        //mock
        File file = PowerMockito.mock(File.class);
        PowerMockito.when(file.isFile()).thenReturn(true);
        PowerMockito.when(file.getName()).thenReturn("haha");

        PowerMockito.whenNew(File.class).withArguments("yyy").thenReturn(file);

        //test
        NewObjectUtil newObjectUtil = new NewObjectUtil();
        Assert.assertEquals("haha", newObjectUtil.getFileName("yyy"));
    }
}
