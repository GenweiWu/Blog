
## 说明

> 样例: https://jsitor.com/tB8lln23f
```
当一个元素失去焦点的时候 blur 事件被触发。它和 focusout 事件的主要区别是 focusout 支持冒泡。

focusout类似于mouseout，暂时没发现有mouseleave类似事件，所以只能针对子元素分别设置blur方法
就算设置ul的focusout事件，li1移动到li2也会导致focusout被触发
<ul>
<li>111</li>
<li>222</li>
</ul>


```

> 只对部分元素生效
```
focus
The focus event occurs when an element receives focus either via a pointing device or by tabbing navigation. This event is valid for the following elements: LABEL, INPUT, SELECT, TEXTAREA, and BUTTON.
Bubbles: No
Cancelable: No
Context Info: None
-------------------
最后发现给元素设置tabIndex就能让元素能获取焦点了
```


## 参考
- https://developer.mozilla.org/zh-CN/docs/Web/Events/blur
- https://www.w3.org/TR/2000/REC-DOM-Level-2-Events-20001113/events.html#Events-eventgroupings-htmlevents

