package com.njust.guaua;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LoadingCacheTest {

    private int count;

    @Before
    public void setUp() {
        count = 0;
    }

    /**
     * 第一次调用get，缓存中没有命中则会去调用load方法进行初始化
     */
    @Test
    public void testLoadWhenFirstGet() throws ExecutionException {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key.toUpperCase(Locale.ENGLISH) + "_" + (++count);
            }
        });

        //1.get获取时获取不到，就会去调用load进行初始化
        String value = loadingCache.get("aa");
        Assert.assertEquals("AA_1", value);
        Assert.assertEquals(1, count);
    }

    /**
     * 从第二次开始，就可以从缓存中加载，而不用去load了
     */
    @Test
    public void testNonFirstGetWillUseCache() throws ExecutionException {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                //每次失效时，会调用这个方法进行初始化
                log.info("[2]init by key:{}", key);
                TimeUnit.SECONDS.sleep(1);
                return key.toUpperCase(Locale.ENGLISH) + "_" + (++count);
            }
        });

        log.info(">>> [1]begin to get value");
        //1.第一次调用，会触发初始化
        String s1 = loadingCache.get("aaa");
        Assert.assertEquals(1, count);
        Assert.assertEquals("AAA_1", s1);
        log.info("<<< [3]end to get value:{}", s1);


        log.info(">>> [4]begin to get value");
        //2.第二次调用，就会使用缓存中的值
        String s2 = loadingCache.get("aaa");
        Assert.assertEquals(1, count);
        Assert.assertEquals(s1, s2);
        log.info("<<< [5]end to get value:{}", s2);
    }

}
