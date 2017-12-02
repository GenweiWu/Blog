float
==

1、脱离文档流，但是会影响周围的元素对其进行环绕
 - 只影响浮动元素后面的元素，前面的不影响
 
2、不会撑高父元素
 - 除非使用清除浮动
 
3、清除浮动
 - 方式一：父元素 `overflow: auto` 
 - 方式二：通过使用 `clear:both;clear:left;clear:right;`

 [demo here](https://jsfiddle.net/GenweiWu/0oesw3y9/)

---
参考资料  
https://developer.mozilla.org/zh-CN/docs/CSS/float
