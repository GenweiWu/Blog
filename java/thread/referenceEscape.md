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
    // Incorrectly publishes the "this" reference 这里已经将this逸出了
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
    super(er); // Calls superclass's constructor 此处的调用父类构造函数会进行发布，而此时子类还没有初始化完成
    // Obtain the default logger
    logger = Logger.getLogger("com.organization.Log");
  }
 
  public void report(Throwable t) {
    logger.log(Level.FINEST,"Loggable exception occurred", t);
  }
}
```

[演示程序2](./demo/referenceEscape/demo2)


### 参考
- https://wiki.sei.cmu.edu/confluence/display/java/TSM01-J.+Do+not+let+the+this+reference+escape+during+object+construction
