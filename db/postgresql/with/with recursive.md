# with recursive

> https://www.postgresql.org/docs/current/queries-with.html#QUERIES-WITH-RECURSIVE


### 1. 单个字段
```sql
-- with ...as中声明要递归的字段
with recursive cte (n) as(
select 1 -- 初始值
union all
select n + 1 from cte where n<5 -- 递归结束条件
)
select * from cte;
```

> result:
>
| n    |
| ---- |
| 1    |
| 2    |
| 3    |
| 4    |
| 5    |


### 2. 多个字段
```SQL
with recursive cte (n,star) as(
select 1,'*' 
union all
select n + 1,concat(star,'*') from cte where n<5 
)
select * from cte;
```
> result:
> 
| n    | star  |
| ---- | ----- |
| 1    | `*`     |
| 2    | `**`    |
| 3    | `***`   |
| 4    | `****`  |
| 5    | `*****` |




