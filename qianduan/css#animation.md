
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
- TODO 

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

