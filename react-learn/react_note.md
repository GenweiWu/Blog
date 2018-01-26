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
---
### 2. react没有分离前后台啊？
你可以这么理解。

---
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

---
### 4.react生命周期事件

关注下:
- constructor
- componentDidMount
- componentWillUnmount

```jsx
import React, {Component} from 'react';

class MyClock extends Component {
    constructor() {    //构造函数
    super();       //写下面的代码前，必须先手动调用super，否则无法正确获取this
      this.state = {
          date:new Date()
      }
    }

    componentDidMount() {   //当组件输出到dom后，适合进行初始化
        this.inClock = setInterval(() => {
            this.setState({date: new Date()})   //使用set方法而不是直接进行赋值，才能正常触发渲染
        }, 1);

    }

    componentWillUnmount() {  //当组件将要卸载前，适合进行销毁操作
        clearInterval(this.inClock);
    }

    render() {
        return (
            <h2>current time is {this.state.date.toLocaleString()}</h2>
        )
    }

}

export default MyClock;
```
