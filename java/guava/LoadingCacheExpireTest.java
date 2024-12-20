package com.njust.guaua;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 测试两种写入更新机制
 *
 * <ul>
 *     <li>如果load方法比较简单执行快，推荐用expireAfterWrite</li>
 *     <li>如果load方法复杂执行慢，建议用异步方法实现reload，此时推荐用refreshAfterWrite</li>
 * </ul>
 */
@Slf4j
public class LoadingCacheExpireTest {

    private static final int EXPIRE_IN_SECONDS = 3;
    private static final int RELOAD_IN_SECONDS = 1;

    /**
     * 1、expireAfterWrite会导致调用get方法阻塞等待返回
     */
    @Test
    public void expireAfterWrite() throws ExecutionException, InterruptedException {
        LoadingCache<String, Long> loadingCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_IN_SECONDS, TimeUnit.SECONDS)
                .build(new MyCacheLoader());

        syncLoadTest(loadingCache);
    }

    /**
     * 2、refreshAfterWrite会导致调用get方法时(如果load方法比较耗时)先返回就值
     * <h5 color="red">但是要求reload方法是异步执行的</h5>
     * <h5 color="red">默认的reload方法是同步的</h5>
     */
    @Test
    public void refreshAfterWrite() throws ExecutionException, InterruptedException {
        LoadingCache<String, Long> loadingCache = CacheBuilder.newBuilder().refreshAfterWrite(EXPIRE_IN_SECONDS, TimeUnit.SECONDS)
                .build(new MyCacheLoader());

        syncLoadTest(loadingCache);
    }

    private static void syncLoadTest(LoadingCache<String, Long> loadingCache) throws ExecutionException, InterruptedException {
        final String key = "key1";
        loadingCache.put(key, -1L);
        //not expire
        Assert.assertEquals(-1L, (long) loadingCache.get(key));

        //expired, loading...
        TimeUnit.SECONDS.sleep(EXPIRE_IN_SECONDS);
        //1.此时get方法会等待返回
        long begin = System.currentTimeMillis();
        Assert.assertEquals(666, (long) loadingCache.get(key));
        long end = System.currentTimeMillis();
        Assert.assertTrue((end - begin) / 1000 >= RELOAD_IN_SECONDS);
    }

    /**
     * 2、refreshAfterWrite会导致调用get方法时(如果load方法比较耗时)先返回旧值
     * <h5 color="red">覆盖reload方法进行异步执行</h5>
     */
    @Test
    public void refreshAfterWriteFixed() throws ExecutionException, InterruptedException {
        //AsyncCacheLoader重写reload方法为异步执行
        LoadingCache<String, Long> loadingCache = CacheBuilder.newBuilder().refreshAfterWrite(EXPIRE_IN_SECONDS, TimeUnit.SECONDS)
                .build(new AsyncCacheLoader());

        asyncLoadTest(loadingCache);
    }

    /**
     * 3、对2的更优雅的实现方式如下
     */
    @Test
    public void refreshAfterWriteFixed2() throws ExecutionException, InterruptedException {
        //将同步的cacheLoader改成异步的cacheLoader:这个方法只在原始的load方法是同步时有效果
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CacheLoader<String, Long> cacheLoader = CacheLoader.asyncReloading(new MyCacheLoader(), executorService);

        LoadingCache<String, Long> loadingCache = CacheBuilder.newBuilder().refreshAfterWrite(EXPIRE_IN_SECONDS, TimeUnit.SECONDS)
                .build(cacheLoader);

        asyncLoadTest(loadingCache);
    }

    private static void asyncLoadTest(LoadingCache<String, Long> loadingCache) throws ExecutionException, InterruptedException {
        final String key = "key1";
        loadingCache.put(key, -1L);
        //not expire
        Assert.assertEquals(-1L, (long) loadingCache.get(key));

        //expired, load is running...
        TimeUnit.SECONDS.sleep(EXPIRE_IN_SECONDS);
        //1.此时get方法会立即返回旧值
        long begin = System.currentTimeMillis();
        long value = loadingCache.get(key);
        long end = System.currentTimeMillis();
        //约等于0，用<=n00ms来测试
        Assert.assertTrue((end - begin) < 500);
        Assert.assertEquals(-1L, value);

        //load finished
        //保险起见，额外+1s
        TimeUnit.SECONDS.sleep(RELOAD_IN_SECONDS + 1);
        Assert.assertEquals(666L, (long) loadingCache.get(key));
    }


    private static class MyCacheLoader extends CacheLoader<String, Long> {

        @Override
        public Long load(String key) throws Exception {
            TimeUnit.SECONDS.sleep(RELOAD_IN_SECONDS);
            return 666L;
        }

        //这里会等待方法结果返回，其实是个同步方法
//        public ListenableFuture<V> reload(K key, V oldValue) throws Exception {
//            checkNotNull(key);
//            checkNotNull(oldValue);
//            return Futures.immediateFuture(load(key));
//        }
    }

    private static class AsyncCacheLoader extends MyCacheLoader {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        @Override
        public Long load(String key) throws Exception {
            TimeUnit.SECONDS.sleep(RELOAD_IN_SECONDS);
            return 666L;
        }

        @Override
        public ListenableFuture<Long> reload(String key, Long oldValue) throws Exception {
            ListenableFutureTask<Long> listenableFutureTask = ListenableFutureTask.create(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    TimeUnit.SECONDS.sleep(RELOAD_IN_SECONDS);
                    return 666L;
                }
            });
            executorService.submit(listenableFutureTask);
            return listenableFutureTask;
        }
    }

}
