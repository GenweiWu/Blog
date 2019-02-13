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

## 4、块格式化上下文的作用


## 5、参考
- https://developer.mozilla.org/zh-CN/docs/Web/Guide/CSS/Block_formatting_context
- https://mp.weixin.qq.com/s/bGDaiiqPpoTmFhmLZkWuhg
- https://www.jianshu.com/p/bc89987a77bc
