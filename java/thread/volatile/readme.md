
## 可见性  
- 对volatile修饰的变量的修改，其他线程立即可见
- 禁止进行指令重排序
```
volatile变量前面和后面的语句的顺序不能互换
```

- 无法保证原子性

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

