package com.njust.test.java21;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Java21Test {

    @Test
    public void test_var() {
        //JDK 8
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        //JDK 10+ -使用var
        var list2 = new ArrayList<String>();
        var map2 = new HashMap<String, Integer>();
        var msg = "hello";
    }

    @Test
    public void test_switch() {
        //JDK 8
        assertEquals(test_switch_8(1), test_switch_21(1));
        assertEquals(test_switch_8(2), test_switch_21(2));
        assertEquals(test_switch_8(3), test_switch_21(3));
    }

    private int test_switch_8(int day) {
        int dayValue;
        switch (day) {
            case 1:
                dayValue = 1;
                break;
            case 2:
                dayValue = 2;
                break;
            default:
                dayValue = 0;
        }
        return dayValue;
    }

    private int test_switch_21(int day) {
        return switch (day) {
            case 1 -> 1;
            case 2 -> 2;
            default -> 0;
        };
    }

    /**
     * Text Block
     */
    @Test
    public void test_text() throws JsonProcessingException {
        //JDK 8
        String json = "{" +
                "\"name\":\"zhangsan\"," +
                "\"age\":30" +
                "}";

        //JDk 15+
        String json2 = """
                {
                    "name":"zhangsan",
                    "age": 30
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        JsonNode jsonNode2 = objectMapper.readTree(json2);
        assertEquals(jsonNode.get("name").asText(), jsonNode2.get("name").asText());
        assertEquals(jsonNode.get("age").asText(), jsonNode2.get("age").asText());
    }

    @Test
    public void test_records() {
        Person person = new Person();
        person.setName("dave");
        person.setAge(10);

        Person2 person2 = new Person2("dave", 10);
        assertEquals(person.getName(), person2.name());
        assertEquals(person.getAge(), person2.age());
    }

    @Getter
    @Setter
    @ToString
    static class Person {
        private String name;
        private int age;
    }

    /**
     * <ul>
     *     <li>只有一个构造函数</li>
     *     <li>没有set方法</li>
     * </ul>
     */
    static record Person2(String name, int age) {
        // 自动生成 Person2(String name, int age) 构造器
        // 自动生成 name() 和 age() 访问器方法
        // 自动生成 equals(), hashCode() 和 toString()
    }

    @Test
    public void test_instanceOf() {
        Object obj = "str";

        //JDK 8
        if (obj instanceof String) {
            String msg = (String) obj;
            assertFalse(msg.isBlank());
        }

        //JDK 16+
        if (obj instanceof String msg) {
            assertFalse(msg.isBlank());
        }
    }
}
