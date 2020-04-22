
## 说明
```
当一个元素失去焦点的时候 blur 事件被触发。它和 focusout 事件的主要区别是 focusout 支持冒泡。
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

