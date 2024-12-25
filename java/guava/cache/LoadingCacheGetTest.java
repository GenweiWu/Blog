package com.njust.guaua;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * 测试不同的get方法
 */
@Slf4j
public class LoadingCacheGetTest {

    private LoadingCache<String, String> loadingCache;

    @Before
    public void init() {
        this.loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                if (key.equals("error")) {
                    throw new IOException("invalid key");
                }
                return key.toUpperCase(Locale.ENGLISH);
            }
        });
    }

    /**
     * 1.直接调用get方法，会抛出checked异常:
     * <p>
     * 我们要么catch要么抛出
     */
    @Test
    public void testGet() {
        try {
            loadingCache.get("error");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            log.error("get value failed", cause);
        }
    }

    /**
     * 2.getUnchecked方法对get方法进行封装，改为抛出运行时异常
     * <p>
     * 参考源码:
     * <pre>
     *   {@code
     *   public V getUnchecked(K key) {
     *       try {
     *         return get(key);
     *       } catch (ExecutionException e) {
     *         throw new UncheckedExecutionException(e.getCause());
     *       }
     *     }
     *   }
     * </pre>
     */
    @Test(expected = UncheckedExecutionException.class)
    public void testGet2() {
        loadingCache.getUnchecked("error");
    }

    /**
     * 3.<pre>{@code V get(K key, Callable<? extends V> loader)}</pre>方法
     * 实际上使用自定义的方法，来代替默认的load方法
     * <p>
     * 但是不推荐用这个方法，建议 `use LoadingCache and its get(K) method instead of this one.`
     */
    @Test
    public void testGet3() throws ExecutionException {
        //[1] using default loader
        String key = "aaa";
        String value = loadingCache.get(key);
        Assert.assertEquals("AAA", value);

        //[2] using custom loader
        value = loadingCache.get(key, new CustomerLoader());
        //此时加载的缓存，没有调用CustomerLoader
        Assert.assertNotEquals("aaaNew_fromCall", value);

        loadingCache.invalidate(key);
        value = loadingCache.get(key, new CustomerLoader());
        Assert.assertEquals("aaaNew_fromCall", value);
    }

    private static class CustomerLoader implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "aaaNew_fromCall";
        }
    }

}
