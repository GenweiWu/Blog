## `<input type="file"/>`的不足

1、样式在各个浏览器不统一  
2、无法适配切换中英文功能(除非你的操作系统是英文的)  

## 适配方法1
**利用`label`的`for`功能**

#### :)代码
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

#### :)效果  
![image](https://user-images.githubusercontent.com/16630659/55272580-6ec13180-52f9-11e9-972d-6ea0395284b6.png)

#### :)总结
- 样式有局限性：只能用label,在label下放其他元素都不行
- 移上去，没有`没有选择文件`的提示，这块国际化没法搞

## 适配方法2
**将input-file透明化放在前面**

#### :)代码
```html
  <div class="file-input-wrapper">
    <button>点击上传</button>
    <input id="custom-input" type="file"/>
  </div>
```

```css
.file-input-wrapper {
  position: relative;
  width: 90px;
  overflow: hidden;
}

.file-input-wrapper > input[type="file"] {
  opacity: 0;
  position: absolute;
  top: 0;
  right: 0;
  height: 100%;
  font-size: 20px;
  cursor: pointer;
}

button{
  padding:10px 10px;
}
```


#### :)效果
![image](https://user-images.githubusercontent.com/16630659/55272674-eba0db00-52fa-11e9-8472-834d665608c1.png)


#### :)总结
- 缺点是有移上去的提示无法国际化处理
- 优点是样式调整更方便

