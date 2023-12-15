## 聚合函数 `array_agg`和`string_agg`

> https://www.postgresql.org/docs/9.0/functions-aggregate.html



## 1. `array_agg`

```
array_agg(expression)
把表达式变成一个数组 一般配合 array_to_string() 函数使用
```

```sql
-- 准备数据

drop table if exists t_student;

create table t_student(
  id int not null,
  hobby varchar(10) 
);

insert into t_student(id,hobby)
values
(1,'111aaa'),
(1,'111bbb');
insert into t_student(id,hobby)
values
(2,'222aaa'),
(2,'222bbb'),
(2,'222ccc');
insert into t_student(id,hobby)
values
(3,'333DDD'),
(3,'333EEE'),
(3,null);
```



> 数据准备
```sql
select * from t_student;
```

| id   | hobby  |
| ---- | ------ |
| 1    | 111aaa |
| 1    | 111bbb |
| 2    | 222aaa |
| 2    | 222bbb |
| 2    | 222ccc |
| 3    | 333DDD |
| 3    | 333EEE |
| 3    | [NULL] |



> 测试1： 不使用group by

```sql
-- 不使用group by
select array_agg(hobby) from t_student ts;
```

| array_agg                                               |
| ------------------------------------------------------- |
| {111aaa,111bbb,222aaa,222bbb,222ccc,333DDD,333EEE,NULL} |


> 测试2：使用group by
```sql
-- 使用group by
select id,array_agg(hobby) from t_student group by id;
```

| id   | array_agg              |
| ---- | ---------------------- |
| 3    | {333DDD,333EEE,NULL}   |
| 2    | {222aaa,222bbb,222ccc} |
| 1    | {111aaa,111bbb}        |

> 测试3：搭配array_to_string使用

```sql
-- 搭配array_to_string使用
select id,array_to_string(array_agg(hobby),'#') from t_student group by id;
```

| id   | array_to_string      |
| ---- | -------------------- |
| 3    | 333DDD#333EEE        |
| 2    | 222aaa#222bbb#222ccc |
| 1    | 111aaa#111bbb        |




## 2. `string_agg`

```
将指定字段聚合成字符串，NULL会被跳过
```



> 测试1：不使用group by
```sql
-- 不使用group by
select string_agg(hobby,'#') from t_student ts; 
```

|string_agg|
|----------|
|111aaa#111bbb#222aaa#222bbb#222ccc#333DDD#333EEE|



> 测试2：使用group by

```sql
-- 使用group by
select string_agg(hobby,'#') from t_student group by id; 
```

| string_agg           |
| -------------------- |
| 333DDD#333EEE        |
| 222aaa#222bbb#222ccc |
| 111aaa#111bbb        |
