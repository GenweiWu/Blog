package com.njust.test.multiple.EscapeTest2;

public class DefaultExceptionReporter
{
    public DefaultExceptionReporter(ExceptionHandler exceptionHandler)
    {
        exceptionHandler.handle(this);
    }
    
    //默认的报告只是打印以下
    public void report(String exceptionMsg)
    {
        System.out.println("exceptionMsg=" + exceptionMsg);
    }
}
