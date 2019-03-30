## `<input type="file"/>`的不足

1、样式在各个浏览器不统一  
2、无法适配切换中英文功能(除非你的操作系统是英文的)  

## 适配方法1
**利用`label`的`for`功能**

```html
  <input type="file" name="file" id="file" class="inputfile"/>
  <label for="file">点击上传</label>
```

```css
.inputfile {
  /* visibility: hidden etc. wont work */
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}

.inputfile:focus + label {
  /* keyboard navigation */
  outline: 1px dotted #000;
  outline: -webkit-focus-ring-color auto 5px;
}

.inputfile + label * {
  pointer-events: none;
}

label {
  display: inline-block;
  padding: 10px 15px;
  border: 1px rgba(43, 52, 82, 0.2) solid;
  border-radius: 4px;
  background-color: rgba(34, 150, 255, 0.05);
}
```

效果  
![image](https://user-images.githubusercontent.com/16630659/55272580-6ec13180-52f9-11e9-972d-6ea0395284b6.png)


