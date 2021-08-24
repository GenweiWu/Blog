



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
## head是一个空节点(守护节点)，即head.item=null
## last节点的特点是last.next=null

/**
 * Head of linked list.
 * Invariant: head.item == null
 */
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
## 只需要更新last节点，改变last节点的指向

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

```java
//删除头节点，并返回头结点的值
## 只需要更新head节点，修改head指针后将原先的Node.item=null为空节点

private E dequeue() {
	// assert takeLock.isHeldByCurrentThread();
	// assert head.item == null;
	Node<E> h = head;
	Node<E> first = h.next;
    //将之前的节点自己指向自己，帮助gc掉
	h.next = h; // help GC
    
    //head指向新的head节点，不过是通过将head.item置为null
	head = first;
	E x = first.item;
	first.item = null;
	return x;
    
    // Node(null) --> Node(x) --> null
    //取第一个元素，得到  Node(null) --> null，只需要更新head节点
}
```








### 遗留 
- [ ] 为什么LinkedBlockingQueue有两把锁，添加和删除元素不会有问题吗?
   个人看法是，插入和删除元素时，不会同时更新head和last(有一部分原因是head是守护节点)，所以可以考虑分开




