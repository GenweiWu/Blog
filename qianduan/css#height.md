为什么height:100%不生效
==

## 1、给html,body设置height:100%

> html
```html
<!DOCTYPE html>
<html>
<body>
   <div class="area"></div>
</body>
</html>
```
> css
```css
.area{
  height:100%;
}
```
此时高度是0  
![image](https://user-images.githubusercontent.com/16630659/52607087-f66cf100-2eaf-11e9-8092-b033267752f3.png)

### 正确方法是要设置html,body的高度为100%
> css
```css
html,body{
  height:100%;
}

.area{
  height:100%;
}
```

## 2. 为什么height:100%不生效?

包含块的高度没有显式指定(高度由它的内容决定),且该元素不是绝对定位,则height会被置为auto
> The percentage is calculated with respect to the height of the generated box's containing block. If the height of the containing block is not specified explicitly (i.e., it depends on content height), and this element is not absolutely positioned, the value computes to auto. A percentage height on the root element is relative to the initial containing block.  

- 显示指定包括:12px或者可以生效的百分比
- 上面的`.area`的100%一开始不生效,是因为html,body没有设置高度,此时的百分比是不生效的

## 3. 让height:100%生效的两种方法

> https://jsfiddle.net/GenweiWu/wzkqLyxd/  
```html
<div class="parent">
  <div class="inner"></div>
</div>

<div class="parent fix1">
  <div class="inner"></div>
</div>

<div class="parent">
  <!-- 需要有元素来撑起高度，否则fix2的高度还是0 -->
  <div style="height:100px"></div>
  <div class="inner fix2">
  </div>
</div>
```

```css
.parent {
  margin-top: 20px;
  border: 1px solid blue;
  position: relative;
}

.inner {
  height: 100%;
  background: orange;
}

.fix1 {
  height: 100px;
}

.fix2 {
  /* 设置absolute时建议父元素relative一下 */
  position: absolute;
  /*  此时width为0，所以需要设置100% */
  width: 100%;
}
```

### 3.1 显式设置高度
- 参考fix1的方法,显式设置高度,则inner的height:100%就能生效
 `parent的高度(非auto) * 100%` 

### 3.2 设置绝对定位（postion:absolute或postion:fixed）
- 如果父元素的高度是auto，即它的高度是子元素撑起来的，则可以设置`postion:absolute`来让他实现height:100%的效果
- 个人理解：这里的父元素的描述不太准确，应该叫做`containing block`(单独研究)

## 4. 非absolute和absolute元素的heigh:100%的区别

#### 参考:https://jsfiddle.net/GenweiWu/9bzrcd0e/ 

父元素的盒模型如下：   
![image](https://user-images.githubusercontent.com/16630659/52611802-30df8980-2ec2-11e9-9123-9e8b8d5b360b.png)

- 绝对定位元素的高度=padding-box 
- 非绝对定位元素的高度=content-box  
![image](https://user-images.githubusercontent.com/16630659/52611866-78feac00-2ec2-11e9-884c-69e075e353c1.png)
![image](https://user-images.githubusercontent.com/16630659/52611880-83b94100-2ec2-11e9-95a1-66e9da560a79.png)


## 5. 参考
- https://developer.mozilla.org/zh-CN/docs/Web/CSS/height
