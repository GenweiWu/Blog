package com.njust.test.multiple.EscapeTest2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogExceptionReporter extends DefaultExceptionReporter
{
    private final Logger logger;
    
    public LogExceptionReporter(ExceptionHandler exceptionHandler)
    {
        super(exceptionHandler);
        //Thread.yield();
        logger = Logger.getLogger("LogExceptionReporter");
        logger.log(Level.INFO, "logger init success.");
    }
    
    @Override
    public void report(String exceptionMsg)
    {
        logger.log(Level.INFO, exceptionMsg);
    }
}
