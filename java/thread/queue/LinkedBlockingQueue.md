## LinkedBlockingQueue源码学习

### 参考
- [LinkedBlockingQueue源码解析](https://juejin.cn/post/6844903821919928334#heading-6)


### 源码分析
```java
// 队列的最大长度
// 1. 大小为Integer.MAX_VALUE时，可以认为是无界队列
// 2.  自定义大小时，可以认为是有界队列
private final int capacity;

//当前队列的实际长度
private final AtomicInteger count = new AtomicInteger();
```



```java
// 基本数据结构为单向链表   
static class Node<E> {
        E item;
        /**
         * One of:
         * - the real successor Node
         * - this Node, meaning the successor is head.next
         * - null, meaning there is no successor (this is the last node)
         */
        Node<E> next;

        Node(E x) { item = x; }
    }
```

```java
//head头指针，last尾指针
transient Node<E> head;
/**
 * Tail of linked list.
 * Invariant: last.next == null
 */
private transient Node<E> last;
```

```java
//takeLock,对应Condition notEmpty
//putLock,对应Condition notFull

/** Lock held by take, poll, etc */
private final ReentrantLock takeLock = new ReentrantLock();

/** Wait queue for waiting takes */
private final Condition notEmpty = takeLock.newCondition();

/** Lock held by put, offer, etc */
private final ReentrantLock putLock = new ReentrantLock();

/** Wait queue for waiting puts */
private final Condition notFull = putLock.newCondition();
```



```java
// 将节点加到链表尾部
private void enqueue(Node<E> node) {
        // assert putLock.isHeldByCurrentThread();
        // assert last.next == null;
    
    //这块的理解正确的是 last = (last.next = node)
    //即 last.next = 新节点，同时 last重新指向node；
    //此时 [之前的last节点，现在倒数第二个节点] --> [last节点] --> null
    // 比如插入1，此时head = null,last=Node(1) (初始head=last都指向Node(null))
        last = last.next = node;
    }


//一开始：  Node(null), head=last=Node(null)
//插入1后： Node(null)--> Node(1)
```

### 遗留 
- [ ] 为什么LinkedBlockingQueue有两把锁，添加和删除元素不会有问题吗?




