JS闭包closure
==
### 1. JS没有块级作用域，而是函数作用域

#### 1.1 为啥说JS没有块级作用域

```js
var name = "global";

if (true) {
  var name = "local";
  console.log("=>" + name); //=>local
}

console.log("==>" + name); //==>local
``` 
如果js有块级作用域，那么最后应该打印global才对。


#### 1.2 函数作用域

函数可以访问当前函数中的变量，以及它的所有父函数中的变量。

> Functions defined inside other functions, known as nested functions, have access to their parent function's scope.


### 2. 变量作用域：全局变量和局部变量

- 变量有两种作用域：全局变量和局部变量
- 局部变量：函数内使用var定义的变量(没有使用var定义的还是全局变量)



|            | 函数内 | 函数外
|      -       |  -     | -
|函数内局部变量  | Yes   | No
|全局变量       | Yes   | Yes

> 可以得出：父函数无法访问子函数中的变量

### 3. JS的链式作用域

子对象会一级一级地向上查找所有父对象的变量。结果就是，父对象中的变量对子对象都是可见的，反之则不成立。


```js
function f1() {
  var n = 999;

  function f2() {　　　　　　
    alert(n);　　　　
  }　　　
  return f2;
}

　　
var result = f1();　
result(); // 999
```
> 可以通过返回函数的方式，利用JS链式作用域，来达到在外部访问js内部变量的目的  


### 4. 闭包
#### 4.1 什么是闭包？
- 闭包指的是可以访问其他函数内部变量的函数
- 常用的方式是：在一个函数内部创建一个子函数，子函数可以访问这个函数的变量，这样就能突破函数作用域的限制，达到在函数外部访问函数内部变量的效果。

#### 4.2 为什么要用闭包？闭包有啥作用？  

- 读取函数内部的变量   
- 让变量值保持在内存中     

```js
function f1() {

  var n = 999;

  nAdd = function() {
    n += 1
  }　　
  function f2() {　　　　　　
    alert(n);　　　　
  }
  return f2;
}

var result = f1();

result(); // 999
　　
nAdd();　　
result(); // 1000
```

> 在这段代码中，result实际上就是闭包f2函数。它一共运行了两次，第一次的值是999，第二次的值是1000。这证明了，函数f1中的局部变量n一直保存在内存中，并没有在f1调用后被自动清除。
> 为什么会这样呢？原因就在于f1是f2的父函数，而f2被赋给了一个全局变量，这导致f2始终在内存中，而f2的存在依赖于f1，因此f1也始终在内存中，不会在调用结束后，被垃圾回收机制（garbage collection）回收。




### 5. 思考题

注：题目均来自文末的参考链接   

```js
var aNumber = 100;
tweak();

function tweak(){

    // This prints "undefined", because aNumber is also defined locally below.
    document.write(aNumber);

    if (false)
    {
        var aNumber = 123;  
    }
}
```
> 变量提升: 在解释时变量的定义放到最前面去，而赋值在执行时


```
function Foo() {
    var i = 0;
    return function() {
        console.log(i++);
    }
}
 
var f1 = Foo(),
    f2 = Foo();
f1();
f1();
f2();
```
> f1和f2拥有各自的闭包


### 参考
- [学习Javascript闭包](http://www.ruanyifeng.com/blog/2009/08/learning_javascript_closures.html)  
- [Javascript的链式作用域](http://www.oc35.com/2014/05/12/javascript-amazing1.html)
- [msdn高级JavaScript](https://msdn.microsoft.com/zh-cn/library/bzt2dkta(v=vs.94).aspx)
