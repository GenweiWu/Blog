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
```js
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
