package com.njust.redisson;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RSet;
import org.redisson.api.RSetMultimap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

import java.util.Set;

public class RedissonSetTest {

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
    public void testSet() {
        RSet<String> set = redissonClient.getSet("test:set:set", StringCodec.INSTANCE);
        //clear=DEL
        set.clear();

        //add=SADD
        set.add("apple111");
        set.add("banana222");
        set.add("apple333");
        set.add("abandon");
        Assert.assertEquals(4, set.size());  //SCARD

        //contains=SISMEMBER
        Assert.assertTrue(set.contains("abandon"));
        //remove=SREM
        set.remove("abandon");
        Assert.assertFalse(set.contains("abandon"));
        Assert.assertEquals(3, set.size());

        //removeIf=SSCAN+SREM+SREM
        set.removeIf(s -> s.startsWith("apple"));
        Assert.assertEquals(1, set.size());

        //readAll=SMEMBERS
        Set<String> actualSet = set.readAll();
        //SCARD
        Assert.assertFalse(actualSet.isEmpty());
    }

    @Test
    public void testSetUnique() {
        RSet<String> set = redissonClient.getSet("test:set:set", StringCodec.INSTANCE);
        set.clear();

        set.add("XXX");
        set.add("XXX");
        set.add("XXX");
        Assert.assertEquals(1, set.size());
    }

    /**
     * <<ul>
     * <li>
     * {@code RMutiMap<String,String>}这样的泛型，实际上是的效果类似于{@code Map<String,List<String>>}
     * </li>
     * <li>
     * 基于列表 {@code List} 的多值映射 {@code RListMultimap}，它是在保持插入顺序的同时允许一个字段下包含重复的元素
     * </li>
     * <li>
     * 基于集合 {@code Set} 的多值映射 {@code RSetMultimap}，它是不允许一个字段值包含有重复的元素
     * </li>
     * </ul>
     */
    @Test
    public void testSetMultimap() {
        RSetMultimap<Integer, Integer> setMultimap = redissonClient.getSetMultimap("test:set:setMultimap");

        setMultimap.put(1, 101);
        setMultimap.put(1, 102);
        setMultimap.put(1, 102);
        setMultimap.put(2, 102);
        setMultimap.put(2, 103);

        //size=值的个数，不是key的个数
        Assert.assertEquals(4, setMultimap.size());

        Set<Integer> set1 = setMultimap.getAll(1);
        Assert.assertEquals(2, set1.size());
        Set<Integer> set2 = setMultimap.getAll(1);
        Assert.assertEquals(2, set2.size());
    }
}
