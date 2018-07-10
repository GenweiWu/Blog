package com.njust.test.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
     * 3.避免密码泄露，json格式化时不打印，但是入库等操作时还说正确读取的
     *
     * @return
     */
    @JsonIgnore
    public String getPassword()
    {
        return this.password;
    }

}
