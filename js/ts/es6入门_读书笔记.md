## 书籍链接
http://es6.ruanyifeng.com


## 1、变量的解析赋值
```ts
let {length:len} = "1234"
len  //4
typeof len  //"number"
```

```ts
let {toString:xx} = 123
typeof xx  //"function"
xx === Number.prototype.toString  //true
```

> 我的理解是：解析赋值适用于属性,不适用方法;上面的String.length是属性,而String.prototype.toString()是方法.

## 2、[字符串扩展-标签模板](http://es6.ruanyifeng.com/#docs/string#%E6%A0%87%E7%AD%BE%E6%A8%A1%E6%9D%BF)

#### -基本效果：字符串 和 参数 分隔开
```ts
let who = "dave";
test`hello there ${who}`
test(['hello there ', ''], 'dave')

function test(_word, ..._params) {
  console.log(`word==> ${_word}`)
  console.log(`params==> ${_params}`)
}

//word==> hello there ,
//params==> dave

//word==> hello there ,
//params==> dave
```
> 上面两个输出效果一样，可以对比理解下模板标签的作用

#### -例2
```ts
let aa = "hello";
let bb = "world";
test`this is ${aa} and ${bb}`;

function test(_word, ..._params) {
console.log(`word==> ${_word}`)
console.log(`params==> ${_params}`)
console.log`word==> ${_word}`

//word==> this is , and ,
//params==> hello,world
//(2) ["word==> ", "", raw: Array(2)]0: "word==> "1: ""length: 2raw: (2) ["word==> ", ""]__proto__: Array(0) (3) ["this is ", " and ", "", raw: Array(3)]
```
![](./assets/es001.png)

> 个人理解,除非你需要把模板字符串本身和它的参数分开处理，否则没必要这样用,它不等价于直接用

#### 例3：raw属性
```ts
print`hello \n dave`;

let name = "dave"
print`hello ${name}`

function print(_msg, ...params) {
  debugger;
}
```
- print`hello \n dave`  
![image](https://user-images.githubusercontent.com/16630659/50410487-36aade00-0834-11e9-8679-da40dae9994a.png)
![image](https://user-images.githubusercontent.com/16630659/50410505-5215e900-0834-11e9-86ad-b9888b1f24ea.png)

- print`hello ${name}`  
![image](https://user-images.githubusercontent.com/16630659/50410552-b2a52600-0834-11e9-89c7-af8e3940061c.png)
![image](https://user-images.githubusercontent.com/16630659/50410554-b638ad00-0834-11e9-8486-a0b0a6e78d43.png)

> raw属性存放的是字符串转义后的原始字符串，但是\都被转义了(看下面的例子)

```ts
String.raw`1122\n33`
//  "1122\n33"
String.raw({raw:['1122\n33']})
//  "1122
//  33"
String.raw({raw:['1122\\n33']})
//  "1122\n33"  
```

## 3、String.raw
```ts
var name="dave"
String.raw`this is ${name}`
"this is dave"

String.raw`this is ${name} \n123`
"this is dave \n123"
String.raw({raw:'this is \n123'},'dave')
//  "tdavehis is 
// 123"
String.raw({raw:['this is ',' \n123']},'dave')
//  "this is dave 
// 123"
String.raw({raw:['this is ',' \\n123']},'dave')
"this is dave \n123"

String.raw`this is ${name} \n123  then\end`
"this is dave \n123  then\end"
String.raw({raw:['this is ',' \n123  then\end']},'dave')
//  "this is dave 
//  123  thenend"
String.raw({raw:['this is ',' \\n123  then\end']},'dave')
"this is dave \n123  thenend"
String.raw({raw:['this is ',' \\n123  then\\end']},'dave')
"this is dave \n123  then\end"
```

```ts
console.log('1\n2')
//  1
//  2

console.log('1\\n2')
//  1\n2
```

> String.raw的两个作用：转义\比如`\`-->`\\`，另外变量值替换`${name}`-->`dave`

