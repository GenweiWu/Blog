
## 基础

`animation-name` 动画名称  
`animation-duration` 动画持续的时间 (默认值为0s，表示无动画。)  
`animation-timing-function` 动画速度曲线，如`linear`,`ease-in`,`ease-out` (初始值是ease)  
`animation-delay` 动画延时，指动画加载完成到开始动画序列的时间  (初始值0s)  
`animation-iteration-count` 动画重复的次数,如`infinite`表示无限重复  (初始值为1)  
`animation-direction` 动画方向,主要有`normal`,`reverse`,`alternate`,`alternate-reverse`  (初始值为normal)  
`animation-fill-mode` 动画开始前以及动画结束后，应用的元素的展示状态，主要有`none`,`forwards`,`backwards`,`both`  (默认值是none)  
`animation-play-state` 动画播放状态，主要有`paused`,`running`  (默认值是running)  

## 详细描述

### :) animation-direction 
> 测试信息
```css
.main{
  /* animation: 3s ease-in 1s infinite reverse both running slidein; */
    animation-name: slidein;
    animation-duration: 1.5s;
    animation-timing-function: ease-in;
    animation-delay: 2s;
    animation-iteration-count: 2;
    animation-direction: alternate;
    animation-fill-mode: none; 
    animation-play-state: running;
}
```

-  normal 向右跑了两次  
![direction_normal](https://user-images.githubusercontent.com/16630659/59479614-4379c780-8e90-11e9-8f3d-61565c2de7d9.gif)
- reverse 向左跑了两次  
![direction_reverse](https://user-images.githubusercontent.com/16630659/59479611-42e13100-8e90-11e9-8e70-a1dbf4a54f6d.gif)
- alternate 先向右，又向左弹回来  
![direction_alternate](https://user-images.githubusercontent.com/16630659/59479612-42e13100-8e90-11e9-8f77-ae77d6a70a72.gif)
- alternate-reverse  先向左，又向右弹回来  
![direction_alternate_reverse](https://user-images.githubusercontent.com/16630659/59479613-4379c780-8e90-11e9-8c36-d4eb3970219f.gif)


### :) animation-fillmode
> 测试信息
```css
.main{
    margin: 20px;
    margin-top:50px;
    border: 5px solid #333;
    width: 100px;
    height: 100px;
    border-radius: 50%;
}

.main{
  /* animation: 3s ease-in 1s infinite reverse both running slidein; */
    animation-name: slidein;
    animation-duration: 1.5s;
    animation-timing-function: ease-in;
    animation-delay: 3s;
    animation-iteration-count: 1;
    animation-direction: normal;
    animation-fill-mode: forwards;
    animation-play-state: running;
}

@keyframes slidein {
 0% {margin-left: 0%;background-color:red}
 100% {margin-left: calc(100% - 100px - 5px);background-color:yellow}
}
```

> 我的理解

![image](https://user-images.githubusercontent.com/16630659/59480289-a5d3c780-8e92-11e9-995d-1a206229a44a.png)

> demo

- none (空->动画->空)  
![fillmode_none](https://user-images.githubusercontent.com/16630659/59480997-33b0b200-8e95-11e9-8426-5685c71c65c5.gif)
- forward (空->动画->黄色)  
![fillmode_forwards](https://user-images.githubusercontent.com/16630659/59480998-33b0b200-8e95-11e9-882a-653523f07e70.gif)
- backwards (红色->动画->空)  
![fillmode_backwards](https://user-images.githubusercontent.com/16630659/59480999-34494880-8e95-11e9-929f-b5700a3f8f62.gif)
- both (红色->动画->黄色)  
![fillmode_both](https://user-images.githubusercontent.com/16630659/59481000-34494880-8e95-11e9-8fe4-79421bbe24e5.gif)


## 样例

```html
  <section>
    <div class="main"></div>
  </section>
```

```css
.main{
  background-color: #1766aa;
    margin: 20px;
    border: 5px solid #333;
    width: 150px;
    height: 150px;
    border-radius: 50%;
}

.main{
  /* animation: 3s ease-in 1s infinite reverse both running slidein; */
    animation-name: slidein;
    animation-duration: 3s;
    animation-timing-function: ease-in;
    animation-delay: 0.1s;
    animation-iteration-count: infinite;
    animation-direction: normal;
    animation-fill-mode: none;
    animation-play-state: running;
}

@keyframes slidein {
 0% {margin-left: 0%;}
 100% {margin-left: 80%;}
}
```

![animation](https://user-images.githubusercontent.com/16630659/59418639-f21ff880-8dfb-11e9-8771-3fccb3da8088.gif)


## 参考
- https://developer.mozilla.org/zh-CN/docs/Web/CSS/animation
- https://developer.mozilla.org/zh-CN/docs/Web/CSS/CSS_Animations/Using_CSS_animations

