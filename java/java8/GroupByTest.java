package com.njust.test.java8;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 分组和分区
 */
public class GroupByTest {
    /**
     * 分组
     */
    @Test
    public void groupByTest() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("book111", 20, "math"));
        bookList.add(new Book("book222", 40, "paint"));
        bookList.add(new Book("book333", 15, "math"));

        //1.一般groupBy
        Map<String, List<Book>> type2booksMap = bookList.stream().collect(Collectors.groupingBy(Book::getType));
        String expect = "{paint=[GroupByTest.Book(name=book222, price=40, type=paint)], math=[GroupByTest.Book(name=book111, price=20, type=math), GroupByTest.Book(name=book333, price=15, type=math)]}";
        Assert.assertEquals(expect, type2booksMap.toString());

        //2.groupBy后再次mapping
        Map<String, Set<String>> type2nameMap = bookList.stream()
                .collect(Collectors.groupingBy(Book::getType, Collectors.mapping(Book::getName, Collectors.toSet())));
        expect = "{paint=[book222], math=[book333, book111]}";
        Assert.assertEquals(expect, type2nameMap.toString());

        //3.计算分组数量: 计算每种类型的书籍的数量
        Map<String, Long> type2countMap = bookList.stream().collect(Collectors.groupingBy(Book::getType, Collectors.counting()));
        expect = "{paint=1, math=2}";
        Assert.assertEquals(expect, type2countMap.toString());


        //4.分组后计算某个属性：计算每种类型的书籍的总价格
        Map<String, Integer> type2priceMap = bookList.stream().collect(Collectors.groupingBy(Book::getType, Collectors.summingInt(Book::getPrice)));
        expect = "{paint=40, math=35}";
        Assert.assertEquals(expect, type2priceMap.toString());

        //5.分组后join
        Map<String, String> type2bookStrMap = bookList.stream().collect(Collectors.groupingBy(Book::getType, Collectors.mapping(Book::getName, Collectors.joining("---"))));
        expect = "{paint=book222, math=book111---book333}";
        Assert.assertEquals(expect, type2bookStrMap.toString());

        //6.分组后，多维度统计：count|sum|min|max|average
        Map<String, IntSummaryStatistics> statisticsMap = bookList.stream().collect(Collectors.groupingBy(Book::getType, Collectors.summarizingInt(Book::getPrice)));
        expect = "{paint=IntSummaryStatistics{count=1, sum=40, min=40, average=40.000000, max=40}, math=IntSummaryStatistics{count=2, sum=35, min=15, average=17.500000, max=20}}";
        Assert.assertEquals(expect, statisticsMap.toString());


        //7.1 分组后,reduce
        //[1]初始值;  [2]从Book得到price;  [3]如何处理两个price;
        Map<String, Integer> type2sumMap = bookList.stream().collect(
                Collectors.groupingBy(Book::getType,
                        Collectors.reducing(0, Book::getPrice, (p1, p2) -> p1 + p2)));
        expect = "{paint=40, math=35}";
        Assert.assertEquals(expect, type2sumMap.toString());

        //7.2 分组后reduce2
        //对比7.1, 主要缺少[2]从Book得到price,即只要针对Book本身操作，而不是Book的某个属性
        //缺少[1],缺少初始值，所以返回是Optional类型
        Map<String, Optional<Book>> type2bookMap = bookList.stream().collect(
                Collectors.groupingBy(Book::getType,
                        Collectors.reducing((b1, b2) -> b1.addBook(b2))));
        expect = "{paint=Optional[GroupByTest.Book(name=book222, price=40, type=paint)], math=Optional[GroupByTest.Book(name=book111-book333, price=35, type=math)]}";
        Assert.assertEquals(expect, type2bookMap.toString());

        //7.3 在7.2的基础上加上初始值，此时返回的不是Optional
        Map<String, Book> type2bookMap222 = bookList.stream().collect(
                Collectors.groupingBy(Book::getType,
                        Collectors.reducing(Book.EMPTY, (b1, b2) -> b1.addBook(b2))));
        expect = "{paint=GroupByTest.Book(name=book222, price=40, type=paint), math=GroupByTest.Book(name=book111-book333, price=35, type=math)}";
        Assert.assertEquals(expect, type2bookMap222.toString());
    }

    /**
     * 分区：特殊的分组，对于制定的条件，一个true分组一个false分组
     */
    @Test
    public void partitionByTest() {
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
    static class Book {

        public static final Book EMPTY = new Book();

        private String name;

        private int price;

        private String type;

        private Book() {
        }

        public Book(String bookName, int bookPrice, String bookType) {
            this.name = bookName;
            this.price = bookPrice;
            this.type = bookType;
        }

        /**
         * 为了测试reduce
         */
        public Book addBook(Book another) {
            if (this.type == null) {
                return another;
            }

            assert this.type.equals(another.type);

            String name = this.name + "-" + another.name;
            int price = this.price + another.price;
            return new Book(name, price, type);
        }
    }
}
