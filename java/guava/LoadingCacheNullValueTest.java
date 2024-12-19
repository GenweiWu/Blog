package com.njust.guaua;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * 1.get获取value如果得到null会抛异常: load方法不支持返回null
 * <p>
 * 2.规避方法是用Optional.empty()来代替null
 */
@Slf4j
public class LoadingCacheNullValueTest {

    private LoadingCache<Integer, Integer> loadingCache;
    private LoadingCache<Integer, Optional<Integer>> loadingCacheFixed;

    @Before
    public void init() {
        this.loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<Integer, Integer>() {
            @Override
            public Integer load(Integer key) throws Exception {
                //1.有时候我们生成的逻辑比较复杂(仅有key是不够的)，我们会单独调用put方法去设置value，此处就想返回null
                if (key < 0) {
                    return null;
                }
                return key + 1;
            }
        });

        this.loadingCacheFixed = CacheBuilder.newBuilder().build(new CacheLoader<Integer, Optional<Integer>>() {
            @Override
            public Optional<Integer> load(Integer key) throws Exception {
                if (key < 0) {
                    return Optional.empty();
                }
                return Optional.of(key + 1);
            }
        });
    }

    @Test
    public void testNonNullValue() throws ExecutionException {
        int key = 111;
        int value = loadingCache.get(key);
        Assert.assertEquals(112, value);

        Optional<Integer> optionalInteger = loadingCacheFixed.get(key);
        Assert.assertTrue(optionalInteger.isPresent());
        Assert.assertEquals(112, (int) optionalInteger.get());
    }

    /**
     * load方法返回null会报错
     */
    @Test(expected = CacheLoader.InvalidCacheLoadException.class)
    public void testNullValue() throws ExecutionException {
        //此时会抛出异常
        //com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key -1.
        loadingCache.get(-1);
    }

    /**
     * 规避方法是返回Optional.empty来代替null
     */
    @Test
    public void testNullValueAfterFixed() throws ExecutionException {
        Optional<Integer> optionalValue = loadingCacheFixed.get(-1);
        Assert.assertFalse(optionalValue.isPresent());
    }

}
