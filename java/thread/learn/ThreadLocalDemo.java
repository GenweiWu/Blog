package com.njust.test.learn;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ThreadLocal
 * <pre>
 *
 * </pre>
 */
public class ThreadLocalDemo
{
    //private static Map<Thread, Integer> dataMap = new HashMap<>();
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    
    public static void main(String[] args)
    {
        for (int i = 0; i < 2; i++)
        {
            new Thread(() -> {
                int data = new Random().nextInt();
                //simple:1
                threadLocal.set(data);
                System.out.println(Thread.currentThread().getName() + " put data:" + data);
                
                //complicated:2
                Student instance = StudentHolder.getInstance();
                instance.setName("Name" + data);
                instance.setAge(data);
                
                new A().get();
                new B().get();
            }).start();
        }
    }
    
    static class A
    {
        public void get()
        {
            //1
            Integer data = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + " A read data:" + data);
            
            //2
            Student instance = StudentHolder.getInstance();
            System.out.println(Thread.currentThread().getName() + " A read student:" + instance);
        }
    }
    
    static class B
    {
        public void get()
        {
            //1
            Integer data = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + " B read data:" + data);
            
            //2
            Student instance = StudentHolder.getInstance();
            System.out.println(Thread.currentThread().getName() + " B read student:" + instance);
        }
    }
    
}

class StudentHolder
{
    private static ThreadLocal<Student> studentThreadLocal = new ThreadLocal<>();
    
    /*
    * 或者这样写，就不会出现 studentThreadLocal.get() == null的情况
    private static ThreadLocal<Student> studentThreadLocal = new ThreadLocal<Student>()
    {
        @Override
        protected Student initialValue()
        {
            return new Student();
        }
    };
    */
    
    public static Student getInstance()
    {
        Student student = studentThreadLocal.get();
        if (student == null)
        {
            student = new Student();
            studentThreadLocal.set(student);
        }
        return student;
    }
}

@Getter
@Setter
@ToString
class Student
{
    private String name;
    
    private int age;
}



