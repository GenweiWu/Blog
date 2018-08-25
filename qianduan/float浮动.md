float
==

1、脱离文档流，但是会影响周围的元素对其进行环绕
 - 只影响浮动元素后面的元素，前面的不影响

2、float元素会脱离文档流，导致后面的元素顶替上去，但是会钻到浮动元素下面

3、当一个元素浮动之后，它会被移出正常的文档流，然后向左或者向右平移，一直平移直到碰到了所处的容器的边框，或者碰到另外一个浮动的元素
 
4、不会撑高父元素
 - 除非使用清除浮动
 
5、清除浮动
 - 方式一：父元素 `overflow: auto` 
 - 方式二：通过使用 `clear:both;clear:left;clear:right;`

 [demo here](https://jsfiddle.net/GenweiWu/0oesw3y9/)

---
参考资料  
- https://developer.mozilla.org/zh-CN/docs/CSS/float  
- [解决子级用css float浮动 而父级div没高度不能自适应高度](http://www.divcss5.com/jiqiao/j612.shtml)  
- [css浮动讲解](https://www.cnblogs.com/iyangyuan/archive/2013/03/27/2983813.html)  
    - 对应的[demo](https://jsfiddle.net/GenweiWu/8fa3nxmc/)
- http://luopq.com/2015/11/08/CSS-float/
