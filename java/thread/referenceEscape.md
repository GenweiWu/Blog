发布与逸出的几个场景
==

### 还未完成初始化就发布了

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

[演示程序](./demo/referenceEscape/demo1)
