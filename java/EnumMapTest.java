package com.njust;

import org.junit.Assert;
import org.junit.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class EnumMapTest {

    @Test
    public void testHashMapWithEnumKey() {
        Map<SexEnum, Integer> sexToCountMap = new HashMap<>();
        sexToCountMap.put(SexEnum.MALE, 20);
        sexToCountMap.put(SexEnum.FEMALE, 10);

        Assert.assertEquals(2, sexToCountMap.size());
        Assert.assertEquals(20, (int) sexToCountMap.get(SexEnum.MALE));
    }

    /**
     * 当HashMap的key是枚举时，建议使用EnumMap，性能更好
     */
    @Test
    public void testEnumMapWithEnumKey() {
        EnumMap<SexEnum, Integer> sexToCountMap = new EnumMap<>(SexEnum.class);
        sexToCountMap.put(SexEnum.MALE, 20);
        sexToCountMap.put(SexEnum.FEMALE, 10);

        Assert.assertEquals(2, sexToCountMap.size());
        Assert.assertEquals(20, (int) sexToCountMap.get(SexEnum.MALE));
    }

    private enum SexEnum {
        MALE,
        FEMALE
    }
}
