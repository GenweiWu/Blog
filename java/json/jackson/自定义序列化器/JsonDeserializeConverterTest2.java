package com.njust.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JsonDeserializeConverterTest2 {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Getter
    @Setter
    private static class Person {

        /**
         * 1.默认是0时区
         */
        @JsonFormat(pattern = DATE_FORMAT)
        private Date createTime;

        /**
         * 2.这里想用服务器时区
         */
        @JsonDeserialize(converter = TimeConverter.class)
        private Date updateTime;
    }

    private static class TimeConverter extends StdConverter<String, Date> {

        @Override
        public Date convert(String dateTimeStr) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
            Date time = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            return time;
        }
    }

    @Test
    public void testConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Person person = objectMapper.readValue("{\"createTime\":\"2023-12-08 16:40:00\",\"updateTime\":\"2023-12-08 16:40:00\"}", Person.class);

            LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 8, 16, 40, 0);
            Date expectCreateTime = Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
            Date expectUpdateTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

            Assert.assertEquals(expectCreateTime, person.getCreateTime());
            Assert.assertEquals(expectUpdateTime, person.getUpdateTime());
        } catch (Exception e) {
            Assert.fail();
        }

    }
}
