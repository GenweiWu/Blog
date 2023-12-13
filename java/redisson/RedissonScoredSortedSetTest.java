package com.njust.redisson;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.ScoredEntry;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.Collection;

public class RedissonScoredSortedSetTest {
    private static RedissonClient redissonClient;

    @BeforeClass
    public static void init() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://1.2.3.4:6579");
        singleServerConfig.setPassword("pwd");

        redissonClient = Redisson.create(config);
    }

    @AfterClass
    public static void teardown() {
        redissonClient.shutdown();
    }

    @Test
    public void testScoredSortedSet() {
        RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet("test:scoredSortedSet", StringCodec.INSTANCE);
        scoredSortedSet.delete();

        //add=ZADD
        scoredSortedSet.add(3.3, "apple");
        scoredSortedSet.add(6.6, "banana");
        scoredSortedSet.add(4.4, "orange");

        //size=ZCARD
        Assert.assertEquals(3, scoredSortedSet.size());

        //count=ZCOUNT
        int count = scoredSortedSet.count(3.3, true, 6.6, true);
        Assert.assertEquals(3, count);
        count = scoredSortedSet.count(4, true, 6.6, true);
        Assert.assertEquals(2, count);
        count = scoredSortedSet.count(4, true, 6.6, false);
        Assert.assertEquals(1, count);

        //valueRange=ZRANGEBYSCORE
        Collection<String> valueRange1 = scoredSortedSet.valueRange(4, true, 7, true);
        Assert.assertEquals(2, valueRange1.size());
        Assert.assertTrue(valueRange1.contains("orange"));
        Assert.assertTrue(valueRange1.contains("banana"));

        //entryRange=ZRANGEBYSCORE
        Collection<ScoredEntry<String>> scoredEntries = scoredSortedSet.entryRange(4, true, 7, true);
        Assert.assertEquals(2, scoredEntries.size());
        Assert.assertTrue(scoredEntries.contains(new ScoredEntry<>(4.4, "orange")));
        Assert.assertTrue(scoredEntries.contains(new ScoredEntry<>(6.6, "banana")));

        Integer index = scoredSortedSet.rank("orange");
        Assert.assertNotNull(index);
        Assert.assertEquals(1, (int) index);

        index = scoredSortedSet.rank("banana");
        Assert.assertNotNull(index);
        Assert.assertEquals(2, (int) index);

        index = scoredSortedSet.rank("xxx");
        Assert.assertNull(index);
    }

    @Test
    public void testScoredSortedSet2() {
        RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet("test:scoredSortedSet", StringCodec.INSTANCE);
        scoredSortedSet.delete();
        scoredSortedSet.add(4.4, "v1");

        //Requires Redis 6.2.0 and higher.
        scoredSortedSet.addIfGreater(5.5, "v1");
        //Requires Redis 6.2.0 and higher.
        scoredSortedSet.addIfGreater(3.3, "v1");

        Double actualScore = scoredSortedSet.getScore("v1");
        Assert.assertNotNull(actualScore);
        Assert.assertEquals(5.5, actualScore, 0.0000001);
    }
}
