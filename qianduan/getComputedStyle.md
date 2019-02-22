
`Window.getComputedStyle()`返回一个对象的计算后的属性值。

## 用法
```
 window.getComputedStyle(elem,null).getPropertyValue("height");
 
 //可以从伪元素拉取样式信息 (比如, ::after, ::before)
 window.getComputedStyle(elem,'::after').getPropertyValue("height");
```

## 参考
- https://developer.mozilla.org/zh-CN/docs/Web/API/Window/getComputedStyle
