
## IfElse型

[Indeed](https://stackoverflow.com/a/38203292)
```html
<p class="{{condition ? 'checked' : 'unchecked'}}">
or

<p [ngClass]="condition ? 'checked' : 'unchecked'">
or

<p [ngClass]="[condition ? 'checked' : 'unchecked']">
```
