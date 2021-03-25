package com.njust.test;

import java.util.Objects;

import org.junit.Test;

public class CloneDemo
{
    
    /**
     * 实现克隆需要：
     * 1. 实现cloneable接口
     * 2. override方法clone
     * @throws CloneNotSupportedException
     */
    @Test
    public void test()
        throws CloneNotSupportedException
    {
        Book book = new Book(1);
        Book book2 = (Book)book.clone();
        System.out.println(book.equals(book2));
    }
    
    static class Book implements Cloneable
    {
        private int price;
        
        public Book(int price)
        {
            this.price = price;
        }
        
        @Override
        protected Object clone()
            throws CloneNotSupportedException
        {
            return super.clone();
        }
        
        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }
            Book book = (Book)o;
            return price == book.price;
        }
        
        @Override
        public int hashCode()
        {
            return Objects.hash(price);
        }
    }
}
