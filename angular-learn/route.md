
## routeLink

一般用在a上，但是用在其他元素上也可以，比如
```
<a routerLink="/dashboard">DashBoard</a>
```

```
<ul>
<li *ngFor="let hero of heroes" routerLink="/detail/{{hero.id}}></li>
</ul>
```
