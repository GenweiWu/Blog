linear-gradient
==

支持颜色渐变

## 基本用法
```
linear-gradient( 
  [ <angle> | to <side-or-corner> ,]? <color-stop> [, <color-stop>]+ )
  \---------------------------------/ \----------------------------/
    Definition of the gradient line        List of color stops  

where <side-or-corner> = [left | right] || [top | bottom]
  and <color-stop>     = <color> [ <percentage> | <length> ]?
```

## 基础用法(关注css部分)
```html
<h4>单方向</h4>
<section>
  <div class="fill1"></div>
  <div class="fill2"></div>
  <div class="fill3"></div>
  <div class="fill4"></div>
  <div class="default">默认不写方向则是向下的</div>
</section>
<h4>斜方向</h4>
<section>
  <div class="fill5"></div>
  <div class="fill6"></div>
  <div class="fill7"></div>
  <div class="fill8"></div>
</section>

<h4>自定义角度</h4>
<section>
  <div class="fill9">top是0</div>
  <div class="fill10">right是90</div>
  <div class="fill11">bottom是180</div>
  <div class="fill12">left是270或-90</div>
</section>

<h4>多段颜色</h4>
<section>
  <div class="stop1"></div>
  <div class="stop2">多个过渡色</div>
  <div class="stop3">指定"百分比终点"的多个过渡色</div>
  <div class="stop4">指定"长度终点"的多个过渡色</div>
</section>

<h4>玩的hi</h4>
<section>
  <div class="hi1"></div>
  <div class="hi2"></div>
  <div class="hi3">使用calc</div>
</section>
```

```css
section{
  overflow:auto;
}
section>div{
  float:left;
  width:200px;
  height:75px;
  border:1px solid;
}

.fill1{
  background:linear-gradient(to left, yellow,red);
}

.fill2{
 background: linear-gradient(to right,yellow,red)
}
.fill3{
 background: linear-gradient(to top,yellow,red)
}
.fill4{
 background: linear-gradient(to bottom,yellow,red)
}
.default{
  background: linear-gradient(yellow,red)
}
.fill5{
 background: linear-gradient(to top right,yellow,green)
}
.fill6{
 background: linear-gradient(to bottom right,yellow,green)
}
.fill7{
 background: linear-gradient(to bottom left,yellow,green)
}
.fill8{
 background: linear-gradient(to top left,yellow,green)
}
.fill9{
 background: linear-gradient(0deg,yellow,green)
}
.fill10{
 background: linear-gradient(90deg,yellow,green)
}
.fill11{
 background: linear-gradient(180deg,yellow,green)
}
.fill12{
 background: linear-gradient(-90deg,yellow,green)
}
.stop1{
  background:linear-gradient(90deg,white,red)
}
.stop2{
  background:linear-gradient(90deg,white,yellow,red)
}
.stop3{
  background:linear-gradient(90deg,white,yellow 75%,red)
}
.stop4{
  background:linear-gradient(90deg,white,yellow 150px,red)
}

.hi1{
  background:linear-gradient(90deg,blue,white,red)
}
.hi2{
  background:linear-gradient(90deg,blue,transparent,red)
}
.hi3{
  background:linear-gradient(90deg,white,yellow calc(100% - 20px),red)
}
```

![image](https://user-images.githubusercontent.com/16630659/53733460-0333a200-3ebc-11e9-8aba-d558dc12edaa.png)
![image](https://user-images.githubusercontent.com/16630659/53733526-337b4080-3ebc-11e9-91a5-7bbb3772dee8.png)

## 透明

```html
  <h4>完全透明(0)时，颜色没影响</h4>
  <section>
    <div class="test1"></div>
    <div class="test2"></div>
  </section>
  <h4>不完全透明时，颜色有影响</h4>
  <section>
    <div class="test3"></div>
    <div class="test4"></div>
  </section>
  <h4>支持图片+透明</h4>
  <section>
    <div class="light-image1"></div>
    <div class="light-image2"></div>
  </section>
```

```css
div{
  height:30px;
  margin-top:10px;
}
.test1 {
  background: linear-gradient(to right, blue, rgba(0,0,0,0));
}
.test2{
  background: linear-gradient(to right, blue, rgba(255,255,255,0));
}
.test3 {
  background: linear-gradient(to right, blue, rgba(0,0,0,0.3));
}
.test4{
  background: linear-gradient(to right, blue, rgba(255,255,255,0.3));
}


.light-image1{
  height:50px;
   background: url(https://user-images.githubusercontent.com/16630659/53804491-a51dc200-3f82-11e9-9bda-0ccb0bb59611.png);
}
.light-image2{
  height:50px;
   background: linear-gradient(to right,rgba(255,255,255,0), rgba(255,255,255,1)),
     url(https://user-images.githubusercontent.com/16630659/53804491-a51dc200-3f82-11e9-9bda-0ccb0bb59611.png);
}
```

![image](https://user-images.githubusercontent.com/16630659/53804876-9b488e80-3f83-11e9-8c00-7d948ded332f.png)


## 参考资料
- https://developer.mozilla.org/zh-CN/docs/Web/CSS/linear-gradient 
- https://css-tricks.com/snippets/css/css-linear-gradient/
- https://developer.mozilla.org/zh-CN/docs/Web/Guide/CSS/Using_CSS_gradients

## 备忘

> 白毛浮绿水 白F
```
#FFF 白色
#000 黑色
```



