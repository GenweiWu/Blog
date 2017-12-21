jquery.extend方法
==


#### 1. `jQuery.extend(dest,src1,src2...)  `  
  将src1,src2合并到dest，此时desc会被改变
  
```js
var a = {name:"zhang"};
var b = {name:"lisi",age:1};
var cc =   $.extend(a,b)
console.log(a);   // {name: "lisi", age: 1}  
console.log(cc);  // {name: "lisi", age: 1}
```
可以看出a也被改变了。
   
#### 2. `jQuery.extend({},src1,src2...)`   
   通过将第一个参数设置成{},可以避免上面的元素本身改变的问题
```js
var a = {name:"zhang"};
var b = {name:"lisi",age:1};
var dd=   $.extend({},a,b)
console.log(a);  //{name: "zhang"}
console.log(dd);  //{name: "lisi", age: 1}
```
可以看出a没有被改变了。


#### 3. `jQuery.extend(boolean,dest,src1,src2,src3...)`  
  第一个参数设置成true会进行深度拷贝
  
```js
var a = {name:"zhang",detail:{page1:1,page2:2}};
var b = {name:"lisi",age:1,detail:{page1:111,page3:333}};
var c=   $.extend(true,{},a,b)
console.log(c);  //{name: "lisi", detail: {page1:111,page2:2,page3:333}, age: 1}

````

```js
var a = {name:"zhang",detail:{page1:1,page2:2}};
var b = {name:"lisi",age:1,detail:{page1:111,page3:333}};
var c=   $.extend(false,{},a,b)
console.log(c);   //{name: "lisi", detail: {page1:111,page3:333}, age: 1}
```
对比发现，对于深拷贝，会将子对象中的属性进行合并，而不是简单的覆盖掉


---
### 参考:
http://www.cnblogs.com/RascallySnake/archive/2010/05/07/1729563.html
