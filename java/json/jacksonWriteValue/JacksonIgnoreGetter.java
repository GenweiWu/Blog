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

        System.out.println("realPwd:" + project);
    }

}

//console: 可以看出，json格式化后的确看不到真实密码了，但是同时直接获取对象也是不对的了
//{
//    "name" : "test",
//    "startDate" : 1531221386945,
//    "password" : "password",
//    "formatedStartDate" : "2018-07-10 19:16:26"
//    }
//    realPwd:Project(name=test, startDate=Tue Jul 10 19:16:26 CST 2018, password=password)


//正确的避免前台展示密码，同时后台正确读取密码的方法(以为入库等操作，框架处理会自动调用get方法)
//{
//    "name" : "test",
//    "startDate" : 1531221582727,
//    "formatedStartDate" : "2018-07-10 19:19:42"
//    }
//    realPwd:Project(name=test, startDate=Tue Jul 10 19:19:42 CST 2018, password=admin123)
