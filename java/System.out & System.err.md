##System.out和System.err的并发

最近在开发时发现，同时使用System.out和System.err打印内容，会出现多线程的现象。

###代码如下：
```java
public class SystemDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println("+++++");
            // System.out.flush();
            System.err.println("-----");
        }
    }
}


```

运行结果类似于：
[](../201604/2016-04-10_103450.png)