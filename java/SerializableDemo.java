package com.njust.test.kemu2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.ToString;

@ToString
public class SerializableDemo implements Serializable
{
    //都可以
    public String name = "BBB";
    
    //private可以被序列化，反序列化
    private int age = 90;
    
    //final不可以被反序列化，即不能被修改
    final String hobby = "yyy";
    
    public static void main(String[] args)
        throws IOException, ClassNotFoundException
    {
        SerializableDemo demo = new SerializableDemo();
        //SerializableDemo(name=AAA, age=10, hobby=xxx)
        //writeObject(demo);
        SerializableDemo demo222 = (SerializableDemo)readObject();
        //SerializableDemo(name=AAA, age=10, hobby=yyy)
        System.out.println(demo222);
    }
    
    private static Object readObject()
        throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./111.txt")))
        {
            return objectInputStream.readObject();
        }
    }
    
    private static void writeObject(Serializable serializable)
        throws IOException
    {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./111.txt")))
        {
            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();
        }
        
    }
    
}
