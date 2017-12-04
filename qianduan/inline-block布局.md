inline-block布局
==

使用`inline-block`布局，需要关注几点:


##### 1、人为设置宽度  
##### 2、html源码中元素之间有空格，会导致空隙  
  > 所以如果25% + 75%后出现换行的话：
  - 设置box-sizing:border-box;
  - `inline-block`元素之前有空格
  
  [demo here](https://jsfiddle.net/GenweiWu/w92zya9d/)  

##### 3、如果有需要的话还要设置`vertical-align:center`


参考自:  
http://zh.learnlayout.com/inline-block-layout.html
