MyBatis
==

## 判断参数是否存在
```xml
<if test="_parameter.containsKey('forUpdate')">your code....</if> 
```
> 可以不传

对比
```xml
<if test="forUpdate != null">
```
> 你必须传了`forUpdate`参数，否则会报错
