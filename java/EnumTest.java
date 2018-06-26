package com.njust.test;

import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import lombok.Getter;

public class EnumTest
{

    @Test(expected = IllegalArgumentException.class)
    public void testBasic()
    {
        Assert.assertEquals(TypeEnum.valueOf("aaa"), TypeEnum.AAA);
        Assert.assertEquals(TypeEnum.valueOf("bbb"), TypeEnum.BBB);
    }

    @Test
    public void testBasic2()
    {
        //enum.valueOf方法必须使用enum变量本身的定义，不是属性detail，而且大小写都必须一样
        Assert.assertEquals(TypeEnum.valueOf("AAA"), TypeEnum.AAA);
        Assert.assertEquals(TypeEnum.valueOf("BBB"), TypeEnum.BBB);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqual01()
    {
        Assert.assertEquals(TypeEnum.valueOf("--aa"), TypeEnum.AAA);
        Assert.assertEquals(TypeEnum.valueOf("--bb"), TypeEnum.BBB);
    }

    @Test
    public void testEqual02()
    {
        //2.判断相等方法1是直接字符串比较；2是自己写方法转换到enum对象
        Assert.assertEquals("--aa", TypeEnum.AAA.getDetail());
        Assert.assertEquals("--bb", TypeEnum.BBB.getDetail());
    }

    @Test
    public void testEqual03()
    {
        Assert.assertEquals(TypeEnum.from("--aa"), TypeEnum.AAA);
        Assert.assertEquals(TypeEnum.from("--bb"), TypeEnum.BBB);
    }

    @Test
    public void illegal01()
    {
        TypeEnum.valueOf("***");
    }

    enum TypeEnum
    {
        AAA("--aa"),
        BBB("--bb");

        @Getter
        private String detail;

        TypeEnum(String detail)
        {
            this.detail = detail;
        }

        public static TypeEnum from(String detail)
        {
            return Stream.of(values()).filter(n -> n.detail.equals(detail)).findFirst().orElse(null);
        }
    }
}
