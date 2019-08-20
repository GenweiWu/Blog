package com.njust.test.multiple.EscapeTest2;

/**
 * A1处放开运行：
 * Exception in thread "main" java.lang.NullPointerException
 * at com.njust.test.multiple.EscapeTest2.LogExceptionReporter.report(LogExceptionReporter.java:21)
 * at com.njust.test.multiple.EscapeTest2.Main.lambda$main$0(Main.java:9)
 * at com.njust.test.multiple.EscapeTest2.DefaultExceptionReporter.<init>(DefaultExceptionReporter.java:7)
 * at com.njust.test.multiple.EscapeTest2.LogExceptionReporter.<init>(LogExceptionReporter.java:12)
 * at com.njust.test.multiple.EscapeTest2.Main.main(Main.java:7)
 * ----------------------------------------------------------------------------------------------
 * A1处注释掉:
 * 八月 20, 2019 5:01:51 下午 com.njust.test.multiple.EscapeTest2.LogExceptionReporter <init>
 * 信息: logger init success.
 * 八月 20, 2019 5:01:51 下午 com.njust.test.multiple.EscapeTest2.LogExceptionReporter report
 * 信息: error.222
 */
public class Main
{
    public static void main(String[] args)
    {
        LogExceptionReporter logExceptionReporter = new LogExceptionReporter(
            reporter -> {
                //这里执行的时候，LogExceptionReporter实际上还没完成初始化呢
                //reporter.report("error.111"); //==> 这里是A1
            });
        
        logExceptionReporter.report("error.222");
    }
}
