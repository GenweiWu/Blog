
## 依赖
```xml
  <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.7</version>
        </dependency>
```

## example
```java
package com.njust.commons;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtilsTest {

    public static final String FORMAT = "yyyyMMdd hh:mm:ss";

    /***
     * 如何获取一天的开始和结束
     */
    @Test
    public void test() throws ParseException {
        Date date = DateUtils.parseDate("20230113 15:36:21", FORMAT);

        //获取一天的开始
        Date startOfToday = DateUtils.truncate(date, Calendar.DATE);
        Assert.assertEquals(DateUtils.parseDate("20230113 00:00:00", FORMAT), startOfToday);

        //获取下一天的开始
        Date startOfTomorrow = DateUtils.ceiling(date, Calendar.DATE);
        Assert.assertEquals(DateUtils.parseDate("20230114 00:00:00", FORMAT), startOfTomorrow);

        //进而得到一天的结束
        Date endOfToday = DateUtils.addSeconds(startOfTomorrow, -1);
        Assert.assertEquals(DateUtils.parseDate("20230113 23:59:59", FORMAT), endOfToday);
    }

    /**
     * 向下取整
     * <p>truncate:截取</p>
     */
    @Test
    public void truncateTest() throws ParseException {
        Date date = DateUtils.parseDate("20230213 15:36:21", FORMAT);

        Date actual = DateUtils.truncate(date, Calendar.YEAR);
        Assert.assertEquals(DateUtils.parseDate("20230101 00:00:00", FORMAT), actual);

        actual = DateUtils.truncate(date, Calendar.MONTH);
        Assert.assertEquals(DateUtils.parseDate("20230201 00:00:00", FORMAT), actual);

        actual = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
        Assert.assertEquals(DateUtils.parseDate("20230213 00:00:00", FORMAT), actual);

        actual = DateUtils.truncate(date, Calendar.HOUR_OF_DAY);
        Assert.assertEquals(DateUtils.parseDate("20230213 15:00:00", FORMAT), actual);

        actual = DateUtils.truncate(date, Calendar.MINUTE);
        Assert.assertEquals(DateUtils.parseDate("20230213 15:36:00", FORMAT), actual);

        actual = DateUtils.truncate(date, Calendar.SECOND);
        Assert.assertEquals(DateUtils.parseDate("20230213 15:36:21", FORMAT), actual);
    }


    /**
     * <p>ceiling:天花板，即向上取整</p>
     */
    @Test
    public void ceilingTest() throws ParseException {
        Date date = DateUtils.parseDate("20230213 15:36:21", FORMAT);

        Date actual = DateUtils.ceiling(date, Calendar.YEAR);
        Assert.assertEquals(DateUtils.parseDate("20240101 00:00:00", FORMAT), actual);

        actual = DateUtils.ceiling(date, Calendar.MONTH);
        Assert.assertEquals(DateUtils.parseDate("20230301 00:00:00", FORMAT), actual);

        actual = DateUtils.ceiling(date, Calendar.DAY_OF_MONTH);
        Assert.assertEquals(DateUtils.parseDate("20230214 00:00:00", FORMAT), actual);

        actual = DateUtils.ceiling(date, Calendar.HOUR_OF_DAY);
        Assert.assertEquals(DateUtils.parseDate("20230213 16:00:00", FORMAT), actual);

        actual = DateUtils.ceiling(date, Calendar.MINUTE);
        Assert.assertEquals(DateUtils.parseDate("20230213 15:37:00", FORMAT), actual);

        actual = DateUtils.ceiling(date, Calendar.SECOND);
        Assert.assertEquals(DateUtils.parseDate("20230213 15:36:22", FORMAT), actual);
    }
}

```
