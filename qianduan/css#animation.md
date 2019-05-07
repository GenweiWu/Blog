
## 霓虹灯效果

![nihongdeng](https://user-images.githubusercontent.com/16630659/56943599-d1088e80-6b52-11e9-8203-42dd938e5ab4.gif)

```html
  <div>
    <span>点点</span>
    <span>试试</span>
    <span>刚刚</span>
    <span>哈哈</span>
    <span>请求</span>
    <span>恩恩</span>
    <span>天天</span>
  </div>  
```

```css
div{
  background-image: linear-gradient(to right, red, orange, yellow, green, yellow, orange, red, orange, yellow, green, yellow, orange, red);
  -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    -webkit-background-size: 200% 100%;
  animation:bgp 5s infinite linear;
}

@keyframes bgp
{
 from {    background-position: 0 0; }
  to {      background-position: -100% 0; }
}
```

## 点赞效果

![like](https://user-images.githubusercontent.com/16630659/57270195-e898c780-70bc-11e9-8b16-3b0d9186caa8.gif)

```html
<script src="https://code.jquery.com/jquery-3.0.0.js"></script>
  
  <div id="area">
    <i>+1</i><span>16</span>
  </div>
```

```css
.increase i{
  display:block;
}

i {
  position: absolute;
  z-index: 2;
  display: none;
  width: 20px;
  opacity: 0;
  top: 0;
  -webkit-animation: bar_plus 1s ease;
  vertical-align: top;
  color:red;
}

@-webkit-keyframes bar_plus {
    0% {
        opacity: 0;
        -webkit-transform: scale(0)
    }
    50% {
        opacity: 1
    }
    60% {
        opacity: 1
    }
    100% {
        opacity: 0;
        -webkit-transform: scale(1.4)
    }
}
```

```js
$("#area").click(function(){
  
  $("#area").removeClass('increase');
  
  var tId=setTimeout(function(){
    $("#area").addClass('increase');
    
    clearTimeout(tId);
  },0);
  
})
```
