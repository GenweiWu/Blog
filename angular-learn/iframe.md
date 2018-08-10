angular2中iframe操作

## iframe使用html填充
### 不是使用innerHTML，而是使用src进行实现

> 假设html为内容为
```html
<h1>haha</h1>
```

> 则对应
```html
<div class="iframe-test">
    <iframe src="data:text/html;charset=utf-8,<h1>haha</h1>"></iframe>
</div>
```
