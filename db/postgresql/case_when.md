



## count测试
> 注意：count(0)=1，count(null)=0
```sql
select count(1); --1
select count(0); --1
select count('男生'); --1
select count(null); --0
```

## case when测试

### (1)构造测试数据
```sql
create table if not exists t_student(
  id serial,
  name varchar(20) not null,
  gender varchar(10) not null
);

INSERT INTO t_student 
("name", gender) 
values
('zhangsan', 'male'),
('lisi', 'male'),
('wangwu', 'male'),
('xiaohong ', 'female')
```

### (2)else 0不符合需求预期
> 1
```sql
select
	case
		when gender = 'male' then 1
		else 0
	end
from
	t_student;
```
|case|
|----|
|1|
|1|
|1|
|0|

> 2
```sql
select
	count(case
		when gender = 'male' then 1
		else 0
	end) as male,
	count(case
		when gender = 'female' then 1
		else 0
	end) as female
from
	t_student;
```
|male|female|
|----|------|
|4|4|


### (3)else null符合需求预期(另else不写默认就是null)
> 1
```sql
select 
       case 
           when gender = 'male' then 1 else null 
       end
from t_student;
```
|case|
|----|
|1|
|1|
|1|
|NULL|

> 2
```sql
select
	count(case
		when gender = 'male' then 1
		else null
	end) as male,
	count(case
		when gender = 'female' then 1
		else null
	end) as female
from
	t_student;
``` 
|male|female|
|----|------|
|3|1|



