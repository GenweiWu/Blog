package com.njust.guaua;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Random;
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
                //1.有时候我们生成的逻辑比较负责，仅有key是不行，我们会单独调用put方法去设置，此时就想返回null
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
        Integer value = loadingCache.get(key);
        log.info("get value:{} by key:{}", value, key);
    }

    @Test(expected = CacheLoader.InvalidCacheLoadException.class)
    public void testNullValue() throws ExecutionException {
        int key = -1;
        //2.此时会跑异常
        //com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key -1.
        int value = loadingCache.get(key);
        log.info("get value:{} by key:{}", value, key);
    }

    @Test
    public void testFixed() throws ExecutionException {
        int key = 111;
        Optional<Integer> value = loadingCacheFixed.get(key);
        log.info("get value:{} by key:{}", value, key);

        //null
        key = -1;
        value = loadingCacheFixed.get(key);
        log.info("get value:{} by key:{}", value, key);
    }

    @Test
    public void testFixedFlow() throws ExecutionException {
        int key = -1;
        Optional<Integer> optionalValue = loadingCacheFixed.get(key);
        log.info("get value:{} by key:{}", optionalValue, key);
        if (!optionalValue.isPresent()) {
            int newValue = key + new Random().nextInt();
            loadingCacheFixed.put(key, Optional.of(newValue));
        }

        optionalValue = loadingCacheFixed.get(key);
        log.info("get value:{} by key:{}", optionalValue, key);
    }

}
