

## 数据结构

jdk1.7时：数组+链表

jdk1.8时：数组+链表/红黑树

![](https://res-static.hc-cdn.cn/fms/img/47a64a3fed6c16cd1eb47fe093c890331603764318016)



```java
/** The default initial capacity - MUST be a power of two. */
//初始容量大小=16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

/** The load factor used when none specified in constructor. */
//负载因子：0.75
static final float DEFAULT_LOAD_FACTOR = 0.75f;

final float loadFactor;

/** The next size value at which to resize (capacity * load factor). */
//阈值，超过这个值就要进行数组的扩容；
//计算方法为：容量*负载因子，例如初始阈值为16*0.75=12
int threshold;
```



...逻辑有点复杂呀







## 参考

https://segmentfault.com/a/1190000012926722
