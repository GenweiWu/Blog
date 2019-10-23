lock
==

### 1. lock作为普通锁

```java
Lock lock = new ReentrantLock();
lock.lock();
...
lock.unlock();
```

> LockDemo.java


### 2. 读写锁 readWriteLock
读读不阻塞，读写阻塞，写写阻塞

```java
ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

readWriteLock.readLock().lock();
readWriteLock.readLock().unlock();

readWriteLock.writeLock().lock();
readWriteLock.writeLock().unlock();
```

> ReadWriteLockDemo.java

### 3. 典型用法 缓存系统

- 已有读锁->想获取写锁: 先释放读锁，再回去写锁
- 已有写锁->想获取读锁：可以直接降级，即可以先获取读锁，再释放写锁

> 样例代码看 sample.java  

> 测试代码看 LockCacheDemo.java



