
## 可见性  
- 对volatile修饰的变量的修改，其他线程立即可见
- 禁止进行指令重排序
```
volatile变量前面和后面的语句的顺序不能互换
```

- 无法保证原子性

## code

### code1


```java
/*
 * 1.如果print先执行，则是-1--2
 * 2.如果change先执行，则是666-666
 * 但是也会出现-1-666的情况
 */
class Data
{
    private int a = -1;
    
    private int b = -2;
    
    public void change()
    {
        a = 666;
        b = a;
    }
    
    public void print()
    {
        System.out.println(a + "-" + b);
    }
}
```


## 常见用法

#### 1.状态标记
```java
volatile boolean flag = false;
 
while(!flag){
    doSomething();
}
 
public void setFlag() {
    flag = true;
}
```

#### 2.double check
```java
class Singleton{
    private volatile static Singleton instance = null;
     
    private Singleton() {
         
    }
     
    public static Singleton getInstance() {
        if(instance==null) {
            synchronized (Singleton.class) {
                if(instance==null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
```

