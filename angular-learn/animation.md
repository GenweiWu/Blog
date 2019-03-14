动画效果 Animation
==

### 使用 stagger() 来实现在每个动画之间延迟 xx毫秒

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


### 参考
- https://angular.cn/guide/transition-and-triggers
- https://www.jianshu.com/p/9374fc1653ea
