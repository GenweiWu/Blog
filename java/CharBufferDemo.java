package com.njust.test;

import java.nio.CharBuffer;

import org.junit.Assert;
import org.junit.Test;

public class CharBufferDemo
{
    @Test
    public void test_wrap()
    {
        char[] chars = "HelloWorld".toCharArray();
        
        // warp方法
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        charBuffer.append("xxx");
        
        //修改charBuffer会导致原始char数组也被修改
        char[] expect = "xxxloWorld".toCharArray();
        Assert.assertArrayEquals(expect, chars);
        Assert.assertArrayEquals(expect, charBuffer.array());
    }
    
    @Test
    public void test_duplicate()
    {
        char[] chars = "HelloWorld".toCharArray();
        // duplicate方法
        CharBuffer charBuffer = CharBuffer.wrap(chars).duplicate();
        charBuffer.append("123");
        
        //
        char[] expect = "123loWorld".toCharArray();
        Assert.assertArrayEquals(expect, chars);
        Assert.assertArrayEquals(expect, charBuffer.array());
    }
    
    /**
     * 建议返回只读的CharBuffer
     */
    @Test
    public void test_readonly()
    {
        char[] chars = "HelloWorld".toCharArray();
        CharBuffer charBuffer = CharBuffer.wrap(chars).asReadOnlyBuffer();
        //java.nio.ReadOnlyBufferException
        //charBuffer.append("123");
    }
}
