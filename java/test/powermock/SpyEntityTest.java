package com.njust.learn;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.when;

/**
 * 学习spy的用法
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SpyEntity.class)
public class SpyEntityTest {

    @Test
    public void say_mock() {
        SpyEntity spyEntity = PowerMockito.mock(SpyEntity.class);
        when(spyEntity.say("111")).thenReturn("aaa");

        Assert.assertEquals(spyEntity.say("111"), "aaa");
        //没有模拟返回值，则对应String默认值为null
        Assert.assertNull(spyEntity.say("222"));
    }

    /**
     * 直接spy方法
     */
    @Test
    public void say_spy() {
        SpyEntity spyEntity = PowerMockito.spy(new SpyEntity());
        when(spyEntity.say("111")).thenReturn("aaa");

        Assert.assertEquals("aaa", spyEntity.say("111"));
        //没有模拟返回值，则调用实际方法
        Assert.assertEquals("say:222", spyEntity.say("222"));
    }

    @Test
    public void say_spyStatic() {
        PowerMockito.spy(SpyEntity.class);
        when(SpyEntity.sayInt(666)).thenReturn("bbb");

        Assert.assertEquals("bbb", SpyEntity.sayInt(666));
        //没有模拟返回值，则调用实际方法
        Assert.assertEquals("sayInt:777", SpyEntity.sayInt(777));
        Assert.assertEquals("sayInt:999", SpyEntity.sayInt(999));
    }
}
