
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

