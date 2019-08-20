发布与逸出的几个场景
==

### 还未完成初始化就发布了
```java
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
