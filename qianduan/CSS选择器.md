
## 后代选择器的坑

我们想要选择`.area`下面的所有`div`和`span`
```css
.area>div,span{}

/* 上面的效果相当于 */
.area>div{}
span{}

/*正确的写法是分开写*/
.area>div{}
.area>span{}
```

## not选择器
```css
.card-content:not(.empty):hover {
  box-shadow: 0 4px 10px 2px rgba(0, 0, 0, .2);
}
```

```html
<div class="card-content empty">
    这个不符合
</div>

<div class="card-content">
    这个符合
</div>

```


## 参考
- http://www.ruanyifeng.com/blog/2009/03/css_selectors.html
