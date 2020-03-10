BFC 块格式化上下文
==

## 1、什么是块格式化上下文?
BFC即块格式化上下文，可以理解成一个封闭的大箱子(box)，内部元素不会影响箱子外面，同理外面布局不会影响内部布局。

## 2、如何触发块格式化上下文

- body 根元素；
- 浮动元素：float 不为none的属性值；
- 绝对定位元素：position (absolute、fixed)
- display为： inline-block、table-cells、flex
- overflow 除了visible以外的值 (hidden、auto、scroll)


## 3、块格式化上下文的特点

1、内部的盒子会在垂直方向，一个个地放置；  
2、BFC是页面上的一个隔离的独立容器；  
**3、属于同一个BFC的两个相邻Box的上下margin会发生重叠；(外边距合并)**   
**4、计算BFC的高度时，浮动元素也参与计算；**  
5、每个元素的左边，与包含的盒子的左边相接触，即使存在浮动也是如此；   
**6、 BFC的区域不会与float重叠；**  

## 4、块格式化上下文的作用

### 4.1 [防止外边距合并](https://jsfiddle.net/GenweiWu/8px72y5f/)   
- (外边距合并要求两者在同一个块中)  

![image](https://user-images.githubusercontent.com/16630659/52697143-c6a31380-2fab-11e9-87a4-5a1b6f86a876.png)

### 4.2 [防止浮动元素高度坍塌](https://jsfiddle.net/GenweiWu/busa073v/)
![image](https://user-images.githubusercontent.com/16630659/52697271-0ec23600-2fac-11e9-8ceb-9fb422a118e5.png)

### 4.3 [不与浮动元素重叠](https://jsfiddle.net/GenweiWu/d02fxago/)
![image](https://user-images.githubusercontent.com/16630659/52698334-3e723d80-2fae-11e9-8c21-e7b96900c5ae.png)

## 5、参考
- https://developer.mozilla.org/zh-CN/docs/Web/Guide/CSS/Block_formatting_context
- https://mp.weixin.qq.com/s/bGDaiiqPpoTmFhmLZkWuhg
- https://www.jianshu.com/p/bc89987a77bc
