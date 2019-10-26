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
