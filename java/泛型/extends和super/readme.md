
### PECS(Producer Extends Consumer Super)原则

1.频繁往外取，适合用<? Extends T>  
2.频繁往里存，适合用<? Super T>

> 上界通配符  extends  
![](https://pic4.zhimg.com/80/cdec0a066693684036d4bcaab4fdc1e3_720w.jpg?source=1940ef5c)

> 下界通配符 super  
![](https://pic2.zhimg.com/80/0800ab14b2177e31ee3b9f6d477918fa_720w.jpg?source=1940ef5c)

### 代码
```java
package com.njust.test.kemu2;

import java.util.ArrayList;
import java.util.List;

class Fruit
{

}

class Apple extends Fruit
{

}

class BigApple extends Apple
{

}

class SmallApple extends Apple
{

}

public class Test
{
    @org.junit.Test
    public void testExtends()
    {
        List<? extends Apple> extendsList = new ArrayList<>();
        
        //不能往里存
        //假如这里？表示BigApple，你放Apple是不行的就不合适了
        //又假如这里？表示SmallApple，你放BigApple也不合适
        //extendsList.add(new Fruit());
        //extendsList.add(new Apple());
        //extendsList.add(new BigApple());
        
        //只能往外取，取出来只能是Apple或是Object
        Apple apple = extendsList.get(0);
    }
    
    @org.junit.Test
    public void testSuper()
    {
        List<? super Apple> superList = new ArrayList<>();
        
        //可以往里存,但是Fruit不行
        //不管？是Apple或者Apple的父类,(a),你放进去Apple或者Apple的子类,(b),b也肯定是a
        //superList.add(new Fruit());
        superList.add(new Apple());
        superList.add(new BigApple());
        
        //取出来只能放Object了
        //因为?可能是Apple的任意父类
        Object object = superList.get(0);
        
    }
}

```

### 参考
- https://www.zhihu.com/question/20400700


