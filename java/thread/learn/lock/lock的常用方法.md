这个最好把`Lock`的四个锁法都比较一下（容我copy些东西）：

------

- `void lock();`

> If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until the lock has been acquired.

**在等待获取锁的过程中休眠并禁止一切线程调度**

------

- `void lockInterruptibly() throws InterruptedException;`

> If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until one of two things happens:
> The lock is acquired by the current thread; or Some other thread interrupts the current thread, and interruption of lock acquisition is supported.

**在等待获取锁的过程中可被中断**

------

- `boolean tryLock();`

> Acquires the lock if it is available and returns immediately with the value true. If the lock is not available then this method will return immediately with the value false.

**获取到锁并返回true；获取不到并返回false**

------

*`boolean tryLock(long time, TimeUnit unit) throws InterruptedException;`

> If the lock is available this method returns immediately with the value true. If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until one of three things happens:The lock is acquired by the current thread; or Some other thread interrupts the current thread, and interruption of lock acquisition is supported; or The specified waiting time elapses.

**在指定时间内等待获取锁；过程中可被中断**

------

假如线程`A`和线程`B`使用同一个锁`LOCK`，此时线程A首先获取到锁`LOCK.lock()`，并且始终持有不释放。如果此时B要去获取锁，有四种方式：

- `LOCK.lock()`: 此方式会始终处于等待中，即使调用`B.interrupt()`也不能中断，除非线程A调用`LOCK.unlock()`释放锁。
- `LOCK.lockInterruptibly()`: 此方式会等待，但当调用`B.interrupt()`会被中断等待，并抛出`InterruptedException`异常，否则会与`lock()`一样始终处于等待中，直到线程A释放锁。
- `LOCK.tryLock()`: 该处不会等待，获取不到锁并直接返回false，去执行下面的逻辑。
- `LOCK.tryLock(10, TimeUnit.SECONDS)`：该处会在10秒时间内处于等待中，但当调用`B.interrupt()`会被中断等待，并抛出`InterruptedException`。10秒时间内如果线程A释放锁，会获取到锁并返回true，否则10秒过后会获取不到锁并返回false，去执行下面的逻辑。
