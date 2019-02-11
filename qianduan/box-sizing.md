
## 1. box-sizing有啥作用？

当你设置了元素的高度或宽度，实际展现的元素却超出你的设置：这是因为元素的边框和内边距会撑开元素。  
当你设置一个元素为 `box-sizing: border-box;` 时，此元素的内边距和边框不再会增加它的高度、宽度。    
(这是最流行的用法，但是它还支持其他设置)
>http://zh.learnlayout.com/box-sizing.html

## 2. box-sizing的值

- `content-box`  
之前css2使用的值；
特点是：设置的高度和宽度直接设置到内容框，在内容框的外面绘制内边距+边框；

- `border-box`  
css3新增的值；
特点是：先设置元素的整体高度和宽度，内边距+边框都在前者的大小内绘制，所以内容框的大小 = 设置的大小 - 内边距 - 边框；

- `inherit`  
继承父元素的box-sizing的值。

## 3. demo

### 3.1 `content-box`  
![image](https://user-images.githubusercontent.com/16630659/52550542-7ee98400-2e13-11e9-86dd-5c0a25c6ef6a.png)

### 3.2 `border-box`   
![image](https://user-images.githubusercontent.com/16630659/52550674-09ca7e80-2e14-11e9-9b6c-ea9567e347eb.png)
