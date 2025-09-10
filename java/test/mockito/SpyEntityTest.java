package com.njust.learn;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * 学习spy的用法
 */
public class SpyEntityTest {

    @Test
    public void say() {
        SpyEntity spyEntity = new SpyEntity();
        assertEquals("say:111", spyEntity.say("111"));
        assertEquals("say:222", spyEntity.say("222"));
    }

    @Test
    public void say_mock() {
        SpyEntity spyEntity = mock(SpyEntity.class);
        when(spyEntity.say("111")).thenReturn("aaa");

        assertEquals("aaa", spyEntity.say("111"));
        //没有模拟返回值，则对应String默认值为null
        assertNull(spyEntity.say("222"));
    }

    /**
     * 直接spy方法
     */
    @Test
    public void say_spy() {
        SpyEntity spyEntity = spy(new SpyEntity());
        when(spyEntity.say("111")).thenReturn("aaa");

        assertEquals("aaa", spyEntity.say("111"));
        //没有模拟返回值，则调用实际方法
        assertEquals("say:222", spyEntity.say("222"));
    }

    @Test
    public void sayStatic() {
        assertEquals("sayStatic:777", SpyEntity.sayStatic(777));
        assertEquals("sayStatic:999", SpyEntity.sayStatic(999));
    }

    /**
     * mockito不支持spy静态方法，只能用mock来代替
     */
    @Test
    public void sayStatic_spy() {
        try (MockedStatic<SpyEntity> mockStatic = mockStatic(SpyEntity.class)) {
            mockStatic.when(() -> SpyEntity.sayStatic(777)).thenReturn("aaa");
            mockStatic.when(() -> SpyEntity.sayStatic(999)).thenCallRealMethod();

            assertEquals("aaa", SpyEntity.sayStatic(777));
            assertEquals("sayStatic:999", SpyEntity.sayStatic(999));
        }
    }
}
