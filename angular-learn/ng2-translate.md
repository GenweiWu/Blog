angular2国际化插件

## 资料
https://www.npmjs.com/package/ng2-translate  
https://github.com/ngx-translate/core  

## instant加载问题

#### 1) 问题描述

> 比如在ngInit方法使用instant方法来初始化菜单信息,但是偶现菜单国际化失败的问题(显示的还是key而不是国际化)

```html
<div *ngFor="let menu of contentMenuList">
    <div (click)="changeContentMenu(menu.id)">{{menu.name}}</div>
</div>
```

```ts
this.contentMenuList = [
  { "id": "article", "name": this.translate.instant('app.home-page.menu-article') },
  { "id": "question", "name": this.translate.instant('app.home-page.menu-question') },
  { "id": "essence", "name": this.translate.instant('app.home-page.menu-essence') }
];  
```

#### 2) 问题原因
因为instant方法是同步的,执行instant方法的时候,可能国际化还没加载完成,就会出现这个问题.

#### 3) 规避

- 方法一(推荐)

```html
<div *ngFor="let menu of contentMenuList">
    <div (click)="changeContentMenu(menu.id)">{{menu.name | translate}}</div>
</div>
```

```ts
this.contentMenuList = [
  { "id": "article", "name": 'app.home-page.menu-article' },
  { "id": "question", "name": 'app.home-page.menu-question' },
  { "id": "essence", "name": 'app.home-page.menu-essence' }
];  
```

- 方法二
```ts
// 解决加载国际化失败的问题
this.translate.get('app.home-page.menu-article').subscribe(e => {
  this.contentMenuList = [
    { "id": "article", "name": this.translate.instant('app.home-page.menu-article') },
    { "id": "question", "name": this.translate.instant('app.home-page.menu-question') },
    { "id": "essence", "name": this.translate.instant('app.home-page.menu-essence') }
  ];
});
```

## 参数

### html中使用参数

> zh.json
```zh.json
"articleToMain": "{{count}}s后跳转到首页"
```

> html
```html
<b>
({{'articleToMain'|translate:{count:leftTime} }})
</b>
```
[注意:leftTime后的大括号后要有空格](https://stackoverflow.com/a/46593334)

> ts
```ts
leftTime:number = 5;
```

### TS中使用参数
> zh.json
```json
"articleToMain": "{{count}}s后跳转到首页"
```

> ts
```ts
var param = {count:"111"};
this.leftTime = this.translate.instant("articleToMain",param);
```







