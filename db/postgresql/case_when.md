

> 测试数据
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
