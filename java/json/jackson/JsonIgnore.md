## JacksonIgnore



### 😄使用建议

1. `JsonIgnore`加到字段上或get方法上或set方法上，效果都一样：会导致序列化和反序列化都失效！
2. 字段上添加JsonProerty，此时在get方法添加JsonIgnore不会生效
3. 使用建议：

```
(1)如果想完全忽略某个字段的序列化/反序列化，则添加JsonIgnore
(2)如果只是想实现ReadOnly或SetOnly则参考使用 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
```





### 测试过程

> JsonIgnore放到get方法，竟然导致set方法也失效了
>
> jackson 2.13.3

```java
package com.njust.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;

/**
 * JsonIgnore放到get方法，竟然导致set方法也失效了(仍然会进行序列化)
 * <pre>
 *     jackson 2.13.3
 * </pre>
 */
public class JsonIgnoreTest {

    @Test
    public void serializeTest() throws JsonProcessingException {

        Student student = new Student();
        student.setId(111);
        student.setName("ZhangSan");

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(student);
        //name不会被序列化
        Assert.assertEquals("{\"id\":111}", str);
        Assert.assertEquals("ZhangSan", student.getName());
    }

    @Test
    public void deserializeTest() throws JsonProcessingException {
        String json = "{\"id\":666,\"name\":\"LiSi\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(json, Student.class);
        System.out.println(student);

        //Assert.assertEquals("LiSi", student.getName());
        Assert.assertNull(student.getName());
    }


    @ToString
    static class Student {
        @Getter
        @Setter
        private int id;

        private String name;

        /**
         * 1. getName方法上添加JsonIgnore，会导致反序列化也失败，即setName也不会被调用了
         */
        @JsonIgnore
        public String getName() {
            return name;
        }

        /**
         * 2.set方法上并没有添加JsonIgnore
         */
        public void setName(String name) {
            this.name = name;
        }
    }
}
```


> 添加JsonProperty到字段上，则JsonIgnore写到get方法上失效了(仍然会进行序列化)

```java
package com.njust.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * 1.JsonProperty加到属性name上
 * 2.JsonIgnore放到getName方法上，但是不加到setName方法上
 * <ul>
 *     效果是:
 *     <li>序列化OK</li>
 *     <li>反序列化OK</li>
 * </ul>
 * <pre>
 *     jackson 2.13.3
 * </pre>
 */
public class JsonIgnoreTest {

    @Test
    public void serializeTest() throws JsonProcessingException {

        Student student = new Student();
        student.setId(111);
        student.setName("ZhangSan");

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(student);
        //name还是会被序列化
        Assert.assertEquals("{\"id\":111,\"name\":\"ZhangSan\"}", str);
        Assert.assertEquals("ZhangSan", student.getName());
    }

    @Test
    public void deserializeTest() throws IOException {
        String json = "{\"id\":666,\"name\":\"LiSi\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(json, Student.class);
        System.out.println(student);

        //name还是会被反序列化
        Assert.assertEquals("LiSi", student.getName());
        //Assert.assertNull(student.getName());
    }


    @ToString
    static class Student {
        @Getter
        @Setter
        private int id;

        /**
         * check-1
         */
        @JsonProperty
        private String name;

        /**
         * check-2
         */
        @JsonIgnore
        public String getName() {
            return name;
        }

        /**
         * check-3
         */
        public void setName(String name) {
            this.name = name;
        }
    }
}
```



>利用@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)实现只写

```java
package com.njust.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * 1.JsonProperty加到属性name上
 * <br>
 * 2.同时添加access = JsonProperty.Access.WRITE_ONLY表示只读
 * <ul>
 *     效果是:
 *     <li>反序列化OK</li>
 *     <li color="orange">序列化不再生效</li>
 * </ul>
 * <pre>
 *     jackson 2.13.3
 * </pre>
 */
public class JsonIgnoreTest {

    @Test
    public void serializeTest() throws JsonProcessingException {

        Student student = new Student();
        student.setId(111);
        student.setName("ZhangSan");

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(student);
        //name不会被序列化
        Assert.assertEquals("{\"id\":111}", str);
        Assert.assertEquals("ZhangSan", student.getName());
    }

    @Test
    public void deserializeTest() throws IOException {
        String json = "{\"id\":666,\"name\":\"LiSi\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(json, Student.class);
        System.out.println(student);

        //name会被反序列化
        Assert.assertEquals("LiSi", student.getName());
    }


    @ToString
    static class Student {
        @Getter
        @Setter
        private int id;

        /**
         * check-1
         */
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
```



> 把@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)从字段上，移到get方法上，效果是一样的

```java
package com.njust.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * 1.JsonProperty加到属性name上
 * <br>
 * 2.同时添加access = JsonProperty.Access.WRITE_ONLY表示只读
 * <ul>
 *     效果是:
 *     <li>反序列化OK</li>
 *     <li color="orange">序列化不再生效</li>
 * </ul>
 * <br>
 * <p color="red">
 * 3.从字段上移到get或set方法上也一样
 * </p>
 * <pre>
 *     jackson 2.13.3
 * </pre>
 */
public class JsonIgnoreTest {

    @Test
    public void serializeTest() throws JsonProcessingException {

        Student student = new Student();
        student.setId(111);
        student.setName("ZhangSan");

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(student);
        //name不会被序列化
        Assert.assertEquals("{\"id\":111}", str);
        Assert.assertEquals("ZhangSan", student.getName());
    }

    @Test
    public void deserializeTest() throws IOException {
        String json = "{\"id\":666,\"name\":\"LiSi\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(json, Student.class);
        System.out.println(student);

        //name会被反序列化
        Assert.assertEquals("LiSi", student.getName());
    }


    @ToString
    static class Student {
        @Getter
        @Setter
        private int id;

        private String name;

        /**
         * check-1
         */
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
```
