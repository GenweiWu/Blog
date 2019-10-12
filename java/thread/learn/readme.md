
## 1、传统的创建线程方法

方法1：继承`Thread`方法，然后覆盖`run`方法  
方法2：通过`new Thread(new Runnable(){})`在Runnable接口实现run方法

> ThreadDemo.java

## 2、传统定时器Timer


```java
//一次性执行，延迟`delay`ms 
public void schedule(TimerTask task, long delay)
```

```java
//周期性执行，延迟`delay`ms，每隔`period`ms执行 
public void schedule(TimerTask task, long delay, long period)
```

> TimerDemo.java

## 3、传统线程互斥synchronized

1. `synchronized`加在本地变量或方法参数上是没用的
2. `synchronized`加在非静态方法上，等同于`synchronize(this)`
   `synchronized`加在静态方法上，等同于`synchronize(A.class)`

> SynchronizeDemo.java

## 4、传统线程同步通信 wait,notify

### 1)wait和nofity
1. 调用`wait`，则将持有当前对象的线程交出对象的控制权，同时进入等待状态(WAITING)
2. 调用`nofity`，则将唤醒一个正在等待该对象控制权的线程B，让B继续运行；而A自己则继续执行，且A执行完同步块才真正释放锁
3. 调用`nofityAll`则唤醒所有，而不是某一个

### 2)wait() 与 notify/notifyAll 方法必须在同步代码块中使用
在synchronized修饰的同步代码块或方法里面调用wait() 与  notify/notifyAll()方法, 
否则抛异常`java.lang.IllegalMonitorStateException`  

### 3)notify需要退出同步代码块再释放锁
调用notify的线程A，会唤醒一个等待该对象锁的线程B，而A自己则继续执行，直到执行完对象锁锁住的同步代码块才释放锁

> 下面这个，只有执行到2才会真正触发wait的线程继续执行，3则不影响
```java
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
```

> WaitNotifyDemo222.java

### 4)interrupt可以打断wait状态
```java
synchronized (lock)
{
    try
    {
        lock.wait();
        System.out.println("wait end");
    }
    catch (InterruptedException e)
    {
        System.out.println("interrupt here!!!");
    }
}
```

> WaitNotifyDemo333.java

### 5)wait一般总是和while判断一起，而不是if判断一起(可能存在假唤醒)
```
As in the one argument version, interrupts and spurious wakeups are
possible, and this method should always be used in a loop:
```
```java
   synchronized (obj) {
       while (&lt;condition does not hold&gt;)
           obj.wait(timeout, nanos);
           //wait等待，如果此时被notify唤醒，则继续往下执行，所以用while相当于二次检查条件是否满足
       ... // Perform action appropriate to condition
   }
```

> WaitNotifyDemo.java

## 5、线程范围内变量共享ThreadLocal

指的是同一个线程，但涉及多个步骤(经常是不同类的方法)，如何共享数据;  
典型场景是web应用读取session中的登录信息(可能调用多个方法，但是并没有将session信息作为参数传来传去)

1. `ThreadLocal` 类似于 `Map<Thread,T>`
2. `ThreadLocal`只能保存一个变量，所以保存多个变量，要不多个`ThreadLocal`要么封装成对象
3. 可以覆盖`initialValue`方法，则可以避免`threadLocal.get()`可能为null的问题
```java
    private static ThreadLocal<Student> studentThreadLocal = new ThreadLocal<Student>()
    {
        @Override
        protected Student initialValue()
        {
            return new Student();
        }
    };
```

> ThreadScopeShareData.java  //用Map<Thread,T>模拟ThreadLocal

> ThreadLocalDemo.java


