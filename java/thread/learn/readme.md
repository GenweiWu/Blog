
### 1、传统的创建线程方法

方法1：继承`Thread`方法，然后覆盖`run`方法  
方法2：通过`new Thread(new Runnable(){})`在Runnable接口实现run方法

> ThreadDemo.java

### 2、传统定时器Timer


```java
//一次性执行，延迟`delay`ms 
public void schedule(TimerTask task, long delay)
```

```java
//周期性执行，延迟`delay`ms，每隔`period`ms执行 
public void schedule(TimerTask task, long delay, long period)
```

> TimerDemo.java

### 3、传统线程互斥synchronized

1. `synchronized`加在本地变量或方法参数上是没用的
2. `synchronized`加在非静态方法上，等同于`synchronize(this)`
   `synchronized`加在静态方法上，等同于`synchronize(A.class)`

> SynchronizeDemo.java

### 4、传统线程同步通信 wait,notify

#### 1)wait和nofity
1. 调用`wait`，则将持有当前对象的线程交出对象的控制权，同时进入等待状态(WAITING)
2. 调用`nofity`，则将唤醒一个正在等待该对象控制权的线程，让它继续运行
3. 调用`nofityAll`则唤醒所有，而不是某一个

#### 2)wait一般总是和while判断一起，而不是if判断一起(可能存在假唤醒)
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



