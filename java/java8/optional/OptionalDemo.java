package com.njust.test.java8;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class OptionalDemo
{
    /**
     * 一、创建Optional的3种方法
     */
    @Test
    public void test_createOptional()
    {
        //1.Optional.empty()
        Optional<Object> empty = Optional.empty();
        Assert.assertFalse(empty.isPresent());
        
        //2.Optional.of(T t)，传入null的话会抛空指针
        int actual = Optional.of(12).get();
        Assert.assertEquals(12, actual);
        //Optional<Object> optional = Optional.of(null);
        
        //3.Optional.ofNullable，如果传入null会返回Optional.empty
        Optional<String> stringOptional = Optional.ofNullable("xxx");
        Assert.assertTrue(stringOptional.isPresent());
        Assert.assertEquals("xxx", stringOptional.get());
        
        Optional<Object> objectOptional = Optional.ofNullable(null);
        Assert.assertEquals(Optional.empty(), objectOptional);
    }
    
    /**
     * 二、检查值是否存在
     */
    @Test
    public void test02()
    {
        //2.1 isPresent
        Assert.assertTrue(Optional.of("123").isPresent());
        
        //2.2 ifPresent,then
        Optional.of("456").ifPresent(System.out::println);
    }
    
    /**
     * 三、可选操作
     */
    @Test
    public void test03()
    {
        //3.1 orElse,如果不存在则返回默认值
        String actual = Optional.of("123").orElse(null);
        Assert.assertEquals("123", actual);
        
        actual = Optional.of("").orElse(null);
        Assert.assertEquals("", actual);
        
        String nullValue = null;
        actual = Optional.ofNullable(nullValue).orElse("default");
        Assert.assertEquals("default", actual);
        
        //3.2 orElseGet，如果不存在则返回计算的默认值
        //区别在于，仅当不存在时才计算默认值(如果默认值很贵的话)
        actual = Optional.ofNullable(nullValue).orElseGet(() -> "default222");
        Assert.assertEquals("default222", actual);
        
        //3.3 get
        actual = Optional.of("").get();
        Assert.assertEquals("", actual);
        
        //java.util.NoSuchElementException: No value present
        //actual = Optional.ofNullable(nullValue).get();
                
        Optional.ofNullable(nullValue).ifPresent(x -> {
            System.out.println("this is:" + x);
        });
    }
    
    @Test
    public void test03_b()
    {
        Heavy heavy = null;
        Heavy heavy1 = Optional.ofNullable(heavy).orElse(new Heavy("default111"));
        Heavy heavy2 = Optional.ofNullable(heavy).orElseGet(() -> new Heavy("default222"));
        
        heavy = new Heavy();
        heavy1 = Optional.ofNullable(heavy).orElse(new Heavy("default333"));
        //注意：444并没有new出来
        heavy2 = Optional.ofNullable(heavy).orElseGet(() -> new Heavy("default444"));
        
        //        -------- heavy:default111
        //        -------- heavy:default222
        //        -------- heavy:default333
    }
}

class Heavy
{
    String name = "default";
    
    public Heavy()
    {
    }
    
    public Heavy(String name)
    {
        System.out.println("-------- heavy:" + name);
    }
}
