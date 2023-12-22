package com.njust.redisson;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RedissonKeyTest {
    private static RedissonClient redissonClient;

    @BeforeClass
    public static void init() {
        redissonClient = Redisson.create();

        for (int i = 0; i < 10; i++) {
            RBucket<String> bucket = redissonClient.getBucket("test:key:" + i, StringCodec.INSTANCE);
            bucket.set("Hello" + i);
        }
    }

    @AfterClass
    public static void teardown() {
        redissonClient.shutdown();
    }

    @Test
    public void testGetKeysByPattern() {
        //getKeysByPattern默认每次扫描10个key
        Iterable<String> keys = redissonClient.getKeys().getKeysByPattern("test:key:*");
        keys.forEach(k -> {
            RBucket<String> bucket = redissonClient.getBucket(k, StringCodec.INSTANCE);
            Assert.assertEquals(k.replace("test:key:", "Hello"), bucket.get());
        });
    }

    @Test
    public void testGetKeysStreamByPattern() {
        Stream<String> keysByPatternStream = redissonClient.getKeys().getKeysStreamByPattern("test:key:*");
        Set<String> collect = keysByPatternStream.filter(k -> k.compareTo("test:key:5") > 0).collect(Collectors.toSet());
        Assert.assertEquals(4, collect.size());
    }

    @Test
    public void testKeyWithLimit() {
        //感觉是随机取了几个
        Iterable<String> keysWithLimit = redissonClient.getKeys().getKeysWithLimit("test:key:*", 3);
        int count = 0;
        for (String s : keysWithLimit) {
            System.out.println(s);
            count++;
        }
        Assert.assertEquals(3, count);
    }
}
