boxshadow 添加边框阴影，发光效果


## 基本语法 
`box-shadow: h-shadow v-shadow blur spread color;`  
`box-shadow: h-shadow v-shadow blur spread color inset;`

- `h-shadow`:沿x轴的偏移
正值--> 负值<--
- `v-shadow`:沿y轴的偏移
正值向下，负值向上
- `blur`
模糊度，值越大则范围越广+越淡
- `spread`
表示边框的宽度，正值会加宽，负值会变窄
- `color`
颜色
- `inset`
修改默认的`outset`为`inset`,只有需要设置为inset时才设置
注意：不能写outset

[在线演示_box-shadow基本属性](http://jsfiddle.net/GenweiWu/gexhyq38/)

## 简写
- 三属性、四属性、五属性   
`box-shadow:h-shadow v-shadow color`  
`box-shadow:h-shadow v-shadow blur color`  
`box-shadow:h-shadow v-shadow blur spread color` 

- 比如：  
  `box-shadow: 10px 5px green;`  
= `box-shadow: 10px 5px 0 0 green;`

  `box-shadow: 10px 5px 5px green;`  
= `box-shadow: 10px 5px 5px 0 green;`

  `box-shadow: 10px 5px 0px 3px green inset;`  
= `box-shadow: inset 10px 5px 0px 3px green;`

[在线演示_box-shadow简写](http://jsfiddle.net/GenweiWu/5fa4jrgx/)

## 单侧的box-shadow
可以通过x偏移+y偏移来实现单侧的`box-shadow`，但是效果需要通过调整spread来避免其他边也显示。

另外，多个元素的边框效果用单个元素的单侧来拼凑效果不好，要么影响其他边，要么两元素之前有隔断效果

**建议另行设置一个元素的边框来模拟多个元素的边框效果**

[在线演示_单侧的box-shadow](http://jsfiddle.net/GenweiWu/r2xh4wam/)

[在线演示_多元素的边框模拟](http://jsfiddle.net/GenweiWu/L0fk3juh/)



