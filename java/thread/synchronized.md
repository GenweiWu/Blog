
## 1、synchronized使用总结
1. 两个线程调用同一个对象的同步方法，则一个线程执行完另一个线程才会执行
2. 两个线程分别调用一个对象的同步方法，则不会造成同步
3. 同一个线程中调用两个同步方法，不体现什么同步，因为就一个线程
4. 同一个线程调用一个同步方法，这个同步方法中调用另一个同步方法，则由于可重入性不会进行阻塞

#### 测试代码

> TestTask.java
```java
package com.njust.test.multiple.syncdemo;

public class TestTask
{
    
    public synchronized void doSlow()
    {
        System.out.println(Thread.currentThread().getName() + " -----> slow begin");
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " -----> slow end");
    }
    
    public synchronized void doFast()
    {
        System.out.println(Thread.currentThread().getName() + " -----> fast begin");
        System.out.println(Thread.currentThread().getName() + " -----> fast end");
    }
    
    public synchronized void callAnotherSync()
    {
        System.out.println(Thread.currentThread().getName() + " -----> call synchronized method begin");
        this.doFast();
        System.out.println(Thread.currentThread().getName() + " -----> call synchronized method end");
    }
}

```

> SyncDemo.java
```java
package com.njust.test.multiple.syncdemo;

import org.junit.Test;

public class SyncDemo
{
    /**
     * 2->1会进行同步
     * 两个线程调用同一个对象的同步方法，其中一个执行完另一个线程才能执行
     * <p>
     * t1执行完，t2才开始执行:
     * t1 -----> slow begin
     * t1 -----> slow end
     * t2 -----> slow begin
     * t2 -----> slow end
     * ====> demo finish
     */
    @Test
    public void test1()
        throws InterruptedException
    {
        TestTask testTask = new TestTask();
        
        Thread t1 = new Thread(testTask::doSlow, "t1");
        Thread t2 = new Thread(testTask::doSlow, "t2");
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        System.out.println("====> demo finish");
    }
    
    /**
     * 2->2不会进行同步
     * 两个线程调用两个对象的同步方法，则不会互相阻塞
     * <p>
     * t1 -----> slow begin
     * t2 -----> slow begin
     * t1 -----> slow end
     * t2 -----> slow end
     * ====> demo finish
     */
    @Test
    public void test2()
        throws InterruptedException
    {
        TestTask testTask1 = new TestTask();
        TestTask testTask2 = new TestTask();
        
        Thread t1 = new Thread(testTask1::doSlow, "t1");
        Thread t2 = new Thread(testTask2::doSlow, "t2");
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        System.out.println("====> demo finish");
    }
    
    /**
     * 1对n更不会了
     * 这是同一个线程里调用同步方法，会依次执行，不涉及租不阻塞
     * <p>
     * t1 -----> slow begin
     * t1 -----> slow end
     * t1 -----> fast begin
     * t1 -----> fast end
     * ====> demo finish
     */
    @Test
    public void test3()
        throws InterruptedException
    {
        TestTask testTask1 = new TestTask();
        
        Thread t1 = new Thread(() -> {
            testTask1.doSlow();
            testTask1.doFast();
        }, "t1");
        t1.start();
        
        t1.join();
        System.out.println("====> demo finish");
    }
    
    /**
     * 1、t1,t2调用同一个对象的同步方法，会进行同步，造成阻塞
     * 2、同时，同一个对象的同步方法内部调用另一个同步方法，由于内置锁可重入，所以不会阻塞
     * <p>
     * t1 -----> call synchronized method begin
     * t1 -----> fast begin
     * t1 -----> fast end
     * t1 -----> call synchronized method end
     * t2 -----> call synchronized method begin
     * t2 -----> fast begin
     * t2 -----> fast end
     * t2 -----> call synchronized method end
     * ====> demo finish
     */
    @Test
    public void test4()
        throws InterruptedException
    {
        TestTask testTask1 = new TestTask();
        
        Thread t1 = new Thread(testTask1::callAnotherSync, "t1");
        Thread t2 = new Thread(testTask1::callAnotherSync, "t2");
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        System.out.println("====> demo finish");
    }
}

```

## 2、内置锁synchronized的可重入性

- 可重入性  
对于某个对象，多个方法的锁相同的话，就可以重入  

- demo  
  - [同一个类的两个同步方法](./demo/HelloSync.java)
  - [子类同步方法调用父类同步方法](./demo/LoggingWidget.md)

- 参考  
  - https://www.jianshu.com/p/5982573172b1

