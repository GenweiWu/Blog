
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

### 2. ng g without test

to generate a component without a "...spec.ts" file   
you simply run "--spec false". Example below
```
ng g c testfile --spec false
```


## angular中css的使用

### 1、css中引用图片资源，建议用绝对路径(错，是从根目录开始的相对路径)

[Maybe the easiest workaround is to insert the full path in the css](https://stackoverflow.com/a/35744829/6182927)
```css
/*(1)相对路径，不好维护*/   
.sample { background-image: url(../img/bg.jpg); }

/*(2)部署后有问题*/
.sample { background-image: url(/assets/plugins/test/img/bg.jpg); }

/*(3)本地编译报错*/
.sample { background-image: url(./assets/plugins/test/img/bg.jpg); }
```
#### - 问题
> index.html
```html
    <!-- <base href="/test-service/"> -->
    <base href="/">
```
由于index.html中的开发时和部署后的href的差别，如果写成(2)的绝对路径是不行,在环境上不会自动加上`/test-service`，会有问题

#### - 结论
css使用相对路径(1)
```css
.title-icon {
  background-image: url('../../../assets/imgs/hi_icon.png') -79px -209px no-repeat;
  width: 50px;
  height: 28px;
  float: left;
}
```

html使用绝对路径
```html
<div>
<img src="assets/imgs/111.png>
</div>
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

