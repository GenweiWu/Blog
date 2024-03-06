package com.njust.learn.annotation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AService.class)
public class AServiceTest {

    /**
     * 我也不知道为啥写@Mock就报错，写@Spy就可以
     */
    @Spy
    private AService aService = new AService();

    @Before
    public void setup() {
        //使用注解要加上这行
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void hello() throws Exception {
        //这里会报错，无法模拟私有方法；用spy才可以
        PowerMockito.when(aService, "createId", "AService").thenReturn("123");

        Assert.assertEquals("123", aService.hello());

    }
}
