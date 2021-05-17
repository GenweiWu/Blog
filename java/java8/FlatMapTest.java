package com.njust.test.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import lombok.Getter;

/**
 * 1、flatMap的作用就是，多个list集合转换成一个list
 * 2、flatMap与map的区别：
 * - map是映射
 * - flatmap 是映射+扁平化
 * 3、flatMap还可以一对多转换
 */
public class FlatMapTest
{
    /**
     * 1. flatMap扁平化处理，可以合并多个list/数组
     */
    @Test
    public void test01()
    {
        List<String> list1 = Arrays.asList("1", "2", "3");
        List<String> list2 = Arrays.asList("a", "b", "c");
        
        List<List<String>> listList = Stream.of(list1, list2).collect(Collectors.toList());
        System.out.println(listList); //[[1, 2, 3], [a, b, c]]
        
        //flatMap会进行扁平化处理
        List<Object> collect = Stream.of(list1, list2).flatMap(l -> l.stream()).collect(Collectors.toList());
        System.out.println(collect);  //[1, 2, 3, a, b, c]
    }
    
    /**
     * 2.flatMap扁平化处理，可以合并多个对象的list属性
     */
    @Test
    public void test02()
    {
        List<User> userList = new ArrayList<>();
        
        User user = new User("dave");
        user.setHobby("aaa", "bbb", "ccc");
        userList.add(user);
        
        User user2 = new User("smith");
        user2.setHobby("111", "222", "ccc");
        userList.add(user2);
        
        //get all hobby
        //方法1
        List<String> allHobby = new ArrayList<>();
        userList.forEach(u -> allHobby.addAll(u.getHobby()));
        System.out.println(allHobby); //[aaa, bbb, ccc, 111, 222, ccc]
        
        //方法2
        List<String> allHobby222 = userList.stream().flatMap(h -> h.getHobby().stream()).collect(Collectors.toList());
        System.out.println(allHobby222); //[aaa, bbb, ccc, 111, 222, ccc]
        
        //如果要进行唯一化处理，则flatMap+distinct
        List<String> allHobby333 =
            userList.stream().flatMap(h -> h.getHobby().stream()).distinct().collect(Collectors.toList());
        System.out.println(allHobby333); //[aaa, bbb, ccc, 111, 222]
    }
    
    /**
     * 3、另，flatMap也可实现一对多映射
     */
    @Test
    public void test03()
    {
        List<Integer> collect = Stream.of(1, 2, 3).collect(Collectors.toList());
        System.out.println(collect);    //[1, 2, 3]
        
        //使用map实现1对1映射
        List<String> collect1 = Stream.of(1, 2, 3).map(n -> "Age is:" + n).collect(Collectors.toList());
        System.out.println(collect1);    //[Age is:1, Age is:2, Age is:3]
        
        //使用flatMap能实现一对多映射
        List<String> collect2 =
            Stream.of(1, 2, 3).flatMap(n -> Stream.of("A" + n, "B" + n)).collect(Collectors.toList());
        System.out.println(collect2);    //[A1, B1, A2, B2, A3, B3]
    }
    
    /**
     * 4、这里演示了，扁平化 + 一对多映射
     */
    @Test
    public void test04()
    {
        List<User> userList = new ArrayList<>();
        
        User user = new User("dave");
        user.setHobby("aaa", "bbb", "ccc");
        userList.add(user);
        
        User user2 = new User("smith");
        user2.setHobby("111", "222", "ccc");
        userList.add(user2);
        
        List<String> collect = userList.stream()
            .flatMap(u -> u.getHobby().stream())
            .flatMap(u -> Stream.of("*" + u))
            .collect(Collectors.toList()); //[*aaa, *bbb, *ccc, *111, *222, *ccc]
        System.out.println(collect);
    }
    
    class User
    {
        private String name;
        
        @Getter
        private List<String> hobby;
        
        public void setHobby(String... hobby)
        {
            this.hobby = Arrays.asList(hobby);
        }
        
        public User(String name)
        {
            this.name = name;
        }
    }
}
