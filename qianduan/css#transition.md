
## transition的控制

```html
 <div class="area">
   <div class="item">111</div>
   <div class="item">222</div>
 </div>
```

```css
.area{
  display:inline-block;
}

.item{
  padding: 5px 10px;
  border: 1px solid blue;
  background-color: gold;
  margin-top: 5px;
  
  transition:transform 2s;
}

.item:hover{
  transform:translateX(40px);
  background-color: green;
  transition:background-color 2s;
}
```

#### 效果

![transition_20190523](https://user-images.githubusercontent.com/16630659/58231942-cfee0a00-7d6a-11e9-9986-0e470c2ab114.gif)

#### 我的理解
`状态A`=>`状态B`的动画效果，由B对应的`transition`属性控制

![image](https://user-images.githubusercontent.com/16630659/58232178-72a68880-7d6b-11e9-9413-1500901f87a4.png)

