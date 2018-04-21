Vue counter
==

### 一、功能介绍

[>>> 演示地址](https://jsfiddle.net/GenweiWu/2Laux27u/)

1. 单个计数器：通过点击`<`来减少，通过点击`>`来增加
2. 两个计算器的综合显示在上方
3. 可以设置每个计数器的初始值
4. 可以设置每个计算器的`step`，来决定每次增加或减少的幅度(+n/-n)

---
### 二、知识
#### 1. 父组件通过 `props` 传递数据给子组件

- 父组件中通过 `v-bind:value` 的方式传递值
```html
<counter @count="doCount1" :init-value="count1" :step="2"></counter>
```

- 子组件中定义props来获取，另外这些参数支持默认值(还有类型检测)
```js
props:{
  'initValue':Number,
  'step':{
    default: 1,
    type: Number
  }
}
```

#### 2. 子组件通过 `$emit` 传递消息给父组件

- 子组件在触发修改后，手工调用`$emit`方法
```js
//其中"count"是触发的事件名称,{countValue:xxx}是传递的参数内容
methods:{
  increase:function(){
    this.count += this.countStep;
    this.$emit("count",{countValue:this.count});
  },
  decrease:function(){
    this.count -= this.countStep;
    this.$emit("count",{countValue:this.count});
  }
}
``` 

- 父组件捕获对应的事件，在方法中读取对应的参数内容
```html
//注意这里的方法就是count
<counter @count="doCount1" :init-value="count1" :step="2"></counter>
```

```js
methods:{
  doCount1:function(_c){
  //这里读取参数
    this.count1 = _c.countValue;
  }
}
```



