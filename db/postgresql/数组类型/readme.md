
## 数组类型
> http://www.postgres.cn/docs/9.3/arrays.html

## 一、SQL测试 

### 测试例子
```sql
drop table if exists t_array_test;
create table t_array_test(
   id serial not null primary key,
   belong_tenant TEXT ARRAY
);
```

```sql
-- insert
insert
	into
	t_array_test
(belong_tenant)
values
('{aaaa,bbbb}'),
('{dddd,eeee}');
```

```sql
-- update
update
	t_array_test
set
	belong_tenant = '{cccc,dddd}'
where
	id = 1;
```

```sql
-- search
select
	*
from
	t_array_test
where
	'cccc' = any(belong_tenant); 

-- multi search
select
	*
from
	t_array_test
where
	belong_tenant && ARRAY['cccc','dddd','No'];
```

### 可能涉及到类型转换
```sql
-- 可能涉及到类型转换
	belong_tenant && ARRAY['111']::varchar[]
```

## 二、搭配ibatis使用

#### 0、注意，java中对象要定义成数组而不是list
```java
private Integer[] points
```

#### 1、插入参考  【同时支持非空+空数组的插入】
```xml
insert into 
    t_xxx 
values (
    #{obj.points, jdbcType=ARRAY, "org.apache.ibatis.type.ArrayTypeHandler"}
)
```

#### ~~传参: List<T> + foreach(ARRAY[])~~ 【对空数组支持不好，不推荐】
> 注意:参考以前foreach写法,但是open="ARRAY[", close="]"
```xml
<select id="findByArray" parameterClass="MyObject">
    SELECT * FROM my_table WHERE my_array @>
    <foreach item="item" index="index" collection="list" open="ARRAY[" separator="," close="]">
        #{item}
    </foreach>
</select>
```

#### 2、查询: 数组+ArrayTypeHandler

> 映射包装类型
```java
class PgFolder {
  long id;
  Integer[] ids;
  // getter / setter
}
```

```xml
<resultMap id="xxx" type="...">
...
    <result column="ids" property="ids" typeHandler="org.apache.ibatis.type.ArrayTypeHandler"/>
</resultMap>
```






