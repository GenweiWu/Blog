
## Demo1：pipe动态监检测语言

```html
<div>{{''|currentLocale}}</div>
```

### **有缺陷的**
```ts
import { ChangeDetectorRef, Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from 'ng2-translate';

@Pipe({
  name: 'currentLocale',
  //这里需要声明为`非纯管道`，否则不会触发变更监测
  pure: false
})
export class CurrentLocalePipe implements PipeTransform {
  private _currentLocale: string;

  constructor(
    private translate: TranslateService,
    private ref: ChangeDetectorRef
  ) {
    //`pipe`的`ngOnInit`不会执行，所以只能放到`constructor`里
    translate.onLangChange.subscribe(x => {
      this.update();
      this.ref.detectChanges();
    });

    this.update();
  }

  private update() {
    //'zh' or 'en'
    this._currentLocale = this.translate.currentLang;
  }

  transform(value: any): any {
    return this._currentLocale;
  }

}

```

### 上面的代码存在缺陷：`ERROR Error: ViewDestroyedError: Attempt to use a destroyed view: detectChanges`
原来的页面关闭后，打开新页面，之前的代码还是会执行，所以会报错

```ts
import { ChangeDetectorRef, OnDestroy, Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from 'ng2-translate';
import { Subscription } from 'rxjs';

@Pipe({
  name: 'currentLocale',
  pure: false
})
export class CurrentLocalePipe implements PipeTransform, OnDestroy {
  private _currentLocale: string;

  private changes$$: Subscription;

  constructor(
    private translate: TranslateService,
    private ref: ChangeDetectorRef
  ) {
  //记录下Subscription用于后面释放
    this.changes$$ = translate.onLangChange.subscribe(x => {
      this.update();
      this.ref.detectChanges();
    });

    this.update();
  }

  private update() {
    this._currentLocale = this.translate.currentLang;
  }

  transform(value: any): any {
    return this._currentLocale;
  }

  ngOnDestroy() {
  //重要!!!这里要释放
    if (this.changes$$) {
      this.changes$$.unsubscribe();
    }
  }


}

```


