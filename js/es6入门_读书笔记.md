## 书籍链接
http://es6.ruanyifeng.com


## 变量的解析赋值
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
