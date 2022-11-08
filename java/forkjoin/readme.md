ForkJoinPool
==

- 分治，以及工作窃取
- 效果是多个子任务并发执行后，都执行完再结束

### 无返回值
> 继承RecursiveAction


### 有返回值
> 继承RecursiveTask<T>

### java8新写法
```java
private static void test2() {
        //计算随机的1000个数字的和。
        int[] data = prepareData();
        int sum = IntStream.of(data).parallel().reduce(0, Integer::sum);
        System.out.println("expect sum is:" + sum);
    }

    private static void test1() {
        //打印0-50的数值。
        IntStream.rangeClosed(0, 50).parallel().forEach(x -> {
            System.out.println(Thread.currentThread().getName() + "-->" + x);
        });
    }  
```
  
### 参考
- https://cloud.tencent.com/developer/article/1704658
- https://www.cnblogs.com/myseries/p/12582271.html
