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


## 类型定义`type`
```ts
export interface SelectOption {
  label: string;
  value: string;
}

export type SelectOptions = SelectOption[];
```
