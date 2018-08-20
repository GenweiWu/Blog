## 数据绑定 与 变更检测

在angular2中有个特性是数据双向绑定，当检测到ts中变量发生改变，html中就会重新加载这个变量的展示效果。
当然也涉及到[input]这种单向绑定的变更检测。

## 手动触发检测
如果有些时候，自动更新没有触发，可以手动触发。

```ts
import { ChangeDetectorRef } from '@angular/core';
...
constructor(
    private changeDetectorRef:ChangeDetectorRef
  ) { }
...

methodXxx(){
  ...
  this.changeDetectorRef.detectChanges(); 
}
```

