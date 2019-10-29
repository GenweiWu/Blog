并发工具类
==


## 1. `semaphore` 用来控制访问数量

典型场景是：厕所有5个坑，最多能进5个人;或者限制读数据库的链接数等

```java
//可同时5个并发访问
Semaphore semaphore = new Semaphore(5);

//获取锁
semaphore.acquire();
...
//释放锁
semaphore.release();
```

## 2. `CyclicBarrier`用来进行多次协同

典型场景是：几个人一起出发去旅游，早上到门口集合，然后又各自出发玩耍，中午又到食堂集合  
即各自等待到达集合点1，然后各自到达集合点2

```java
CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

//集合点1等待
cyclicBarrier.await();
 
 //集合点2等待
cyclicBarrier.await();
```

## 3. `CountDownLatch`类似倒计时计数器的功能
可以让1个或多个线程，等待其他线程执行完毕再执行

```java
//初始值为5
CountDownLatch finishLatch = new CountDownLatch(5);

//将值-1
finishLatch.countDown();

//等待中，直到值减到0才继续向下执行
 finishLatch.await();
 //可以同时多个在等待
 finishLatch.await();
```

## 4. `Exchanger`实现跨线程交互数据

```java
Exchanger<String> stringExchanger = new Exchanger<>();

//
String data="111";
String exchange = stringExchanger.exchange(data);

//
```
