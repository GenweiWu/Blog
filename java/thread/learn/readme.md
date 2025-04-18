
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
3. `synchronized`必须锁object，int是不行的(编译错误)
```java
 private  int num = 0;

 public void method1()
 {
     synchronized (num) //报错，要求是Object类型，比如Integer,String等
     {

     }
 }
```
4. `synchronized`不要去锁变化的对象，比如Integer类型一直在修改，就不要用它了
```
//Integer修改值后其实已经变了
int num = 300;
System.out.println("identityHashCode-->" + System.identityHashCode(num));

num++;
System.out.println("identityHashCode-->" + System.identityHashCode(num));
```
> SynchronizeDemo2.java

5. 避免使用使用常量池的对象，万一刚好使用了常量池的同一变量
```
Byte,Short,Integer,Long,Character,Boolean,这5种包装类默认创建了数值[-128，127]的相应类型的缓存数据
两种浮点数类型的包装类Float,Double并没有实现常量池技术
String也是有常亮池的
```

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
       while (<condition does not hold>)
           obj.wait(timeout, nanos);
           //wait等待，如果此时被notify唤醒，则继续往下执行，所以用while相当于二次检查条件是否满足
       ... // Perform action appropriate to condition
   }
```

> WaitNotifyDemo.java

### 6)wait会释放当前所在的锁，而且被唤醒后需要再次获取锁才能继续执行
> https://stackoverflow.com/a/13664082/6182927  
```java
public synchronized guardedJoy() {
    // must get lock before entering here
    while(!joy) {
        try {
            wait(); // releases lock here
            // must regain the lock to reentering here
        } catch (InterruptedException e) {}
    }
    System.out.println("Joy and efficiency have been achieved!");
}
```

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

## 6、多个线程之前数据共享

#### 1. 如果做的事情是一样的，则可以直接通过同一个Runnable来共享数据

> MultiThreadShareDemo.java
```java
class Seller implements Runnable
{
    private int count = 100;
    
    @Override
    public void run()
...
}
```

#### 2. 如果做的事情不一样，主要有两种方法
- 方法1:通过让多个内部类，共享外部类的数据，来实现共享

> MultiThreadShareDemo2a.java
```java
ShareData1 shareData1 = new ShareData1();

new Thread(() -> {
   shareData1.increase();
}).start();
new Thread(() -> {
   shareData1.decrease();
}).start();
```

- 方法2:通过将共享数据，作为构造函数的参数，传递给多个Runnable来实现
> MultiThreadShareDemo2b.java
```java
ShareData2 shareData2 = new ShareData2();

for (int i = 0; i < 2; i++)
{
   new Thread(new Increase(shareData2)).start();
}
for (int i = 0; i < 2; i++)
{
   new Thread(new Decrease(shareData2)).start();
}
```




