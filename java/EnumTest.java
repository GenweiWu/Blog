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
        //Assert.assertTrue(TypeEnum.valueOf("aaa")== TypeEnum.AAA);
        Assert.assertSame(TypeEnum.valueOf("aaa"), TypeEnum.AAA);
        Assert.assertSame(TypeEnum.valueOf("bbb"), TypeEnum.BBB);
    }

    @Test
    public void testBasic2()
    {
        //enum.valueOf方法必须使用enum变量本身的定义，不是属性detail，而且大小写都必须一样
        Assert.assertSame(TypeEnum.valueOf("AAA"), TypeEnum.AAA);
        Assert.assertSame(TypeEnum.valueOf("BBB"), TypeEnum.BBB);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqual01()
    {
        Assert.assertSame(TypeEnum.valueOf("--aa"), TypeEnum.AAA);
        Assert.assertSame(TypeEnum.valueOf("--bb"), TypeEnum.BBB);
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
        Assert.assertSame(TypeEnum.from("--aa"), TypeEnum.AAA);
        Assert.assertSame(TypeEnum.from("--bb"), TypeEnum.BBB);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegal01()
    {
        TypeEnum.valueOf("***");
    }

    @Test
    public void illegal02()
    {
        Assert.assertNull(TypeEnum.from("***"));
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
