
```css
<ng-template ngFor let-car [ngForOf]="cars" let-i="index" let-l="last">
    <li class="node-row">
        <div class="node-container">
            <div class="node">{{car.name}}</div>

        </div>
    </li>
</ng-template>
```   

```ts
    this.cars = [{
      name:'Iran MCCI'
    },
      {
      name: 111
    }, {
      name: 222
    },
      {
        name: 333
      },
      {
        name: 444
      }
    ]
```
