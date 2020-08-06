## Named Router简介
支持多个路由同时存在，包括路由中的路由。  
Named Router支持对路由进行命名，从而可以实现在同一个component中可以同时存在多个`router-outlet`进行路由展示

## Names Router的使用

> router配置
```ts
const myRoutes: Route[] = [
  {
    path: "outerlet",
    component: OuterletDemoComponent,
    children: [
      {
        path: "innerHello",
        component: HelloComponent,
        outlet: "inner"
      },
      {
        path: "innerMargin",
        component: MarginAutoDemoComponent,
        outlet: "inner"
      },
    ]
  },
  {
    path: "hello",
    component: HelloComponent
  },
  {
    path: "margin",
    component: MarginAutoDemoComponent
  }
]

...
imports: [
    BrowserModule,
    RouterModule.forRoot(myRoutes, { enableTracing: true })
  ],
```

> OuterletDemoComponent.html   
> 在html中通过routerLink跳转  
```html
<h2>
  outerlet-demo works!
</h2>

<div>
  <span>html router</span>
  <ul>
    <li [routerLink]="['/hello']">hello</li>
    <li [routerLink]="['/outerlet', {outlets: {'inner':['innerHello']}}]">inner hello</li>
    <li [routerLink]="['/outerlet', {outlets: {'inner':['innerMargin']}}]">inner margin</li>
  </ul>
</div>

<div>
  <span>ts router</span>
  <ul>
    <li (click)="gotoHello()">hello</li>
    <li (click)="gotoInnerHello()">inner hello</li>
    <li (click)="gotoInnerMargin()">inner margin</li>
  </ul>
</div>

<router-outlet name="inner"></router-outlet>
```


> OuterletDemoComponent.ts  
> 在ts中使用router路由  
```ts
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-outerlet-demo',
  templateUrl: './outerlet-demo.component.html',
  styleUrls: ['./outerlet-demo.component.css']
})
export class OuterletDemoComponent implements OnInit {

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
  }

  gotoHello() {
    this.router.navigateByUrl("/hello");
  }

  gotoInnerHello() {
    this.router.navigateByUrl("/outerlet/(inner:innerHello)");
  }

  gotoInnerMargin() {
    this.router.navigateByUrl("/outerlet/(inner:innerMargin)");
  }
}
```

#### 扩展：使用queryParam传参数
```ts
    let param  = {"fileId":'xxx'};
    this.router.navigate(['/outerlet', {outlets: {'inner':['innerMargin']}}],{queryParams:param,skipLocationChange:true});
```

### 效果
- 点击hello是覆盖了整个页面，而inner则只覆盖的下半部分
- 点击时可以看到浏览器中的url变化，对应的ts中实现就是对应的url
![](./img/namedRouter.gif)

### 个人总结
1. 内部路由，需要在配置路由时，设置为children，且需要设置outlet属性
2. 路由链接(无论是html方式还是ts方式)则需要制定对应的outlet名称
3. forChild写法中，我测试的结果是只能写在`path`非空的下面,为空的我没试成功
```ts
    RouterModule.forChild([
      {
        path: '',
        redirectTo: "content",
        pathMatch:"full"
      },
      {
        path: 'content',  //<---  这里不能是空的 path:''
        component: HelloComponent,
        children: [
          {
            path: 'innerPreview',
            outlet: "previewOutlet",
            component: ProxyRouteComponent,
            children:[
              {
                path:'',
                loadChildren: 'xxx#xxxModule'
              }
            ]
          }
        ]
      }
    ])
```


## 扩展：NamedRouter with lazy load
即在module中使用Named Router

### 1）需要用一个中转component:`ProxyRouteComponent`
参考：https://github.com/angular/angular/issues/12842

```ts
{
    path: 'me',
    outlet: 'hub',
    component: ProxyRouteComponent,
    children: [
        {
            path: '',
            loadChildren: 'hello#HelloModule',
        },
    ],
},
```    
Where proxy route component is simply

> proxy-route.component.ts
```
import { Component } from '@angular/core';

@Component({
    selector: 'b-proxy-route',
    template: '<router-outlet></router-outlet>',
})
export class ProxyRouteComponent {
}
```

对应的链接是 ...xxx`/(hub:me)`

### 2）component被多个module使用会报错

上面的`ProxyRouteComponent`被多个module使用会遇到一个报错：
```
Using multiple components in different modules causing "Type X is part of the declarations of 2 modules" error
```
#### 需要用module包一层
参考：https://github.com/angular/angular/issues/10646

解决的方法是，用一个module包一层
> proxy-route.module.ts
```proxy-route.module.ts
import {NgModule}      from '@angular/core';
import { ProxyRouteComponent } from './proxy-route.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
      RouterModule   //<==需要引用这个，否则不认识router-outlet元素
  ],
  exports:      [ ProxyRouteComponent ],
  declarations: [ProxyRouteComponent]
})
export class ProxyRouteModule {

}
```

- 引用的使用，改import ProxyRouteModule，不再declarations ProxyRouteComponent  
- 引入module，可以直接使用module中的component

> Xxx.module.ts
```Xxx.module.ts
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { XxxComponent } from './xxx.component';
import { ProxyRouteComponent } from '../common/proxy-route.component';
import { ProxyRouteModule } from '../common/proxy-route.module';

@NgModule({
  imports: [
    ProxyRouteModule,   //<==引入module
    RouterModule.forChild([
      {
        path: '',
        redirectTo: "content",
        pathMatch:"full"
      },
      {
        path: 'content',
        component: XxxComponent,   //<== 这里配置子路由
        children: [
          {
            path: 'innerPreview',
            outlet: "previewOutlet",
            component: ProxyRouteComponent,   //<==这里直接使用component
            children:[
              {
                path:'',
                loadChildren: '../hello.module#HelloModule'
              }
            ]
          }
        ]
      }
    ])
  ],
  declarations: [],  //<==不需要引用component了
  providers:[]
})
export class XxxModule { }

```

## 如何传递参数queryParams

- html中的写法
> hello-route.component.html
```html
<div>
  <span>html router</span>
  <ul>
    <li [routerLink]="['/hello']">hello</li>
    <li [routerLink]="['/module-test/xx', {outlets: {'inner':['innerPreview']}}]" [queryParams]="{id: 1}">inner innerPreview111</li>
    <li [routerLink]="['/module-test/xx', {outlets: {'inner':['innerPreview']}}]" [queryParams]="{id: 2}">inner innerPreview222</li>
  </ul>
</div>

<div>
  <span>ts router</span>
  <ul>
    <li (click)="gotoHello()">hello</li>
    <li (click)="gotoInnerPreview111()">inner gotoInnerPreview111</li>
    <li (click)="gotoInnerPreview222()">inner gotoInnerPreview222</li>
  </ul>
</div>

<router-outlet name="inner"></router-outlet>
```

- ts中的写法

> hello-route.component.ts
```ts
...
 gotoHello() {
    this.router.navigateByUrl('/hello');
  }

  gotoInnerPreview111() {
    this.router.navigate(
      [
        '/module-test/xx',
        {outlets: {'inner': ['innerPreview']}}
      ],
      {
        queryParams: {id: 1},
        // skipLocationChange: false
      }
    );
  }

  gotoInnerPreview222() {
    this.router.navigate(
      [
        '/module-test/xx',
        {outlets: {'inner': ['innerPreview']}}
      ],
      {
        queryParams: {id: 2},
        // skipLocationChange: false
      }
    );
  }
...
```

> hello-route.module.ts
```ts
...
RouterModule.forChild([
      {
        path: 'xx',
        component: HelloRouteComponent,
        children: [
          {
            path: 'innerPreview',
            outlet: 'inner',
            component: ProxyRouteComponent,   //<==这里直接使用component
            children: [
              {
                path: '',
                loadChildren: '../hello/hello.module#HelloModule'
              }
            ]
          }
        ]
      },
      {
        path: 'hello',
        component: HelloComponent
      }
    ])
...
```


## 参考
- https://stackoverflow.com/a/38038733
- https://onehungrymind.com/named-router-outlets-in-angular-2/
