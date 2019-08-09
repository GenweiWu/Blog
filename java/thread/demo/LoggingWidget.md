
## 父类Widget

```java
package com.njust.test.multiple;

public class Widget
{
    public synchronized void doSomething()
    {
        System.out.println(this.toString() + "--- parent begin");
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.toString() + "--- parent end");
    }
}

```

## 子类LoggingWidget

```java
package com.njust.test.multiple;

public class LoggingWidget extends Widget
{
    public synchronized void doSomething()
    {
        System.out.println(this.toString() + "--- sub begin");
        super.doSomething();
        System.out.println(this.toString() + "--- sub end");
    }
    
    public static void main(String[] args)
    {
        new Thread(() -> new LoggingWidget().doSomething()).start();
    }
}
```

### output
```
com.njust.test.multiple.LoggingWidget@9ec32f5--- sub begin
com.njust.test.multiple.LoggingWidget@9ec32f5--- parent begin
com.njust.test.multiple.LoggingWidget@9ec32f5--- parent end
com.njust.test.multiple.LoggingWidget@9ec32f5--- sub end
```
