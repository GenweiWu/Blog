
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
