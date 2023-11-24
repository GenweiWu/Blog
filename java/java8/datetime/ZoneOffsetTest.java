package com.njust.test.java8;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;

/**
 * ZoneOffset表示时区的偏移量
 */
public class ZoneOffsetTest {

    /**
     * 如何创建ZoneOffset
     */
    @Test
    public void test01() {
        //1.会使用jvm的默认时区
        OffsetDateTime now = OffsetDateTime.now();
        ZoneOffset offset = now.getOffset();

        System.out.println(now);
        System.out.println(offset);
        //2.默认时区也可以这样获得
        System.out.println(ZoneId.systemDefault());
        //2023-11-24T15:51:27.449+08:00
        //+08:00
        //Asia/Shanghai

        //3.创建方式
        ZoneOffset zoneOffset = ZoneOffset.ofHours(+8);
        ZoneOffset zoneOffset1 = ZoneOffset.ofHoursMinutes(+8, 0);
        ZoneOffset zoneOffset2 = ZoneOffset.ofHoursMinutesSeconds(+8, 0, 0);
        ZoneOffset zoneOffset3 = ZoneOffset.ofTotalSeconds(8 * 60 * 60);
        Assert.assertEquals(zoneOffset, zoneOffset1);
        Assert.assertEquals(zoneOffset, zoneOffset2);
        Assert.assertEquals(zoneOffset, zoneOffset3);
    }

    /**
     * 获取偏移秒数
     */
    @Test
    public void test02() {
        ZoneOffset offset = OffsetDateTime.now().getOffset();

        //1.获取偏移秒数
        int totalSeconds = offset.getTotalSeconds();
        //assume we are in GMT+8
        Assert.assertEquals(8 * 60 * 60, totalSeconds);
    }

    /**
     * ZoneOffset和ZoneId的转换
     */
    @Test
    public void test03() {
        //1. ZoneOffset --> ZoneId
        ZoneOffset zoneOffset = ZoneOffset.of("-8");
        ZoneId zoneId = ZoneId.from(zoneOffset);
        Assert.assertEquals(ZoneId.of("-08:00"), zoneId);

        //2.ZoneId --> ZoneOffset
        //注意：由于夏令时的存在，不同的instant可能返回不同的offset
        ZoneId zoneId1 = ZoneId.systemDefault();
        Instant now = Instant.now();
        ZoneOffset offset = zoneId1.getRules().getOffset(now);
        Assert.assertEquals(ZoneOffset.of("+08:00"), offset);
    }

    /**
     * OffsetDateTime和ZoneOffset的使用
     * <p>
     * OffsetDateTime使用ZoneOffset
     */
    @Test
    public void test04() {
        ZoneOffset zoneOffset1 = ZoneOffset.ofHours(+8);
        OffsetDateTime offsetDateTime1 = LocalDateTime.now().atOffset(zoneOffset1);

        ZoneOffset zoneOffset2 = ZoneOffset.UTC;
        OffsetDateTime offsetDateTime2 = LocalDateTime.now().atOffset(zoneOffset2);

        System.out.println(offsetDateTime1);
        System.out.println(offsetDateTime2);
        //2023-11-24T16:00:49.811+08:00
        //2023-11-24T16:00:49.812Z
    }

    /**
     * ZoneDateTime和ZoneOffset的使用
     * <p>
     * ZonedDateTime使用ZoneId
     */
    @Test
    public void test05() {
        ZoneId zoneId = ZoneId.of("+8");
        ZonedDateTime zonedDateTime1 = LocalDateTime.now().atZone(zoneId);

        ZoneId zoneId2 = ZoneId.of("UTC");
        ZonedDateTime zonedDateTime2 = LocalDateTime.now().atZone(zoneId2);

        System.out.println(zonedDateTime1);
        System.out.println(zonedDateTime2);
        //2023-11-24T16:38:16.743+08:00
        //2023-11-24T16:38:16.744Z[UTC]
    }

}
