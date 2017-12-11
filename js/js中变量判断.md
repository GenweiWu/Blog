js中true判断
==

#### 1、通过if(a)的方式判断，以下全部是false:
 false,0,''或"",null,undefined,NaN


#### 2、下面的js问题以及答案
```js

var arr = ['A', '', null, undefined, '   '];
var r = arr.filter(function (s) {
  return s && s.trim();
});
var rr = arr.filter(function (s) {
  return s;
});

console.log(r);
//["A"]
console.log(rr);
//["A", "   "]

````
