
## ngStyle不能出现多个

> 即下面的只有前面的是生效的
```html
<span  [ngStyle]="{'border':'1px solid blue'}" [ngStyle]="{'color':'red'}">111</span>
<span  [ngStyle]="{'border':'1px solid blue'}">111</span>
```
