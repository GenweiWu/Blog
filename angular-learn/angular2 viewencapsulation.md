angular2 viewencapsulation
==

### 背景：
有一个common.css中有默认样式，在组件a中想覆盖其中一个class属性(部分属性)，而组件b中还是用默认的

### 测试结论

 1. 啥也不写：默认是ViewEncapsulation.Emulated

2. encapsulation: ViewEncapsulation.None  
 (a)子样式覆盖父样式  
 (b)此时子样式会变成全局样式影响其他组件

3. encapsulation: ViewEncapsulation.Emulated  
 (a)子样式覆盖父样式  
 (b)此时子样式不会变成全局样式

1. encapsulation: ViewEncapsulation.Native  
 (a)子样式生效，父样式不起作用

**==> 从测试结果看来，使用默认的即可**

## 注意
1. 如果部分页面是jquery生成的组件内容，那么可能出现问题(比如使用Emulated无法在插件自己拼接的代码里生成)
```html
<div _ngcontent-c4="" class="ztree assetsTree" id="treeBody2">    <==这里生成的是加了`_ngcontent-c4`的
  <li>...                                                       <== 组件自己拼接的没有对应的标识
```

## 如何覆盖封装好的组件的样式呢？
一般来说，你做不到这点，即你的样式只会影响当前html内容，但是不包括html中的子组件，子组件的样式由它自己决定。

但是当前有一个穿刺样式的办法，关键字`::ng-deep`
参考
- https://angular.cn/guide/component-styles
- https://github.com/primefaces/primeng/issues/558

## 参考
1. View Encapsulation的种类  
- ViewEncapsulation.None  
  没有Shadow Dom，样式没有封装，全局可以使用。
- ViewEncapsulation.Emulated  
  angular2的默认值，模仿 Shadow Dom，通过在标签上增加标识，来固定样式的作用域。
- ViewEncapsulation.Native  
  使用原生的Shadow Dom。

2. 和全局样式的关系
- native，原生，样式不进不出，不能作用于当前组件之外，同时其它组件不能作用于当前组件，主要是指全局样式
- emulated，仿真（默认），样式不能出，全局样式能进来。
- none ，样式能进能出。
