# ArrayBlockingQueue源码学习

参考：
- https://juejin.cn/post/6844903989788540941

### 基础结构是数组，循环数组

> items.length是最大容量，count是实际容量
```java
 final Object[] items;

    /** items index for next take, poll, peek or remove */
    int takeIndex;

    /** items index for next put, offer, or add */
    int putIndex;

    /** Number of elements in the queue */

/// 此时这里就是简单的count，而不是AtomInteger? 
/// ==> 因为用的同一个lock控制put和take

/// 为啥要有一个count?直接items.length不可以吗?
/// ==> count是实际放进来的元素数量，而item.length=capacity是指最大容量；即count=0时，array={null,null,null}的情况是可能存在的

    int count;
```

### 锁是  1*Lock + 2*condition
```java
    /** Main lock guarding all access */
    final ReentrantLock lock;

    /** Condition for waiting takes */
    //用于可以take元素了
    private final Condition notEmpty;

    /** Condition for waiting puts */
    ///: 用于通知可以put元素了
    private final Condition notFull;

    /**
     * Shared state for currently active iterators, or null if there
     * are known not to be any.  Allows queue operations to update
     * iterator state.
     */
    transient Itrs itrs = null;
```

### 构造函数，需要制定capacity大小，即ArrayBlockingQueue(10)

```java
/// 指定capacity,默认非公平锁
public ArrayBlockingQueue(int capacity) {
	this(capacity, false);
}

/// 主要的构造函数，指定capacity和公平/非公平锁
public ArrayBlockingQueue(int capacity, boolean fair) {
	if (capacity <= 0)
		throw new IllegalArgumentException();
	/// items.length == capacity
    this.items = new Object[capacity];
    lock = new ReentrantLock(fair);
    notEmpty = lock.newCondition();
    notFull =  lock.newCondition();
}
```


### 添加元素offer，循环putIndex

```java
    public boolean offer(E e) {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (count == items.length)
                return false;
            else {
            	///主要的入队方法
                enqueue(e);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }
```

> 统一的入队操作，关注putIndex指示当前put的位置，即假设有 items[putIndex]=null
```java
private void enqueue(E x) {
    // assert lock.getHoldCount() == 1;
    // assert items[putIndex] == null;
    final Object[] items = this.items;
    items[putIndex] = x;
    
    /// 为啥这里重置putIndex=0呢?
    /// ==> 因为数组是循环在使用的
    
    if (++putIndex == items.length)
        putIndex = 0;
    count++;
    notEmpty.signal();
}
```

> 数组在循环使用，即 putIndex == items.length时，则putIndex=0,因为此前有判断count<items.length，说明之前有元素已经被取走了
```java
ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
arrayBlockingQueue.offer(1);
arrayBlockingQueue.offer(2);
arrayBlockingQueue.offer(3);
System.out.println(arrayBlockingQueue); //[1,2,3]
////实际上此时 items = [1,2,3]

arrayBlockingQueue.poll();
System.out.println(arrayBlockingQueue.offer(4));  
System.out.println(arrayBlockingQueue); //[2,3,4]
//// 实际上此时 items = [4,2,3]
```
<details>
<summary>
putIndex=0的原因图解
</summary>

![img](https://user-gold-cdn.xitu.io/2019/11/7/16e46777585160cc?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)
</details>




### 取走元素poll

```java
public E poll() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        return (count == 0) ? null : dequeue();
    } finally {
        lock.unlock();
    }
}
```



```java
private E dequeue() {
    // assert lock.getHoldCount() == 1;
    // assert items[takeIndex] != null;
    final Object[] items = this.items;
    @SuppressWarnings("unchecked")
    E x = (E) items[takeIndex];
    items[takeIndex] = null;
    /// 同样的，takeIndex也会重置到0
    if (++takeIndex == items.length)
        takeIndex = 0;
    count--;
    if (itrs != null)
        itrs.elementDequeued();
    notFull.signal();
    return x;
}
```



### remove(e) 移除指定元素

> 需要遍历元素，如果相等则移除，数组要进行copyArray操作
>
> ​                 |takeIndex        |putIndex
>
> [null, 		xx,		yy ,		null] ，则从takeIndex遍历到putIndex-1

```java
public boolean remove(Object o) {
    if (o == null) return false;
    final Object[] items = this.items;
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        if (count > 0) {
            final int putIndex = this.putIndex;
            int i = takeIndex;
            do {
                if (o.equals(items[i])) {
                    //// 此时关注removeAt(index)
                    removeAt(i);
                    return true;
                }
                if (++i == items.length)
                    i = 0;
            } while (i != putIndex);
        }
        return false;
    } finally {
        lock.unlock();
    }
}
```





> item.length=capacity，没有capacity的属性
>
> count是指实际的元素数量

```java
public int remainingCapacity() {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        return items.length - count;
        // 区分：items.length, count, capacity?
        
    } finally {
        lock.unlock();
    }
}
```



```java
void removeAt(final int removeIndex) {
    // assert lock.getHoldCount() == 1;
    // assert items[removeIndex] != null;
    // assert removeIndex >= 0 && removeIndex < items.length;
    final Object[] items = this.items;
    
    /// 此时removeIndex == takeIndex,比较简单，不需要移动元素
    if (removeIndex == takeIndex) {
        // removing front item; just advance
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
        if (itrs != null)
            itrs.elementDequeued();
    } else {
        // an "interior" remove

        // slide over all others up through putIndex.
        
        /// 此时复杂点，需要从removeIndex的位置开始，依次将右侧元素的值左移，直到遇到putIndex，结果是putIndex左移了一格
        final int putIndex = this.putIndex;
        for (int i = removeIndex;;) {
            int next = i + 1;
            if (next == items.length)
                next = 0;
            if (next != putIndex) {
                items[i] = items[next];
                i = next;
            } else {
                items[i] = null;
                this.putIndex = i;
                break;
            }
        }
        count--;
        if (itrs != null)
            itrs.removedAt(removeIndex);
    }
    notFull.signal();
}
```


### 对比 

|                        | ArrayBlockingQueue                                           | LinkedBlockingQueue                                          |
| ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 锁的实现不同           | 1个lock                                                      | 两个lock：putLock和takeLock                                  |
| 插入、移除元素方式不同 | 操作数组的内容，插入填入值，删除填入null，可能需要进行数组迁移(涉及remove(e)时) | 操作单向链表，插入加入节点，删除移除节点；如果单纯的queue的入队出队，感觉ArrayBlockingQueue效率高 |
| 空间占用               | 初始空间就占用了capacity=items.length，没元素时存的null而已  | 没元素时，空间占用为0，capacity就是个概念上的容量上限        |
| 大小初始化方式         | 必须指定大小                                                 | 不指定大小时，capacity=Integer.MAX_VALUE                     |

