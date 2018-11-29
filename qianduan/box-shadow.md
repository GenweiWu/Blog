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
`box-shadow:h-shadow v-shadow color`  
`box-shadow:h-shadow v-shadow blur color`  
`box-shadow:h-shadow v-shadow blur spread color` 

  `box-shadow: 10px 5px green;`
= `box-shadow: 10px 5px 0 0 green;`

  `box-shadow: 10px 5px 5px green;`
= `box-shadow: 10px 5px 5px 0 green;`

