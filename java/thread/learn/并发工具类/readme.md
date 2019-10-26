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
