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
