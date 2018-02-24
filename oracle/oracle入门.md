oracle入门.md
==

```sql
-- 查询所有表空间
SELECT * FROM V$TABLESPACE;
select * from dba_tablespaces;

-- 查询所有用户
SELECT * FROM ALL_USERS;

-- 查询表所属的表空间等信息
SELECT * FROM user_tables ORDER BY Table_name;

-- 查询oracle服务器版本
SELECT * FROM V$VERSION;

-- 查询属于当前用户的所有表
select * from user_all_tables;
select count(*) from user_all_tables;
```
