
## 1、块级元素的外边距合并

块级元素的上下外边距，有时会合并为一个外边距。

两个条件：
- 块级元素(不包括浮动和绝对定位元素，即float、postion:absolute)  
- 垂直方向

## 2、基本场景

### 2.1 [相邻的兄弟元素](https://jsfiddle.net/GenweiWu/afz8wyv1/)
![image](https://user-images.githubusercontent.com/16630659/52623545-88442080-2ee7-11e9-8a5b-884622a84d2e.png)

### 2.2 [父亲元素和第一个、最后一个子元素](https://jsfiddle.net/GenweiWu/ok5b9tvs/)
![image](https://user-images.githubusercontent.com/16630659/52623722-fc7ec400-2ee7-11e9-8235-572bfe58c2fd.png)

### 2.3 [空的块级元素](https://jsfiddle.net/GenweiWu/5yacgdkx/)
![image](https://user-images.githubusercontent.com/16630659/52623930-7e6eed00-2ee8-11e9-9799-3aa199a52f81.png)

## 3、如何避免外边距合并

> 参考：https://developer.mozilla.org/zh-CN/docs/Web/CSS/CSS_Box_Model/Mastering_margin_collapsing 
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

> 参考：https://segmentfault.com/a/1190000010346113
```
浮动元素不会与任何元素发生叠加，也包括它的子元素
绝对定位元素和其他任何元素之间不发生外边距叠加，也包括它的子元素
inline-block 元素和其他任何元素之间不发生外边距叠加，也包括它的子元素

创建了 BFC 的元素不会和它的子元素发生外边距叠加

普通流中的块级元素的 margin-bottom 永远和它相邻的下一个块级元素的 margin-top 叠加，除非相邻的兄弟元素 clear
普通流中的块级元素（没有 border-top、没有 padding-top）的 margin-top 和它的第一个普通流中的子元素（没有clear）发生 margin-top 叠加
普通流中的块级元素（height为 auto、min-height为0、没有 border-bottom、没有 padding-bottom）和它的最后一个普通流中的子元素（没有自身发生margin叠加或clear）发生 margin-bottom叠加
如果一个元素的 min-height 为0、没有 border、没有padding、高度为0或者auto、不包含子元素，那么它自身的外边距会发生叠加
```

## 4、参考
- https://www.w3.org/TR/2011/REC-CSS2-20110607/box.html#collapsing-margins
- https://segmentfault.com/a/1190000010346113
