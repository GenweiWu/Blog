

## 特性

#### 1、脱离文档流,实现文字环绕

1.1 脱离文档流，但是会影响周围的元素对其进行环绕
 - 只影响浮动元素后面的元素，前面的不影响

1.2 float元素会脱离文档流，导致后面的元素顶替上去，但是会钻到浮动元素下面
> https://jsitor.com/embed/_m84np43X
![image](https://user-images.githubusercontent.com/16630659/76529413-36384a80-64ad-11ea-96ea-737f9835e209.png)

//https://jsfiddle.net/GenweiWu/0oesw3y9/


- 行框元素和浮动元素的不可重叠性
> 这里说的行框元素是div.animal中的span元素，它与div.father>img没有重叠
> div.animal实际上是与div.father>img发生了重叠的
//https://jsfiddle.net/GenweiWu/aq7Lht5e/
 
1.3 包裹性 
 
> 下图中, div.box的宽度跟父元素一样
> 但是div.float的宽度则是子元素的宽度撑开的
 //https://jsfiddle.net/GenweiWu/darx56jL/


#### 2、当一个元素浮动之后，它会被移出正常的文档流，然后向左或者向右平移，一直平移直到碰到了所处的容器的边框，或者碰到另外一个浮动的元素
 
#### 3、高度塌陷:不会撑高父元素
 - 除非使用清除浮动
     - 方式一：父元素 `overflow: auto` 
     - 方式二：通过使用 `clear:both;clear:left;clear:right;`
     
   [demo here](https://jsfiddle.net/GenweiWu/0oesw3y9/)      

---
## 参考资料  
- https://developer.mozilla.org/zh-CN/docs/CSS/float  
- [解决子级用css float浮动 而父级div没高度不能自适应高度](http://www.divcss5.com/jiqiao/j612.shtml)  
- [css浮动讲解](https://www.cnblogs.com/iyangyuan/archive/2013/03/27/2983813.html)  
    - 对应的[demo](https://jsfiddle.net/GenweiWu/8fa3nxmc/)
- http://luopq.com/2015/11/08/CSS-float/
