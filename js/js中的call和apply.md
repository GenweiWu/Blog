js中的call和apply
==

### 1. call和apply的用法

#### 1.1 立即执行函数

```js
function hello() {
  return function() {
    console.log("hello");
  }

}

hello()();  //hello
hello().call();  //hello
hello().apply();  //hello
```

#### 1.2 改变this的指向

```js
var obj = {
  aaa: "this is obj"
}

function hello() {
  console.log(this.aaa);
}

hello(); //undefined
hello.call(obj); //this is obj
hello.apply(obj); //this is obj
```

### 2.call VS apply

#### 2.1 传递参数方式不同
```js
var obj = {
  word: "word"
}

function hello(a, b) {
  console.log(a + " " + this.word + " " + b);
}

hello.apply(obj, ["first", "second"]); //first word second
hello.call(obj, "first", "second"); //first word second
```
> apply传递的是参数数组，call传递的是多个参数。



