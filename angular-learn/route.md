
## routeLink

#### 1、一般用在a上，但是用在其他元素上也可以，比如
```
<a routerLink="/dashboard">DashBoard</a>
```

```
<ul>
<li *ngFor="let hero of heroes" routerLink="/detail/{{hero.id}}></li>
</ul>
```

#### 2、 [routerLink] vs routerLink
https://stackoverflow.com/a/41371094/6182927
> 前者是用一个对应的变量，可以动态传内容；后者是直接用的内容

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

