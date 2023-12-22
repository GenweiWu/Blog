package com.njust.redisson;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.codec.LongCodec;
import org.redisson.client.codec.StringCodec;

/**
 * 测试字符串类型
 */
public class RedissonStrTest {

    private static RedissonClient redissonClient;

    @BeforeClass
    public static void init() {
        redissonClient = Redisson.create();
    }

    @AfterClass
    public static void teardown() {
        redissonClient.shutdown();
    }

    @Test
    public void strTest() {
        String key = "test:string:text";

        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        Assert.assertFalse(bucket.isExists());

        //对应的key不存在则true
        Assert.assertTrue(bucket.setIfAbsent("string"));
        Assert.assertFalse(bucket.setIfAbsent("string222"));
        Assert.assertEquals("string", bucket.get());

        //对应的key存在且删除成功则true
        Assert.assertTrue(bucket.delete());
        Assert.assertFalse(bucket.isExists());

        Assert.assertFalse(bucket.delete());
    }

    @Test
    public void intTest() {
        String key = "test:string:int";

        RBucket<Integer> bucket = redissonClient.getBucket(key, IntegerCodec.INSTANCE);

        bucket.set(6666);
        int actual = bucket.getAndDelete();
        Assert.assertEquals(6666, actual);
    }

    /**
     * 不指定codec也能用，但是在连接工具中看到的是hex类型
     * <p>\x09\xc8\x01</p>
     */
    @Test
    public void longTest() {
        String key = "test:string:long";

        RBucket<Long> bucket = redissonClient.getBucket(key, LongCodec.INSTANCE);

        bucket.set(100L);
        long actual = bucket.getAndDelete();
        Assert.assertEquals(100L, actual);
    }

    /**
     * setIfAbsent=SETNX
     */
    @Test
    public void testSetIfAbsent() {
        String key = "test:string:absent";

        RBucket<Object> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        bucket.delete();

        Assert.assertFalse(bucket.isExists());
        //t1
        Assert.assertTrue(bucket.setIfAbsent("oldValue"));
        Assert.assertEquals("oldValue", bucket.get());
        //t2
        Assert.assertFalse(bucket.setIfAbsent("newValue"));
        Assert.assertEquals("oldValue", bucket.get());
    }

    /**
     * setIfExists=
     */
    @Test
    public void testSetIfPresent() {
        String key = "test:string:present";

        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        bucket.delete();

        //t1-prepare
        Assert.assertFalse(bucket.isExists());
        //t1-
        Assert.assertFalse(bucket.setIfExists("oldValue"));
        Assert.assertNull(bucket.get());

        //t2-prepare
        bucket.set("middleValue");
        Assert.assertTrue(bucket.isExists());
        //t2
        Assert.assertTrue(bucket.setIfExists("newValue"));
        Assert.assertEquals("newValue", bucket.get());
    }
}
