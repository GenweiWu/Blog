
## Event.target, Event.toElement and Event.srcElement的区别 

> https://stackoverflow.com/a/31866151
- `$event.target || $event.srcElement`是指原始触发元素
- `$event.currentTarget`指的是当前触发元素
```js
let target = $event.target || $event.srcElement || $event.currentTarget
```

> 点击div3会向上冒泡div3->div2->div1，冒泡到div1时，$event.target || $event.srcElement都是div3，而$event.currentTarget是div1
```html
<div id="div1">
  <div id="div2">
    <div id="div3"></div>
  </div>
</div>
```

## event.preventDefault()、event.stopPropagation()和return false区别

- event.preventDefault()阻止默认动作
- event.stopPropagation() 阻止事件冒泡
- return false 阻止默认和冒泡事件

> https://blog.csdn.net/chelen_jak/article/details/78831522
