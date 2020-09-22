package com.njust.test.kemu2;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ExecDemo
{
    /**
     * 1、程序还没结束就调用exitValue，会报错的
     */
    @Test
    public void test01()
        throws IOException
    {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("notepad");
        //java.lang.IllegalThreadStateException: process has not exited
        int exitValue = process.exitValue();
        System.out.println(exitValue);
    }
    
    @Test
    public void test02()
        throws IOException, InterruptedException
    {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("notepad");
        //会阻塞主线程，在关闭写字本之后才会打印finish
        //未读取缓冲区，缓冲区可能耗尽
        process.waitFor();
        
        System.out.println("finish");
    }
    
    @Test
    public void test03()
        throws IOException, InterruptedException
    {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("notepad");
        
        //正确的方法处理正常输出和错误输出
        StreamGobbler outputStream = new StreamGobbler(process.getInputStream(), System.out);
        StreamGobbler errorStream = new StreamGobbler(process.getErrorStream(), System.err);
        outputStream.start();
        errorStream.start();
        
        int exitValue = process.waitFor();
        System.out.println("exitValue:" + exitValue);
        
        outputStream.join();
        errorStream.join();
        
        System.out.println("finish");
    }
}

class StreamGobbler extends Thread
{
    InputStream is;
    
    PrintStream os;
    
    StreamGobbler(InputStream is, PrintStream os)
    {
        this.is = is;
        this.os = os;
    }
    
    public void run()
    {
        try
        {
            int c;
            while ((c = is.read()) != -1)
            {
                os.print((char)c);
            }
        }
        catch (IOException x)
        {
            // handle error
        }
    }
}
