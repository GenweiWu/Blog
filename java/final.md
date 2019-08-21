
## 总结

### 1、final修饰类，则类不能被继承

### 2、final修饰方法，则方法不能被override

### 3、final修饰变量的话

#### 3.1、特性
- 如果是基本数据类型：则数据一旦初始化后就不能再修改
- 如果是引用数据类型：则在对象初始化之后就不能再指向其他的对象,但是还是可以修改对象内部的值

> 基本数据类型
```java
//ERROR:不能再次赋值
public static void main(String[] args)
{
    final int num = 1;
    num = 2;  //Error:(8, 9) java: 无法为最终变量num分配值
    System.out.println(num);
}
```

> 引用数据类型
```java
//ERROR:不能再指向其他对象
public static void main(String[] args)
{
    final FinalTest finalTest;
    finalTest = new FinalTest();
    finalTest = new FinalTest();  //Error:(11, 9) java: 可能已分配变量finalTest
    System.out.println(finalTest);
}
```

```java
//OK:但是可以修改对象内部的值
public class FinalTest
{
    private int num;
    
    public static void main(String[] args)
    {
        final FinalTest finalTest;
        finalTest = new FinalTest();
        System.out.println(finalTest.num);  //0
        
        finalTest.num = 999;
        System.out.println(finalTest.num);  //999
    }
}
```


#### 3.2 局部变量和成员变量的区别
- 局部变量必须在使用前初始化：定义时 或 定义后使用前
- 成员变量必须在定义时 或 默认构造函数中初始化

> 局部变量
```java
//ERROR:局部变量使用前未初始化
final int num;
System.out.println(num);  //Error:(8, 28) java: 可能尚未初始化变量num
```

```java
//OK:定义时初始化
final int num=1;
System.out.println(num);
```

```java
//OK:定义后初始化
final int num;
num = 2;
System.out.println(num);
```

> 成员变量
```java
//ERROR:未初始化
public class FinalTest
{
    final int num;  //Error:(5, 15) java: 变量 num 未在默认构造器中初始化
}
```

```java
//OK:定义时初始化
public class FinalTest
{
    final int num = 1;
}
```

```java
//OK:默认构造函数中初始化
public class FinalTest
{
    final int num;
    
    public FinalTest()
    {
        num = 2;
    }
}
```

```java
//ERROR:非默认构造函数中初始化不行
public class FinalTest
{
    final int num;  //Error:(10, 5) java: 可能尚未初始化变量num
    
    public FinalTest()
    {
        
    }
    
    public FinalTest(int num)
    {
        this.num = num;
    }
}
```


## 参考
- https://www.cnblogs.com/dolphin0520/p/3736238.html
