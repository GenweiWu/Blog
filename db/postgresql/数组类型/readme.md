
## 入门
> http://www.postgres.cn/docs/9.3/arrays.html


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
