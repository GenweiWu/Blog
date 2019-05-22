
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

## every 判断是否所有元素都满足某个条件
都满足返回true，有一个不满足就返回false

```ts
let result: boolean = [1, 2, 3].every(x => x > 0);
console.log(result);//true

result = [1, 2, -1].every(x => x > 0);
console.log(result);//false
```

## some 判断是否有元素满足某个条件
只要有一个元素满足这个条件，就返回true，都不满足才返回false

```ts
let result: boolean = [-1, -2, 3].some(x => x > 0);
console.log(result);//true

result = [-1, -2, -3].some(x => x > 0);
console.log(result);//false
```

## 循环

- for of方法
- for in方法
- 传统for循环方法
- forEach方法

```ts
let someArray = ['aa', 'bb', 'cc'];

//for of方式
for (let i of someArray) {
  console.log(i);
}
console.log('----------------');

//for in方式：注意for in得到的是下标不是元素本身
for (let i in someArray) {
  console.log(i + '-->' + someArray[i]);
}
console.log('----------------');

//传统的for循环方式
for (let i = 0; i < someArray.length; i++) {
  console.log(i + '-->' + someArray[i]);
}
console.log('----------------');

//es5 forEach方法
someArray.forEach((val, index, arr) => {
  //var元素值，index下标，arr数组本身
  console.log(index + '==>' + val);
  console.log(arr);
});
```

output:
```
aa
bb
cc
----------------
0-->aa
1-->bb
2-->cc
----------------
0-->aa
1-->bb
2-->cc
----------------
0==>aa
(3) ["aa", "bb", "cc"]
1==>bb
(3) ["aa", "bb", "cc"]
2==>cc
(3) ["aa", "bb", "cc"]
```



