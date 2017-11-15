触发window.resize事件
--

1. window的resize方法，会在窗口大小发生变化时被触发。

2. 如果要手动触发，实现方法如下：

```js
  try {
  
       //以下支持chrome浏览器
        window.dispatchEvent(new Event('resize'));
      } catch (e) {
      
       //以下为了兼容ie11浏览器
        var event = document.createEvent("Event");
        event.initEvent("resize", false, true);
        // args: string type, boolean bubbles, boolean cancelable
        window.dispatchEvent(event);
      }


```
