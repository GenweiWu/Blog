package com.njust.test.rawtype;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Main
{
    @Test
    public void test1()
    {
        ////1 对于普通的String.class是可以的
        MyResponse<String> stringMyResponse = RequestUtil.sayHi(String.class);
        stringMyResponse.setBody("sayHi");
        System.out.println(stringMyResponse);
        
        ////2 对于Map<K,V>.class是不行的，无法指定K,V的具体类型
        //rawtype警告
        MyResponse<Map> mapMyResponse = RequestUtil.sayHi(Map.class);
        Map<String, Boolean> body = new HashMap<>();
        body.put("key", true);
        mapMyResponse.setBody(body);
        
        //但是无法指定Map的具体类型
        // 编译报错
        //MyResponse<Map<String, ?>> mapMyResponse = RequestUtil.sayHi(Map<String, ?>.class);
    }
    
    @Test
    public void test2()
    {
        ////1 对于普通的String.class是可以的
        MyResponse<String> stringMyResponse = RequestUtil.sayHello();
        stringMyResponse.setBody("hello");
        System.out.println(stringMyResponse);
        
        ////2 对于Map<K,V>也可以指定K,V的具体类型!!!
        MyResponse<Map<String, Boolean>> mapMyResponse = RequestUtil.sayHello();
        Map<String, Boolean> body = new HashMap<>();
        body.put("key", true);
        mapMyResponse.setBody(body);
        System.out.println(mapMyResponse);
    }
    
}
