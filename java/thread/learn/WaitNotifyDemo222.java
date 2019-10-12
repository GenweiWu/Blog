package com.njust.test.learn;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * notify需要退出同步代码块再释放锁
 * -------------------------------
 * <pre>
 * [14:14:03] waitTest ==> begin wait
 * [14:14:03] notifyTest ==> begin notify
 * [14:14:03] notifyTest ==> end notify
 * [14:14:06] notifyTest ==> inner sleep end
 * [14:14:06] waitTest ==> end wait
 * [14:14:09] notifyTest ==> outer sleep end
 *
 * Process finished with exit code 0
 * </pre>
 */
public class WaitNotifyDemo222
{
    public static void main(String[] args)
    {
        NotifyTest notifyTest = new NotifyTest();
        
        new Thread(notifyTest::waitTest).start();
        
        new Thread(notifyTest::notifyTest).start();
    }
}

class NotifyTest
{
    public void waitTest()
    {
        synchronized (this)
        {
            try
            {
                printLog("waitTest ==> begin wait");
                this.wait();
                printLog("waitTest ==> end wait");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void notifyTest()
    {
        synchronized (this)
        {
            printLog("notifyTest ==> begin notify");
            this.notify();
            //1.这里已经调用了notify
            printLog("notifyTest ==> end notify");
            
            try
            {
                Thread.sleep(3000);
                printLog("notifyTest ==> inner sleep end");
                //2.这里才退出同步代码块
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        
        try
        {
            Thread.sleep(3000);
            printLog("notifyTest ==> outer sleep end");
            //3.线程继续执行到这儿，不过这里不在同步代码块中
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    //为了记录时间点
    private void printLog(String msg)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String dateStr = simpleDateFormat.format(new Date());
        System.out.println("[" + dateStr + "] " + msg);
    }
    
}
