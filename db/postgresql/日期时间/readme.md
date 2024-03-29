> https://www.postgresql.org/docs/current/datatype-datetime.html#DATATYPE-DATETIME-INPUT 
![image](https://github.com/GenweiWu/Blog/assets/16630659/2065c839-17b3-4c02-8cb7-9a402bee89b8)



## 时间比较

### 1）between的用法 

> https://www.postgresql.org/docs/current/functions-comparison.html

`a BETWEEN x AND y`
等于
`a >= x AND a <= y`

### 2）当前时间、当前日期

```sql
select now() ; -- 2023-07-19 11:27:09.877 +0800
select current_date;  -- date -- 2023-07-19
select (current_date - interval '7 day')::date; -- date -- 2023-07-12
```

### 3）时间和日期对比

> 个人理解：'2023-07-19' 约等于 '2023-07-19 00:00:00'
```sql
select '2023-07-19 00:00:00' = '2023-07-19'; -- false ;不推荐，被当成字符串了吧
select '2023-07-19 00:00:00'::timestamp = '2023-07-19'  -- true  

select '2023-07-19 00:00:00' > '2023-07-19'; -- true
select '2023-07-19 00:00:00' > '2023-07-19'; -- true
select '2023-07-19 12:00:00' > '2023-07-19'; -- true
select '2023-07-19 23:59:59' > '2023-07-19'; -- true
select '2023-07-19 23:59:59' < '2023-07-20'; -- true
```

> 判断日期是否属于今天
```sql
select *
from test1
where ts >= now()::date and ts < now()::date + interval '1 day';
```
or 
```sql
select *
from test1
where ts >= current_date and ts < current_date + interval '1 day';
```

### 4）timestamp转换

> 这些函数返回的是timestamp和date、time类型，不是字符串  
```sql
select '2023-07-19 10:58:00'::timestamp; -- timestamp -- 2023-07-19 10:58:00.000
select '2023-07-19 10:58:00'::date;  -- date -- 2023-07-19
select '2023-07-19 10:58:00'::time;  -- time -- 10:58:00
```

### 5) interval如何动态设置
> 参考下面的3  
```
select current_timestamp - interval '3 day';
select current_timestamp - 3 * interval '1 day';
```

### 6) 给timestamp且not null设置默认值
```sql
drop table if exists t_test;

create table t_test(
 create_time timestamp with time zone default '-infinity' not null
);

select
	*
from
	t_test
where
	create_time != '-infinity'
```


