package com.njust.redisson;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;

import java.util.Collection;

public class RedissonSortedSetTest {
    private static RedissonClient redissonClient;

    @BeforeClass
    public static void init() {
        redissonClient = Redisson.create();
    }

    @AfterClass
    public static void teardown() {
        redissonClient.shutdown();
    }

    /**
     * SortedSet实际上底层是List,一个命令是多个命令的拼凑；
     * 个人不建议使用
     */
    @Test
    public void testSortedSet() {
        RSortedSet<Integer> sortedSet = redissonClient.getSortedSet("test:set:sortedset", IntegerCodec.INSTANCE);
        sortedSet.clear();

        //add==GET+LINDEX+EVAL+DEL
        sortedSet.add(10);
        sortedSet.add(1);
        sortedSet.add(5);

        //size=LLEN
        Assert.assertEquals(3, sortedSet.size());

        //LRANGE
        Collection<Integer> integers = sortedSet.readAll();
        System.out.println(integers);
    }
}
