
## 官方资料
https://angular.cn/guide/quickstart

## angular/cli
https://github.com/angular/angular-cli

```
npm uninstall -g @angular/cli
npm uninstall --save-dev @angular/cli
npm cache clean
npm install -g @angular/cli@latest
npm install --save-dev @angular/cli@latest
```

### 1、ng generaete xxx -d 可以让你提前知道效果，但不会真的生成
```
D:\daveCode\adc-demo222>ng generate component hello-route -d
installing component
You specified the dry-run flag, so no changes will be written.
  identical src\app\hello-route\hello-route.component.css
  identical src\app\hello-route\hello-route.component.html
  identical src\app\hello-route\hello-route.component.spec.ts
  identical src\app\hello-route\hello-route.component.ts
  update src\app\app.module.ts
```

## angular中css的使用

### 1、css中引用图片资源，建议用绝对路径

[Maybe the easiest workaround is to insert the full path in the css](https://stackoverflow.com/a/35744829/6182927)
```
.sample { background-image: url(/assets/plugins/test/img/bg.jpg); }
instead of

.sample { background-image: url(../img/bg.jpg); }
```

## 枚举值enum的使用

> TypeEnum.ts
```ts
export enum TypeEnum{
    one,
    two
}
```

为了在template或者说html中使用，你需要

> component中
```ts
import {TypeEnum} from '../TypeEnum';

...
thisType:TypeEnum = TypeEnum.two;
//注意下面这里，为了让前台认识TypeEnum
TypeEnum = TypeEnum;
...
```

> html中
```html
<div *ngIf="thisType === TypeEnum.one">one</div>
<div *ngIf="thisType === TypeEnum.two">two</div>
```

## ts中的对象类型 interface VS class
```
interface用于进行类型检查，但是它无法创建对象(使用new的方式)
class也可用于类型检查，而且可以用于创建对象

所以，如果单纯的类型检查就用interface，否则就用class
```

参考 https://toddmotto.com/classes-vs-interfaces-in-typescript

