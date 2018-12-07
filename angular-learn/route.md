## debug
```ts
 RouterModule.forRoot(myRoutes, { enableTracing: true })
```

## routeLink

### 1、一般用在a上，但是用在其他元素上也可以，比如
```
<a routerLink="/dashboard">DashBoard</a>
```

```
<ul>
<li *ngFor="let hero of heroes" routerLink="/detail/{{hero.id}}></li>
</ul>
```

### 2、 [routerLink] vs routerLink
https://stackoverflow.com/a/41371094/6182927
> 前者是用一个对应的变量，可以动态传内容；  
> 后者是直接用的内容

### 3、queryParam

```
<ul>
    <li><a href="" routerLink="/module-test" [queryParams]="{n:1}">11</a></li>
    <!-- href="/module-test?n=1" -->
    <li><a href="" routerLink="/module-test" [queryParams]="{n:1,m:2}">22</a></li>
    <!-- href="/module-test?n=1&m=2" -->
    <li><a href="" routerLink="/module-test" [queryParams]="{n:1,m:2,k:3}">33</a></li>
    <!-- href="/module-test?n=1&m=2&k=3" -->
</ul>
```

对应的参数读取方法：
```ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'document-preview',
  templateUrl: './document-preview.component.html',
  styleUrls: ['./document-preview.component.css']
})
export class DocumentPreviewComponent implements OnInit {

  constructor(private activatedRoute:ActivatedRoute) { }

  ngOnInit() {
    //参数读取
    var queryParams = this.activatedRoute.snapshot.queryParams;
    debugger;
  }

}
```

### 4、Path Param

路由的写法
```ts
const routes: Routes = [
  {
    path: 'article-view/:articleId',
    component: ArticleViewComponent
  }
];
```

读取参数
```ts
export class ArticleViewComponent implements OnInit {

  constructor(private activeRoute:ActivatedRoute) { }

  ngOnInit() {
    this.activeRoute.snapshot.params["articleId"]
  }

}
```



## roure in ts

> ts中使用`router.navigate`或`router.navigateByUrl`方法
```ts
import { Router } from '@angular/router';

...
//in your constructor
  constructor(public router: Router){}

//navigation link.
this.router.navigateByUrl('/login');
```

## 不改变url
参考：https://stackoverflow.com/a/37055297/6182927
```ts
router.navigateByUrl("/team/33/user/11", { skipLocationChange: true });
```

```html
<a [routerLink]="..." skipLocationChange>click me</a>
```

## 两种路由方式

- 直接路由component
- 路由module，再路由component
参考：https://angular.io/guide/lazy-loading-ngmodules

> app-routing.module.ts
```ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SelectDemoComponent } from './select-demo/select-demo.component';

const allRoutes : Routes = [
  {
    path: 'ds-select',
    component: SelectDemoComponent
  },
  {
    path:'module-test',
    loadChildren:'./hello-route/hello-route.module#HelloRouteModule'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(allRoutes)
  ],
  exports:[RouterModule],
  declarations: []
})
export class AppRoutingModule { }
```

> hello-route.module.ts
```ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { HelloRouteComponent } from './hello-route.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([{
      path:'',
      component: HelloRouteComponent
    }])
  ],
  declarations: [HelloRouteComponent]
})
export class HelloRouteModule { }
```

