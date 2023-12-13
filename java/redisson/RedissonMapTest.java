package com.njust.redisson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;

import java.util.HashMap;
import java.util.Map;

public class RedissonMapTest {

    private RedissonClient redissonClient;

    @Before
    public void init() {
        redissonClient = Redisson.create();
    }

    @After
    public void teardown() {
        redissonClient.shutdown();
    }

    /**
     * 如果key和value类型不同：
     * 则不好指定codec
     */
    @Test
    public void mapTest() {
        //RMap<String, Integer> map = redissonClient.getMap("test:map:map");
        RMap<String, Integer> map = redissonClient.getMap("test:map:map", new TypedJsonJacksonCodec(
                new TypeReference<String>() {
                },
                new TypeReference<Integer>() {
                }
        ));
        map.clear();

        //put =HGET+HSET
        map.put("str", 11);
        //fastPut =HSET
        map.fastPut("str2", 22);

        Assert.assertEquals(2, map.size());

        int count = map.getOrDefault("str", -1);
        Assert.assertEquals(11, count);
        count = map.getOrDefault("str2", -1);
        Assert.assertEquals(22, count);

        //putAll =HMSET
        Map<String, Integer> dataMap = new HashMap<>();
        dataMap.put("text3", 33);
        dataMap.put("text4", 44);
        map.putAll(dataMap);

        Map<String, Integer> stringIntegerMap = map.readAllMap();
        Assert.assertEquals(4, stringIntegerMap.size());
    }

    /**
     * 不指定codec也可以，只是客户端看的时候有点乱码
     */
    @Test
    public void jsonMapTest() throws JsonProcessingException {
        RMap<String, Object> map = redissonClient.getMap("test:map:json", new TypedJsonJacksonCodec(
                new TypeReference<String>() {
                },
                new TypeReference<Object>() {
                }
        ));
        map.clear();

        //put
        map.fastPut("keyForStr", "text");
        map.fastPut("keyForInt", 123);

        Person person = new Person(100, "ZhangSan");
        String json = new ObjectMapper().writeValueAsString(person);
        map.fastPut("detail", json);

        //
        Assert.assertEquals("text", map.get("keyForStr"));
        Assert.assertEquals(123, map.get("keyForInt"));

        String detail = (String) map.get("detail");
        Person actual = new ObjectMapper().readValue(detail, Person.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(100, person.getId());
        Assert.assertEquals("ZhangSan", person.getName());

        //each
        Map<String, Object> stringIntegerMap = map.readAllMap();
        Assert.assertEquals(3, stringIntegerMap.size());
    }

    @Getter
    @Setter
    private static class Person {
        private int id;
        private String name;

        public Person() {
        }

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
