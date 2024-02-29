

### 返回删除的数据

> pg使用RETURNING语法

> mybatis：  
> 1.要使用select而不是delete方法;  
> 2.要指定resultType或resultMap否则会报错   
```
<select id="removeSomeStuff" parameterType="map" resultType="WhateverType" flushCache="true">
    delete from some_stuff where id = #{id}
    RETURNING *
</select>
```

