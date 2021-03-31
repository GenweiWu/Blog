## 1. 单例

> http://c.biancheng.net/view/1338.html   

类只对应一个实例，且自行创建这个实例



#### 特点

- 类只有一个实例
- 私有构造函数
- 提供对外的访问点  `getInstance`

#### 应用

- windows的任务管理器
- spring的`applicationContext`
- 多线程中的线程池

```
@Scope("prototype")  //原型模式
@Scope("singleton")    //默认是单例 //单例模式
```

## 2. 原型模式，也称为克隆模式

基于一个实例，复制出一个新的相同或类似的实例



####  特点

- 要实现克隆clone方法
- 有深拷贝、浅拷贝

#### 应用
- spring中的scope("proptotype")
- 多个对象大部分属性相同，只有个别属性不同

## 3. 工厂

### 3.1 简单工厂

#### 要素

- 1个简单工厂 `simpleFactory`
- 1个抽象产品

- 多个具体产品

然后工厂通过参数，类似下面的逻辑，产生不同的产品

```java
    static class SimpleFactory {
        public static Product makeProduct(int kind) {
            switch (kind) {
                case 1:
                    return new Product1();
                case 2:
                    return new Product2();
            }
            return null;
        }
    }
```

### 3.2 工厂方法

#### 元素






### 3.3 抽象工厂











## 建造者模式





