
> 这里主要想学习下：线程池中用到的`blockingQueue`和`blockingDeque`的细节

## :cow: java.util.Queue

### 添加元素
`boolean add(E e)` 添加，满了会抛异常，总是返回true  
`boolean offer(E e);`成功返回true，失败返回false  

### 移除队列头部的元素并返回
`remove` 队列为空则抛异常  
`poll` 队列为空则返回null  

### 读取队列头部的元素
`element` 队列为空则抛异常  
`peek`  队列为空则返回null  

### 安全的方法是：
```
offer <--> add
poll <--> remove
peek <--> element
```

> 参考 `java.util.AbstractQueue`

```java
public boolean add(E e) {
    if (offer(e))
        return true;
    else
        throw new IllegalStateException("Queue full");
}

public E remove() {
    E x = poll();
    if (x != null)
        return x;
    else
        throw new NoSuchElementException();
}

public E element() {
    E x = peek();
    if (x != null)
        return x;
    else
        throw new NoSuchElementException();
}
```


## :cow: BlockingQueue
在`Queue`的方法的基础上，新增以下方法

### take
移除并读取队列的头部元素，队列为空则阻塞

### put 
添加一个元素,如果队列满，则阻塞



## :cow: Deque双端队列

[API参考](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html)

### Deque的主要方法

The twelve methods described above are summarized in the following table:

> 抛异常：指操作失败抛出异常
> 特殊值：指操作失败，返回null或者false(添加是返回false，移除和读取是null)
>
> |                 | **First Element (Head)**                                     |                                   |  **Last Element (Tail)**                                                                |                                                              |
> | --------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
> |                 | *抛异常*                                                     | *特殊值*                                                     | *抛异常*                                                     | *特殊值*                                                     |
> | **Insert**      | [`addFirst(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#addFirst-E-) | [`offerFirst(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#offerFirst-E-) | [`addLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#addLast-E-) | [`offerLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#offerLast-E-) |
> | **Remove**      | [`removeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeFirst--) | [`pollFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pollFirst--) | [`removeLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeLast--) | [`pollLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pollLast--) |
> | **Examine**读取 | [`getFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#getFirst--) | [`peekFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peekFirst--) | [`getLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#getLast--) | [`peekLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peekLast--) |



### Deque可以当做Queue(先进先出)用

interface are precisely equivalent to `Deque` methods as indicated in the following table:


> public interface Deque<E> extends Queue<E>  
> Deque可以当做普通的Queue(先进先出)使用，方法的对应关系如下：
>     
| **`Queue` Method**                                           | **Equivalent `Deque` Method**                                |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [`add(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#add-E-) | [`addLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#addLast-E-) |
| [`offer(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#offer-E-) | [`offerLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#offerLast-E-) |
| [`remove()`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#remove--) | [`removeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeFirst--) |
| [`poll()`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#poll--) | [`pollFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pollFirst--) |
| [`element()`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#element--) | [`getFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#getFirst--) |
| [`peek()`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#peek--) | [`peekFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peek--) |

### Deque也可以当做Stack(后进先出)用

> push, pop ,peedk都从头部操作即可
> LinkedList就实现了Deque接口，所以一般 Stack<T> stack = new LinkedList<T>();

| **Stack Method**                                             | **Equivalent `Deque` Method**                                |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [`push(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#push-E-) | [`addFirst(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#addFirst-E-) |
| [`pop()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pop--) | [`removeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeFirst--) |
| [`peek()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peek--) | [`peekFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peekFirst--) |


    

## :cow: BlockingDeque

`BlockingDeque`在`Deque`的基础上，增加了会阻塞的`put`和`take`方法（当然还有一定时间内阻塞的方法offer(e,time,unit)和poll(time,unit)）



### BlockingDeque主要方法

[API](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html)    
    
对应操作失败场景，对应四种方法：

1. `Throws exception`抛异常
2.  `Special value`返回特殊值，如返回false或null
3.  `Blocks`阻塞住，如put和take方法
4.  `Times out`在指定时间内没操作成功，则认为失败



> First Element (Head)  
    
|                          | *Throws exception*                                           | *Special value*                                              | *Blocks*                                                     | *Times out*                                                  |
| ------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Insert**               | [`addFirst(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#addFirst-E-) | [`offerFirst(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#offerFirst-E-) | [`putFirst(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#putFirst-E-) | [`offerFirst(e, time, unit)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#offerFirst-E-long-java.util.concurrent.TimeUnit-) |
| **Remove**               | [`removeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeFirst--) | [`pollFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#pollFirst-long-java.util.concurrent.TimeUnit-) | [`takeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#takeFirst--) | [`pollFirst(time, unit)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#pollFirst-long-java.util.concurrent.TimeUnit-) |
| **Examine**              | [`getFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#getFirst--) | [`peekFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peekFirst--) | *not applicable*                                             | *not applicable*                                             |

>  Last Element (Tail)
    
|                          | *Throws exception*                                           | *Special value*                                              | *Blocks*                                                     | *Times out*                                                  |
| ------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Insert**               | [`addLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#addLast-E-) | [`offerLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#offerLast-E-) | [`putLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#putLast-E-) | [`offerLast(e, time, unit)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#offerLast-E-long-java.util.concurrent.TimeUnit-) |
| **Remove**               | [`removeLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeLast--) | [`pollLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pollLast--) | [`takeLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#takeLast--) | [`pollLast(time, unit)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#pollLast-long-java.util.concurrent.TimeUnit-) |
| **Examine**              | [`getLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#getLast--) | [`peekLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peekLast--) | *not applicable*                                             | *not applicable*                                             |



### BlockingDeque可以被当做BlockingQueue使用

`public interface BlockingDeque<E> extends BlockingQueue<E>, Deque<E>`


| **`BlockingQueue` Method**                                   | **Equivalent `BlockingDeque` Method**                        |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Insert**                                                   |                                                              |
| [`add(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#add-E-) | [`addLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#addLast-E-) |
| [`offer(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#offer-E-) | [`offerLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#offerLast-E-) |
| [`put(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#put-E-) | [`putLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#putLast-E-) |
| [`offer(e, time, unit)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#offer-E-long-java.util.concurrent.TimeUnit-) | [`offerLast(e, time, unit)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#offerLast-E-long-java.util.concurrent.TimeUnit-) |
| **Remove**                                                   |                                                              |
| [`remove()`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#remove--) | [`removeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeFirst--) |
| [`poll()`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#poll--) | [`pollFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pollFirst--) |
| [`take()`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#take--) | [`takeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#takeFirst--) |
| [`poll(time, unit)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#poll-long-java.util.concurrent.TimeUnit-) | [`pollFirst(time, unit)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#pollFirst-long-java.util.concurrent.TimeUnit-) |
| **Examine**                                                  |                                                              |
| [`element()`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#element--) | [`getFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#getFirst--) |
| [`peek()`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html#peek--) | [`peekFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peekFirst--) |


