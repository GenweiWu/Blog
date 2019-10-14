

## 1. 主要4种线程池

### 1)`Executors.newFixedThreadPool(3) `  
创建固定数量的线程的线程池
```java
    /**
     * Creates a thread pool that reuses a fixed number of threads
     * operating off a shared unbounded queue.  At any point, at most
     * {@code nThreads} threads will be active processing tasks.
     * If additional tasks are submitted when all threads are active,
     * they will wait in the queue until a thread is available.
     * If any thread terminates due to a failure during execution
     * prior to shutdown, a new one will take its place if needed to
     * execute subsequent tasks.  The threads in the pool will exist
     * until it is explicitly {@link ExecutorService#shutdown shutdown}.
     *
     * @param nThreads the number of threads in the pool
     * @return the newly created thread pool
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
```

#### 说明
- 如果任务数大于线程数，则任务会排队等待前面的任务执行完再来执行它们

### 2)`Executors.newCachedThreadPool()`
创建可缓存的无限长度线程池,需要多少个就创建多少个(除非受到所在系统或jvm的限制)
```java
    /**
     * Creates a thread pool that creates new threads as needed, but
     * will reuse previously constructed threads when they are
     * available.  These pools will typically improve the performance
     * of programs that execute many short-lived asynchronous tasks.
     * Calls to {@code execute} will reuse previously constructed
     * threads if available. If no existing thread is available, a new
     * thread will be created and added to the pool. Threads that have
     * not been used for sixty seconds are terminated and removed from
     * the cache. Thus, a pool that remains idle for long enough will
     * not consume any resources. Note that pools with similar
     * properties but different details (for example, timeout parameters)
     * may be created using {@link ThreadPoolExecutor} constructors.
     *
     * @return the newly created thread pool
     */
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }
```

#### 说明
- 可以动态回收空闲的线程(比如空闲了60s了)，也可以根据需要动态创建线程


### 3)`Executors.newSingleThreadExecutor()`
创建只有一个线程的线程池
```java
    /**
     * Creates an Executor that uses a single worker thread operating
     * off an unbounded queue. (Note however that if this single
     * thread terminates due to a failure during execution prior to
     * shutdown, a new one will take its place if needed to execute
     * subsequent tasks.)  Tasks are guaranteed to execute
     * sequentially, and no more than one task will be active at any
     * given time. Unlike the otherwise equivalent
     * {@code newFixedThreadPool(1)} the returned executor is
     * guaranteed not to be reconfigurable to use additional threads.
     *
     * @return the newly created single-threaded Executor
     */
    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
```

#### 说明
- 创建一个线程的线程池，它跟单个线程的区别是，如果线程错误退出了，它会重新创建一个线程来顶替
- 跟`Executors.newFixedThreadPool(1)`的区别是，这是线程无法再修改成配置成多个线程，即无法修改

### 4)`Executors.newScheduledThreadPool(2)`
创建一个固定线程个数的线程池，执行定时任务
```java
    /**
     * Creates a thread pool that can schedule commands to run after a
     * given delay, or to execute periodically.
     * @param corePoolSize the number of threads to keep in the pool,
     * even if they are idle
     * @return a newly created scheduled thread pool
     * @throws IllegalArgumentException if {@code corePoolSize < 0}
     */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }
```

#### 说明

- 用法
```java
ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

//延迟指定时间后，一次执行
scheduledExecutorService.schedule(() -> {
    System.out.println(Thread.currentThread().getName() + " one-shot booming");
}, 3, TimeUnit.SECONDS);

//延迟指定时间后，周期执行     
scheduledExecutorService.scheduleAtFixedRate(() -> {
    System.out.println(Thread.currentThread().getName() + " interval continue booming");
}, 3, 1, TimeUnit.SECONDS);
```
- 另，shutdown调用后，会等待schedule的任务执行完，但是不会等待scheduleAtFixedRate继续周期执行，线程池直接退出了

## 2. 线程池的关闭

- shutdown方法  
会将已接收的任务执行完，然后关闭线程池
- shutdownNow方法   
则直接关闭，即使有任务还没执行完

