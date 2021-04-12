
- 线程1调用join方法，会将当前线程1执行完再执行main线程
- 线程1和线程2都在main方法中调用join，顺序不被保证

```java
/**
* 可能输出111 222 main
* 也可能输出222 111 main 
*/
public static void main(String[] args)
        throws InterruptedException
    {
        Thread thread1 = new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("111");
            }
        };
        Thread thread2 = new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("222");
            }
        };
    
        thread1.start();
        thread2.start();
    
        /**
         * 1.111和222的顺序是不被保证的，
         * 2.只能保证111早于main，222也早于main
         */
        thread1.join();
        thread2.join();
    
        System.out.println("main");
    }
```
