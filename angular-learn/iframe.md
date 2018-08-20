angular2中iframe操作

## iframe使用html填充
#### 1)不是使用innerHTML，而是使用src进行实现

> 假设html为内容为
```html
<h1>haha</h1>
```

> (1)直接在html中写
```html
<div class="iframe-test">
    <iframe src="data:text/html;charset=utf-8,<h1>haha</h1>"></iframe>
</div>
```

> (2)使用ts变量
```html
<iframe [src]="htmlContent"></iframe>
```

```ts
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

ngOnInit() {
    let html = "data:text/html;charset=utf-8,<h1>haha</h1>";
    this.htmlContent = this.domSanitizer.bypassSecurityTrustResourceUrl(html);
  }
```

#### 2)注意，使用src="data..."会跨域
[参见](https://stackoverflow.com/questions/19739001/which-is-the-difference-between-srcdoc-and-src-datatext-html-in-an)
```
Iframe with src attribute with HTML Content is cross domain,

But iframe with srcDoc attribute with HTML Content is not cross domain
```

## iframe高度自适应+onload事件+定位到章节
```html
<iframe #contentIframe [src]="contentResource" frameborder="0" on-load="iframeLoaded()"></iframe>
```

```ts
//iframe引用
@ViewChild("contentIframe") contentIframe: ElementRef;

iframeLoaded(){
    console.log("iframeload:" + new Date())
    this.justifyIframeHeight(()=>{
      setTimeout(() => {
        this.jumpToChapter();
      }, 666);
    });    
}

justifyIframeHeight(callback = ()=>{}){
    var thisIframe = this.contentIframe.nativeElement;
    var justifyHeight = thisIframe.contentWindow.document.body.scrollHeight + 50;
    thisIframe.height = justifyHeight + "px";

    callback();
}

private jumpToChapter() {
    let thisIframe = this.contentIframe.nativeElement;
    thisIframe.contentWindow.window.location.hash = "#chapter1";
}      
```    

### 关注点总结
1. iframe的onload事件写法:`on-load`
2. 高度自适应
```ts
    var thisIframe = this.contentIframe.nativeElement;
    var justifyHeight = thisIframe.contentWindow.document.body.scrollHeight + 50;
    thisIframe.height = justifyHeight + "px";
```    
3. 定位到章节
```ts
thisIframe.contentWindow.window.location.hash = "#chapter1";
```
4. 定位到章节，遇到高度只适应会出现不准的问题，建议
   - 高度自适应后再指定定位方法，如例子中回调的实现
   - 设置settimeout延时



