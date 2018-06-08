package com.njust.test.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.Data;

/**
 * 总结：
 * 1.直接toMap需要确保key是唯一的，否则需要制定覆盖策略
 * 2.groupby可以得到 <code>Map<key,List<>>< /code>
 */
public class ToMapTest
{
    @Test
    public void toMap()
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("book111", 20, "math"));
        bookList.add(new Book("book222", 40, "paint"));
        bookList.add(new Book("book333", 15, "math"));

        //总结：1->1的map实现1
        Map<String, Book> name2bookMap = bookList.stream().collect(Collectors.toMap(Book::getName, n -> n));
        System.out.println(name2bookMap);
        //{book333=ToMapTest.Book(name=book333, price=15, type=math), book111=ToMapTest.Book(name=book111, price=20, type=math), book222=ToMapTest.Book(name=book222, price=40, type=paint)}

        //总结：1->n的map
        Map<String, List<Book>> name2bookMap2 = bookList.stream().collect(Collectors.groupingBy(Book::getName));
        System.out.println(name2bookMap2);
        //{book333=[ToMapTest.Book(name=book333, price=15, type=math)], book111=[ToMapTest.Book(name=book111, price=20, type=math)], book222=[ToMapTest.Book(name=book222, price=40, type=paint)]}
    }

    @Test
    public void toMap222()
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("book111", 20, "math"));
        bookList.add(new Book("book222", 40, "paint"));
        bookList.add(new Book("book333", 15, "math"));

        //总结：1->1的map实现1，此时如果key出现重复需要执行覆盖策略，否则报错
        //java.lang.IllegalStateException: Duplicate key ToMapTest.Book(name=book111, price=20, type=math)
        Map<String, Book> name2bookMap =
            bookList.stream().collect(Collectors.toMap(Book::getType, n -> n, (oldOne, newOne) -> newOne));
        System.out.println(name2bookMap);
        //{paint=ToMapTest.Book(name=book222, price=40, type=paint), math=ToMapTest.Book(name=book333, price=15, type=math)}

        //总结：1->n的map
        //这样就没问题
        Map<String, List<Book>> name2bookMap2 = bookList.stream().collect(Collectors.groupingBy(Book::getType));
        System.out.println(name2bookMap2);
        //{paint=[ToMapTest.Book(name=book222, price=40, type=paint)], math=[ToMapTest.Book(name=book111, price=20, type=math), ToMapTest.Book(name=book333, price=15, type=math)]}
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

