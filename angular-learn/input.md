# @Input()


## input变量共享性
使用@Input()绑定的变量，对父组件和子组件是共享的，即任一方修改，都会对另一方生效。
参考：https://stackoverflow.com/a/34208500/6182927

## 针对同一个变量同时使用input和output的方式
> https://stackoverflow.com/a/51478670
```ts
  //表示当前卡片正在被编辑
  @Input() cardIsEditing;
  @Output() cardIsEditingChange: EventEmitter<string> = new EventEmitter();
```
