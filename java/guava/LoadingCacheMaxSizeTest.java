package com.njust.guaua;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 通过maximumSize来设置缓存大小
 */
@Slf4j
public class LoadingCacheMaxSizeTest {

    public static final int MAX_SIZE = 2;

    /**
     * <h3>这里大小为2，则缓存大小撑满时，会把之前的旧缓存去掉；</h3>
     * <p>
     * 去掉旧缓存时，会优化去掉最近用的较少的
     *
     * <pre>
     * 2024-10-14 11:13:16 INFO  [main] LoadingCacheMaxSizeTest:28 - init by key:v1
     * 2024-10-14 11:13:16 INFO  [main] LoadingCacheMaxSizeTest:28 - init by key:v2
     * 2024-10-14 11:13:16 INFO  [main] LoadingCacheMaxSizeTest:28 - init by key:v3
     * 2024-10-14 11:13:16 ERROR [main] LoadingCacheMaxSizeTest:22 - remove detail:v1=V1
     * 2024-10-14 11:13:16 INFO  [main] LoadingCacheMaxSizeTest:28 - init by key:v1
     * 2024-10-14 11:13:16 ERROR [main] LoadingCacheMaxSizeTest:22 - remove detail:v2=V2
     * 2024-10-14 11:13:16 INFO  [main] LoadingCacheMaxSizeTest:28 - init by key:v2
     * 2024-10-14 11:13:16 ERROR [main] LoadingCacheMaxSizeTest:22 - remove detail:v3=V3
     * 2024-10-14 11:13:16 INFO  [main] LoadingCacheMaxSizeTest:28 - init by key:v3
     * 2024-10-14 11:13:16 ERROR [main] LoadingCacheMaxSizeTest:22 - remove detail:v1=V1
     * </pre>
     */
    @Test
    public void test() throws ExecutionException {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().maximumSize(MAX_SIZE).removalListener(new RemovalListener<String, String>() {
            @Override
            public void onRemoval(RemovalNotification<String, String> notification) {
                log.error("remove detail:{}", notification);
            }
        }).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                log.info("init by key:{}", key);
                return key.toUpperCase(Locale.ENGLISH);
            }
        });

        loadingCache.get("v1");
        loadingCache.get("v2");
        loadingCache.get("v3");

        loadingCache.get("v1");
        loadingCache.get("v2");
        loadingCache.get("v3");

    }

}
