package com.njust.test.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.Data;

/**
 * 分组和分区
 */
public class GroupByTest
{
    /**
     * 分组
     */
    @Test
    public void groupByTest()
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("book111", 20, "math"));
        bookList.add(new Book("book222", 40, "paint"));
        bookList.add(new Book("book333", 15, "math"));

        //一般groupBy
        Map<String, List<Book>> type2booksMap = bookList.stream().collect(Collectors.groupingBy(Book::getType));
        System.out.println(type2booksMap);
        //{paint=[GroupByTest.Book(name=book222, price=40, type=paint)], math=[GroupByTest.Book(name=book111, price=20, type=math), GroupByTest.Book(name=book333, price=15, type=math)]}

        //groupBy后再次mapping
        Map<String, Set<String>> type2nameMap = bookList.stream()
            .collect(Collectors.groupingBy(Book::getType, Collectors.mapping(Book::getName, Collectors.toSet())));
        System.out.println(type2nameMap);
        //{paint=[book222], math=[book333, book111]}
    }

    /**
     * 分区：特殊的分组，对于制定的条件，一个true分组一个false分组
     */
    @Test
    public void partitionByTest()
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("book111", 20, "math"));
        bookList.add(new Book("book222", 40, "paint"));
        bookList.add(new Book("book333", 15, "math"));

        Map<Boolean, List<Book>> expensiveBookPartition =
            bookList.stream().collect(Collectors.partitioningBy(b -> b.getPrice() > 25));
        System.out.println(expensiveBookPartition.get(true));
        //[GroupByTest.Book(name=book222, price=40, type=paint)]
        System.out.println(expensiveBookPartition.get(false));
        //[GroupByTest.Book(name=book111, price=20, type=math), GroupByTest.Book(name=book333, price=15, type=math)]
    }

    @Data
    class Book
    {
        private String name;

        private int price;

        private String type;

        public Book(String bookName, int bookPrice, String bookType)
        {
            this.name = bookName;
            this.price = bookPrice;
            this.type = bookType;
        }
    }
}
