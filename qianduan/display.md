display总结
--

#### 1. 说明
 - display属性用来设置元素的生成框的类型。默认是`inline`，但是浏览器会内置将div等改成`display:block`；
 - 常见的块元素(block)：div、p、h1~h6、ol、ul、li、dl、dt、dd等；
 - 常见的内联/行内元素(inline)：span、a、label、input（不包括button）、img、textarea等；
 
#### 2. display:none  
 - 不展示该元素，不会占据空间。而`visibility:hidden`不展示该元素，但是**会占据空间**。
 
#### 3. display:block
 - 块状元素，会占据一行，即时其宽度没有占满一行也会独占一行(其他元素换行进行展示)；
 - 默认继承父元素的宽度，高度会以子元素撑开的高度为准。(个人理解，仅供参考);
 - 也可以自行设置高度和宽度。
 
#### 4. display:inline
 - 行内元素，不单独占一行，其他元素会紧随其后展示。
 - 无法设置高度、宽度、`margin-top`、`margin-bottom`和 `float`。<br>
  `With 'display: inline', the width, height, margin-top, margin-bottom, and float properties have no effect.`
 
 
 
 
  
 
 
 
 
