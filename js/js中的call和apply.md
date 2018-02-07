js中的call和apply
==

### 1. call和apply的用法

#### 1.1 立即执行函数

```
function hello() {
  return function() {
    console.log("hello");
  }

}

hello()();  //hello
hello().call();  //hello
hello().apply();  //hello
```
