package com.njust.guaua;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LoadingCacheTest {


    @Test
    public void test() throws ExecutionException {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                //每次失效时，会调用这个方法进行初始化
                log.info("init by key:{}", key);
                TimeUnit.SECONDS.sleep(1);
                return key.toUpperCase(Locale.ENGLISH);
            }
        });

        log.info(">>> begin to get value");
        //1.第一次调用，会触发初始化
        String s = loadingCache.get("aaa");
        log.info("<<< end to get value:{}", s);

        log.info(">>> begin to get value");
        //2.第二次调用，就会使用缓存中的值
        s = loadingCache.get("aaa");
        log.info("<<< end to get value:{}", s);
    }

}
