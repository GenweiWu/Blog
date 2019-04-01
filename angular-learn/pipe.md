
## Demo1：pipe动态监检测语言

```html
<div>{{''|currentLocale}}</div>
```

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
