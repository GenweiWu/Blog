## 资料
- [ts中文网](https://www.tslang.cn/docs/handbook/classes.html)


## 编译
#### a)单文件编译测试 
`tsc --target es5 hello.ts --watch`

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

### @input中使用enum枚举

> 定义枚举
```
export enum Mode {
  new_button,
  edit ,
  view
}
```

> 父组件.component.html
```
<permission-card [mode]="Mode.new_button"></permission-card>
```

> 父组件.component.ts
```ts
  Mode = Mode;
  constructor(
  ) { }
```

> 子组件.component.ts中
```
  @Input() mode: Mode;
  Mode = Mode;
```

> 子组件.component.html中
```html
<div class="card" *ngIf="mode==Mode.new_button">
    ...
```


## ts中的对象类型 interface VS class
```
interface用于进行类型检查，但是它无法创建对象(使用new的方式)
class也可用于类型检查，而且可以用于创建对象

所以，如果单纯的类型检查就用interface，否则就用class
```

参考 https://toddmotto.com/classes-vs-interfaces-in-typescript


## 类型定义`type`
```ts
export interface SelectOption {
  label: string;
  value: string;
}

export type SelectOptions = SelectOption[];
```

## 泛型
```ts
export class BranchResult<T> {
    responseCode: string;
    responseBody: T[];
}
```

## 方法变量限制
```ts
public getInfo(param: any, callBack: ((x: (InfoResult<string>)) => any)): any {
    var _callback = callBack || (() => { });
    var url = "/rest/getInfo";

    this.rsc.post(url, param, result => {
      _callback(result.json());
    });
  }
```

## js类型判断
```js
 typeof _temp === 'string'
 
 _temp instanceof Array
```
