
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
