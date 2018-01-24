react学习笔记
==

### 1. render只能返回一个对象，即必须有一个根节点
   这个会报错：
```jsx
ReactDOM.render(
    <h1>Hello world</h1>,
    <h2>current time is</h2>,
document.getElementById("root"));
```

应该是：
```jsx
ReactDOM.render(
    <div>
    <h1>Hello world</h1>
    <h2>current time is</h2>
</div>, document.getElementById("root"));
```

### 2. react没有分离前后台啊？
你可以这么理解。

### 3. react只更新必要的元素，其他不变的不更新,那angular2呢?
参见https://doc.react-china.org/docs/rendering-elements.html

**测试发现：react做的比angular要好一些，只更新最少的元素。**

#### 3.1 react:
```js
function showCurrentDateTime() {
    ReactDOM.render(
        <div>
        <h1>Hello world</h1>
        <h2>current time is: {new Date().toLocaleString()}</h2>
    </div>, document.getElementById("root"));
}

setInterval(showCurrentDateTime, 1);
```

改变的只有时间本身，连`current time is`都不变
```
2018/1/24 上午10:35:49
```

#### 3.2 angular
```js
<div class="content-area">
  <h2>current time is: {{title}}</h2>
</div>
```

改变的包括h2和其中的内容，包括`current time is:`和时间
