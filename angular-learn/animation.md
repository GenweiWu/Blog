动画效果 Animation
==

## 1.使用 stagger() 来实现在每个动画之间延迟 xx毫秒

### 1.1 说明
[参考](https://stackoverflow.com/a/45360755)
```ts
trigger('myAwesomeAnimation', [
  transition(':enter', [
    query(':leave', [
      style({transform: 'scale(0.8)',}),
      stagger(100, [
        animate('1.5s ease-out', style({transform: 'scale(1)',})),
      ])
    ]),
    ...
  ]),
])
```

### 1.2 样例

![animation1](https://user-images.githubusercontent.com/16630659/54354618-13086e80-4692-11e9-9056-9be1768e3822.gif)

[样例1](https://stackblitz.com/edit/angular-list-animations-stagger)  
```html
<h2>Angular Animations In Lists</h2>
<button (click)="toggle()">Show / Hide Items</button>
<button (click)="reload()">Reload Items</button>
<button (click)="reload222()">setTimeout Reload Items</button>


<hr />
<!-- 下面这样是不行，没有动态效果 -->
<!-- <div @listAnimation -->
<div [@listAnimation]="items.length"
    (@listAnimation.start)="logAnimation($event)"
    (@listAnimation.done)="logAnimation($event)"
>
  <div *ngFor="let item of items">
    <div>{{ item }}</div>
  </div>
</div>
```

```ts
import { Component, OnInit } from '@angular/core';
import { trigger, transition, style, animate, query, stagger } from '@angular/animations';


@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  animations: [
    trigger('listAnimation', [
      transition('* => *', [ // each time the binding value changes
        query(':leave', [
          stagger(100, [
            animate('0.5s', style({ opacity: 0 }))
          ])
        ], { optional: true }),
        query(':enter', [
          style({ opacity: 0 }),
          stagger(100, [
            animate('0.5s', style({ opacity: 1 }))
          ])
        ], { optional: true })
      ])
    ])
  ],
  styleUrls: ['./app.component.css'],
})


export class AppComponent {
  items = [];


  logAnimation(_event) {
    console.log(_event)
  }
  showItems() {
    // [0, 1, 2, 3, 4, 6, 7, 8, 9, 10].map((i) => {
    //   this.items.push("User Number - " + i)
    // })

    this.items = [0, 1, 2, 3, 4, 6, 7, 8, 9, 10];

  }

  hideItems() {
    this.items = [];
  }

  toggle() {
    this.items.length ? this.hideItems() : this.showItems();
  }

  reload() {
    this.items = [1, 2, 3, 4, 5, 6];
  }

  reload222() {
    this.items = [];
    setTimeout(() => {
      this.items = [1, 2, 3, 4, 5, 6];
    }, 100)
  }
}
```

### 1.2.1 注意:不要直接写在组件上，写在父层div上
> 成功
```html
<div [@listAnimation]="sectionExperts">
      <div *ngFor="let item of items">
         <card [item]="item"></card>
      </div>
</div>
```

> 失败
```html
<div [@listAnimation]="sectionExperts">
   <card [item]="item" *ngFor="let item of items"></card>
</div>
```

## 2、keyframes动画

![animation22](https://user-images.githubusercontent.com/16630659/54354617-13086e80-4692-11e9-904c-4ed26404be58.gif)

[在线样例](https://stackblitz.com/edit/angular-list-animation-keyframes)

```html
<button (click)="toggle()">toggle</button>

<div [@listAnimation]="nameList" style="overflow:hidden;">
	<div *ngFor="let name of nameList">
		<hello name="{{ name }}"></hello>
	</div>

  <!-- 这个不行，可以展示，但是没有动画效果 -->
  <!-- <hello name="{{ name }}" *ngFor="let name of nameList"></hello> -->
</div>
```

```ts
import { Component } from '@angular/core';
import { trigger, transition, style, animate, query, stagger, keyframes } from '@angular/animations';

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [
    trigger('listAnimation', [
      transition('* => *', [ // each time the binding value changes

        query(':enter', [
          style({ transform: 'translateX(-100%)' }),
          stagger(400, [
            animate(600, keyframes([ // 回弹的效果
              style({ opacity: 0, transform: 'translateX(-100%)', offset: 0 }),
              style({ opacity: 1, transform: 'translateX(15px)', offset: 0.3 }),
              style({ opacity: 1, transform: 'translateX(0)', offset: 1.0 })
            ]))
          ])
        ], { optional: true })

      ])
    ])
  ],
})
export class AppComponent {
  name = 'Angular';
  nameList = [];

  ngOnInit() {

  }

  toggle() {
    this.nameList = this.nameList.length ? [] : [1, 2, 3, 4, 5, 6];
  }
}
```




### 参考
- https://angular.cn/guide/transition-and-triggers
- https://www.jianshu.com/p/9374fc1653ea
