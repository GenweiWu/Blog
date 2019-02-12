
## 1、块级元素的外边距合并

两个条件：
- 块级元素(不包括浮动和绝对定位元素，即float、postion:absolute/fixed)  
- 垂直方向

## 2、基本场景

### 2.1 相邻的兄弟元素

### 2.2 父亲元素和第一个、最后一个子元素

### 2.3 空的块级元素

## 3、如何避免外边距合并

参考：https://developer.mozilla.org/zh-CN/docs/Web/CSS/CSS_Box_Model/Mastering_margin_collapsing 
```
(1)相邻元素之间
毗邻的两个元素之间的外边距会折叠（除非后一个元素需要清除之前的浮动）。

(2)父元素与其第一个或最后一个子元素之间
如果在父元素与其第一个子元素之间不存在边框、内边距、行内内容，也没有创建块格式化上下文、或者清除浮动将两者的 margin-top 分开；
或者在父元素与其最后一个子元素之间不存在边框、内边距、行内内容、height、min-height、max-height将两者的 margin-bottom 分开，
那么这两对外边距之间会产生折叠。此时子元素的外边距会“溢出”到父元素的外面。

(3)空的块级元素
如果一个块级元素中不包含任何内容，并且在其 margin-top 与 margin-bottom 之间没有边框、内边距、行内内容、
height、min-height 将两者分开，则该元素的上下外边距会折叠。
```
