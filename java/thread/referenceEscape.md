发布与逸出的几个场景
==

### (1) 还未完成初始化就发布了

```java
//published暴露this的时候，还没完成num的赋值操作，可能会导致外部调用published读取num时的值是未初始化的
final class Publisher {
  public static volatile Publisher published;
  int num;
 
  Publisher(int number) {
    published = this;
    // Initialization
    this.num = number;
    // ...
  }
}
```

[演示程序1](./demo/referenceEscape/demo1)

### (2) 外部方法类型,Handler
> A2处`MyExceptionReporter`的构造方法中调用父类构造函数，而父类构造函数的A1行已经将this暴露给`ExceptionReporter er`了  
> 如果A2执行完了，但是还没进行A3:logger的初始化，此时`ExceptionReporter er`已经可以访问this了，可能调用某个需要Logger方法的方法则会报错

```java
// interface ExceptionReporter
public interface ExceptionReporter {
  public void setExceptionReporter(ExceptionReporter er);
  public void report(Throwable exception);
}
```

```java
// Class DefaultExceptionReporter
public class DefaultExceptionReporter implements ExceptionReporter {
  public DefaultExceptionReporter(ExceptionReporter er) {
    // Carry out initialization
    // Incorrectly publishes the "this" reference  //A1:这里已经将this逸出了
    er.setExceptionReporter(this);
  }
 
  // Implementation of setExceptionReporter() and report()
}
```

```java
// Class MyExceptionReporter derives from DefaultExceptionReporter
public class MyExceptionReporter extends DefaultExceptionReporter {
  private final Logger logger;
 
  public MyExceptionReporter(ExceptionReporter er) {
    super(er); // Calls superclass's constructor  //A2:此处的调用父类构造函数会进行发布，而此时子类还没有初始化完成
    // Obtain the default logger
    logger = Logger.getLogger("com.organization.Log");  //A3：Logger初始化
  }
 
  public void report(Throwable t) {
    logger.log(Level.FINEST,"Loggable exception occurred", t);
  }
}
```

[演示程序2](./demo/referenceEscape/demo2)


### (3) 内部类
由于内部类会有一个指向外部类的引用，内部类的逸出会导致外部类间接逸出

```java
//EventListener暴露给source了，由于内部类会有指向外部类的引用，间接导致ThisEscape暴露给了source，此时A1处还没完成初始化
//此时source已经可以访问ThisEscape了，而ThisEscape可能还在初始化过程呢，则结果出错

public class ThisEscape {
    public ThisEscape(EventSource source){
        source.registerListener(
            new EventListener(){
                public void onEvent(Event e){
                    doSomething(e)
                }
            });
         
         //A1:其它的初始化代码
         ...xxx
    }
}
```

[演示程序3](./demo/referenceEscape/demo3)

### (4) 启动线程Thread

```java
//thread调用start后马上执行了run方法，run方法中可以操作ThreadStarter，而此时ThreadStarter还在初始化过程中

final class ThreadStarter implements Runnable {
  private String name;
  public ThreadStarter() {
    Thread thread = new Thread(this);
    thread.start();
    
    //other code
    ...xxx
    this.name="init";
  }
 
  @Override public void run() {
    // ...
    System.out.println(name);
  }
}
```

[演示程序4](./demo/referenceEscape/demo4)

### 参考
- https://wiki.sei.cmu.edu/confluence/display/java/TSM01-J.+Do+not+let+the+this+reference+escape+during+object+construction
