package com.njust.redisson;

import org.junit.Assert;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

/**
 * 测试字符串类型
 */
public class RedissonStrTest {

    @Test
    public void strTest() {
        String key = "test:string:text";

        RedissonClient redissonClient = Redisson.create();
        RBucket<String> bucket = redissonClient.getBucket(key);
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

        RedissonClient redissonClient = Redisson.create();
        RBucket<Integer> bucket = redissonClient.getBucket(key);

        bucket.set(6666);
        int actual = bucket.getAndDelete();
        Assert.assertEquals(6666, actual);
    }

    /**
     * 能用，但是在连接工具中看到的是hex类型
     * <p>\x09\xc8\x01</p>
     */
    @Test
    public void longTest() {
        String key = "test:string:long";

        RedissonClient redissonClient = Redisson.create();
        RBucket<Long> bucket = redissonClient.getBucket(key);

        bucket.set(100L);
        long actual = bucket.getAndDelete();
        Assert.assertEquals(100L, actual);
    }
}
