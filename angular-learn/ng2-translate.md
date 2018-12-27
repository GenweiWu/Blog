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

