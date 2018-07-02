## jackson进行字符串输出

## 一、`writeValueAsString`
1. 提供getter方法才能进行输出
2. 无论字段是否存在，只要有get方法即可打印出来
3. 可以对敏感字段进行特殊处理

### `JacksonIgnoreGetter.java`
```java
package com.njust.test.json;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonIgnoreGetter
{

    public static void main(String[] args)
        throws JsonProcessingException
    {
        Project project = new Project("test", new Date(), "admin123");

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(project));

        System.out.println("realPwd:" + project.getRealPassword());
    }

}
```

### `Project.java`
```java
package com.njust.test.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
public class Project
{
    @Getter
    private String name;

    /**
     * 1.需要提供get方法
     */
    @Getter
    private Date startDate;

    @Getter
    private String password;

    public Project(String name, Date startDate, String password)
    {
        this.name = name;
        this.startDate = startDate;
        this.password = password;
    }

    /**
     * 2.只要提供了get方法，就算没有对应的属性，也会进行json转换
     *
     * @return
     */
    public String getFormatedStartDate()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(startDate);
    }

    /**
     * 3.避免密码泄露，所以复写get方法来避免内容被打印
     *
     * @return
     */
    public String getPassword()
    {
        return "password";
    }

    /**
     * 4.避免get方法就会出现在json后的字段中，进行ignore标识
     *
     * @return
     */
    @JsonIgnore
    public String getRealPassword()
    {
        return this.password;
    }

}

```

### 加和不加ignore的console区别：
```
{
  "name" : "test",
  "startDate" : 1530528481545,
  "password" : "password",
  "formatedStartDate" : "2018-07-02 18:48:01",
  "realPassword" : "admin123"
}
realPwd:admin123


{
  "name" : "test",
  "startDate" : 1530528510570,
  "password" : "password",
  "formatedStartDate" : "2018-07-02 18:48:30"
}
realPwd:admin123
```




