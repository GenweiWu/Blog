package com.njust.test.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import lombok.Getter;

/**
 * flatMap的作用就是，多个list集合转换成一个list
 */
public class FlatMapTest
{
    @Test
    public void test01()
    {
        List<String> list1 = Arrays.asList("1", "2", "3");
        List<String> list2 = Arrays.asList("a", "b", "c");
        
        List<List<String>> listList = Stream.of(list1, list2).collect(Collectors.toList());
        System.out.println(listList); //[[1, 2, 3], [a, b, c]]
        
        List<Object> collect = listList.stream().flatMap(l -> l.stream()).collect(Collectors.toList());
        System.out.println(collect);  //[1, 2, 3, a, b, c]
    }
    
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
        
        List<String> allHobby333 =
            userList.stream().flatMap(h -> h.getHobby().stream()).distinct().collect(Collectors.toList());
        System.out.println(allHobby333); //[aaa, bbb, ccc, 111, 222]
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
