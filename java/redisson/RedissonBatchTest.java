package com.njust.redisson;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;

import java.util.List;
import java.util.function.BiConsumer;

public class RedissonBatchTest {
    public static final String KEY_PREFIX = "test:batch:";
    public static final String VALUE_PREFIX = "helloBucket_";
    public static final int SIZE = 10;

    private static RedissonClient redissonClient;

    @BeforeClass
    public static void init() {
        redissonClient = Redisson.create();

        for (int i = 0; i < SIZE; i++) {
            redissonClient.getBucket(KEY_PREFIX + i).delete();
        }
    }

    @AfterClass
    public static void teardown() {
        redissonClient.shutdown();
    }

    @Test
    public void testAsync() {
        String key = "test:batch:init";
        String value = "1222";
        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value);

        //同步
        String actual = bucket.get();
        Assert.assertEquals(value, actual);

        //异步
        RFuture<String> rFuture = bucket.getAsync();
        rFuture.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                Assert.assertEquals(value, s);
            }
        });
    }

    @Test
    public void testBatchWrite() {
        RBatch batch = redissonClient.createBatch();
        for (int i = 0; i < SIZE; i++) {
            RBucketAsync<String> bucket = batch.getBucket(KEY_PREFIX + i, StringCodec.INSTANCE);
            bucket.setAsync(VALUE_PREFIX + i);
        }

        BatchResult<?> res = batch.execute();
        List<?> responses = res.getResponses();

        Assert.assertEquals(SIZE, responses.size());
        for (int i = 0; i < SIZE; i++) {
            Assert.assertNull(responses.get(i));
        }
    }

    @Test
    public void testBatchRead() {
        testBatchWrite();

        RBatch batch = redissonClient.createBatch();
        for (int i = 0; i < SIZE; i++) {
            RBucketAsync<String> bucket = batch.getBucket(KEY_PREFIX + i, StringCodec.INSTANCE);
            bucket.getAsync();
        }

        BatchResult<?> batchResult = batch.execute();
        List<?> responses = batchResult.getResponses();

        Assert.assertEquals(SIZE, responses.size());
        for (int i = 0; i < SIZE; i++) {
            Assert.assertEquals(VALUE_PREFIX + i, responses.get(i));
        }
    }

    /**
     * 给以奇数结尾的字符串，添加+++
     * <p>
     * batch的原理所有命令一起发送给redis，所以无法在一个batch中先判断，再修改
     */
    @Test
    public void testJudgeThenModify() {
        testBatchWrite();

        RBatch batch = redissonClient.createBatch();
        for (int i = 0; i < SIZE; i++) {
            RBucketAsync<String> bucket = batch.getBucket(KEY_PREFIX + i, StringCodec.INSTANCE);
            RFuture<String> rFuture = bucket.getAsync();
            rFuture.whenComplete(new BiConsumer<String, Throwable>() {
                @Override
                public void accept(String s, Throwable throwable) {
                    //这些是无法生效的
                    if (s != null) {
                        String indexStr = s.replace(VALUE_PREFIX, KEY_PREFIX);
                        if (Integer.parseInt(indexStr) % 2 != 0) {
                            bucket.setAsync(s + "+++");
                        }
                    }
                }
            });
        }

        BatchResult<?> result = batch.execute();
        List<?> responses = result.getResponses();

        Assert.assertEquals(SIZE, responses.size());
        Assert.assertNotEquals(VALUE_PREFIX + 5 + "+++", responses.get(5));
        Assert.assertEquals(VALUE_PREFIX + 5, responses.get(5));
    }

    /**
     * 判断key不存在则插入，存在的不处理
     */
    @Test
    public void testAbsentThenModify() {
        //prepare
        redissonClient.getKeys().deleteByPattern(KEY_PREFIX + "*");
        //init
        testBatchWrite();

        //beginTest
        RBatch batch = redissonClient.createBatch();
        for (int i = 0; i < SIZE * 2; i++) {
            RBucketAsync<String> bucket = batch.getBucket(KEY_PREFIX + i, StringCodec.INSTANCE);
            //修改生效
            bucket.setIfAbsentAsync("newValue:" + i);
        }

        BatchResult<?> batchResult = batch.execute();
        List<?> responses = batchResult.getResponses();

        Assert.assertEquals(SIZE * 2, responses.size());

        //check
        for (int i = 0; i < SIZE; i++) {
            RBucket<String> actual = redissonClient.getBucket(KEY_PREFIX + i, StringCodec.INSTANCE);
            Assert.assertEquals(VALUE_PREFIX + i, actual.get());
        }
        for (int i = SIZE; i < SIZE * 2; i++) {
            RBucket<String> actual = redissonClient.getBucket(KEY_PREFIX + i, StringCodec.INSTANCE);
            Assert.assertEquals("newValue:" + i, actual.get());
        }
    }
}
