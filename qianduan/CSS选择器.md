
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
