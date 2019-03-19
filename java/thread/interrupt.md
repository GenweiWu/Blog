interrupt用于中断线程
==

## 我的理解
1、只是告诉一个线程，你应该中断，但是是否进行中断的动作由该线程自己判断  
告诉的方式是设置属性为`true`
```java
Thread.isInterrupted()
```

2、对于子线程状态
- 如果子线程正在运行，则设置它的`isInterrupted`为true 
- 如果子线程在阻塞中，调用interrupt()会立即将线程的中断标记设为“true”，但是由于线程处于阻塞状态，
所以该“中断标记”会立即被清除为“false”，同时，会产生一个InterruptedException的异常。 


## 代码样例

### 样例1继续运行，`isInterrupted`被设置为true
```java
import java.util.Date;

public class InterruptThread extends Thread
{
    public void run()
    {
        while (true)
        {
            if (Thread.currentThread().isInterrupted())
            {
                System.out.println(new Date() + "--> Someone interrupted me.");
            }
            else
            {
                System.out.println(new Date() + "--> Thread is Going...");
            }
        }
    }
    
    public static void main(String[] args)
        throws InterruptedException
    {
        InterruptThread t = new InterruptThread();
        t.start();
        Thread.sleep(30);
        //效果是将isInterrupted设置为true了，并不会因此结束
        t.interrupt();
        
        //强行结束，否则会一直运行
        Thread.sleep(30);
        System.out.println(new Date() + "--> force exit");
        System.exit(0);
    }
    
}
```


```console
Tue Mar 19 14:49:32 CST 2019--> Thread is Going...
....................
Tue Mar 19 14:49:32 CST 2019--> Thread is Going...
Tue Mar 19 14:49:32 CST 2019--> Thread is Going...
Tue Mar 19 14:49:32 CST 2019--> Someone interrupted me.
Tue Mar 19 14:49:32 CST 2019--> Someone interrupted me.
....................
Tue Mar 19 14:49:32 CST 2019--> Someone interrupted me.
Tue Mar 19 14:49:32 CST 2019--> Someone interrupted me.
Tue Mar 19 14:49:32 CST 2019--> force exit

Process finished with exit code 0
```

### 样例2继续运行，捕获`InterruptedException`异常，且`isInterrupted`被重置为false
```java

import java.util.Date;

public class InterruptThread2 extends Thread
{
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(500);
                if (Thread.currentThread().isInterrupted())
                {
                    System.out.println(new Date() + "--> Someone interrupted me.");
                }
                else
                {
                    System.out.println(new Date() + "--> Thread is Going...");
                }
            }
            catch (InterruptedException e)
            {
                boolean status = Thread.currentThread().isInterrupted();
                System.out.println(new Date() + "--> Thread InterruptedException --> " + status);
            }
        }
    }
    
    public static void main(String[] args)
        throws InterruptedException
    {
        InterruptThread2 t = new InterruptThread2();
        t.start();
        //本来想设置interrupt标识为true，但是由于该线程在阻塞状态，所以该子线程捕获了InterruptedException异常，且状态被重置了
        t.interrupt();
        
        Thread.sleep(3000);
        System.out.println(new Date() + "--> force exit");
        System.exit(0);
    }
    
}
```

```console
Tue Mar 19 15:09:35 CST 2019--> Thread InterruptedException --> false
Tue Mar 19 15:09:36 CST 2019--> Thread is Going...
Tue Mar 19 15:09:37 CST 2019--> Thread is Going...
Tue Mar 19 15:09:37 CST 2019--> Thread is Going...
Tue Mar 19 15:09:38 CST 2019--> Thread is Going...
Tue Mar 19 15:09:38 CST 2019--> Thread is Going...
Tue Mar 19 15:09:38 CST 2019--> force exit

Process finished with exit code 0
```

## 参考
- http://www.cnblogs.com/skywang12345/p/3479949.html
- https://blog.csdn.net/canot/article/details/51087772
