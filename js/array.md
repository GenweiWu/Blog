
## filter vs find
`arr.find`  返回符合条件的第一个元素  
`arr.filter`  返回符合条件的所有元素的集合  

## putAll
> https://stackoverflow.com/a/32511679/6182927
```ts
var vegetables = ['parsnip', 'potato'];
var moreVegs = ['celery', 'beetroot'];

// Merge the second array into the first one
// Equivalent to vegetables.push('celery', 'beetroot');
Array.prototype.push.apply(vegetables, moreVegs);

console.log(vegetables); // ['parsnip', 'potato', 'celery', 'beetroot']
```

Or you can use the `spread operator` feature of ES6:
```ts
let fruits = [ 'apple', 'banana'];
const moreFruits = [ 'orange', 'plum' ];

fruits.push(...moreFruits); // ["apple", "banana", "orange", "plum"]
```
