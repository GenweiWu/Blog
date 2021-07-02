
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



## :cow: Deque
