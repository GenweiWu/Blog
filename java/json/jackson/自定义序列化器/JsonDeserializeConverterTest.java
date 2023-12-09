package com.njust.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class JsonDeserializeConverterTest {

    @Getter
    private static class Person {
        @JsonDeserialize(converter = LowercaseConverter.class)
        private String name;
    }

    private static class LowercaseConverter extends StdConverter<String, String> {

        @Override
        public String convert(String value) {
            return value.toLowerCase(Locale.ENGLISH);
        }
    }

    @Test
    public void testConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Person person = objectMapper.readValue("{\"name\":\"ZhangSan\"}", Person.class);
            Assert.assertEquals("zhangsan", person.getName());
        } catch (Exception e) {
            Assert.fail();
        }

    }
}
