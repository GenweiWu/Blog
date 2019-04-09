img
==


### 1. img的alt属性和title属性的区别

```
（1）区别
alt是用来替代图片展示，常用来在图片无法正常展示时显示 (只在图片展示不出来时显示) 
而title是在图片上展示解释性文字 (无论图片是否正常展示)
```
[>>>例子](https://jsfiddle.net/GenweiWu/9ywk8onw/)

```
（2）兼容性
不同浏览器的兼容性不同，在ie7(包括)之前ie把alt当做title在用。
```
  > Note: Internet Explorer 7 (and earlier) displays the value of the alt attribute as a tooltip, when mousing over the image. This is NOT  the correct behavior, according to the HTML specification.

### 加载失败，则加载默认图片

```html
<img (error)="loadImgError($event)">
```

```ts
  public loadImgError(_event) {
    let _this = _event.currentTarget;

    const defaultImge = "assets/imgs/broken_image.png";
    //避免默认图片加载失败，出现死循环加载
    if (_this.src.indexOf(defaultImge) == -1) {
      //let _style = getComputedStyle(_this, null);
      //let _width = _style.getPropertyValue('max-width');
      //let _height = _style.getPropertyValue('max-height');
      //_this.style.width = _width;
      //_this.style.height = _height;
      _this.src = defaultImge;
      //_this.title = this.translate.instant("app.common.img.loadFailed");
    }
  }
```
