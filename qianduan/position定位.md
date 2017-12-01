position定位
--


### 1. position:static
 - 默认值，不会被特殊定位
 - 我们称之为`'未positioned'`
 
### 2. position:relative
 - 和static类似，通过使用`top`,`bottom`,`left`,`right`来偏离其正常位置
 - 占用文档流,后续元素会在其正常位置的基础上展示
 
### 3. position:absolute
  - 脱离文档流
  - 相对于其最近的`positioned`的元素定位(都木有则相对body定位)
  - 会随着页面滚动而移动

### 4. position:fixed
 - 脱离文档流
 - 相对于**视窗**来定位
 - 页面滚动它还是会停留在相同的位置

---
### 总结:

1. absolute不会撑开父元素的高度,而是会溢出去
