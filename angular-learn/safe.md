

## 1. safeStyle

```ts
import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'safeStyle'
})
export class SafeStylePipe implements PipeTransform {
  constructor(private sanitized: DomSanitizer) { }
  transform(value: any, args?: any): any {
    return this.sanitized.bypassSecurityTrustStyle(value);
  }

}
```

#### 使用
> html
```html
<div [style.background-image]="loginBackgroundImage | safeStyle">
</div>
```

> ts
```ts
this.loginBackgroundImage = "URL('assets/imgs/login.jpg')";
//or server: http:10.11.12.13:8080/test-service/welcome/xx.jpg
this.loginBackgroundImage = "URL('/test-service/welcome/xx.jpg')";
```
