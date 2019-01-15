MyBatis
==

## 判断参数是否存在(不推荐)
```xml
<if test="_parameter.containsKey('forUpdate')">your code....</if> 
```
> 参数是map时可以不传`forUpdate`参数  
> 但是参数是bean类型时，会报错(xml中设置类型或者Mapper代码中参数就是个java bean都不行)
  ```
  Method "containsKey" failed for object Page{start=0, end=10, pageSize=10, sortOption='CREATE_TIME'}
  ```

对比
```xml
<if test="forUpdate != null">
```
> 你必须传了`forUpdate`参数，否则会报错
