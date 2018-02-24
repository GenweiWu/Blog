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

```sql
-- 创建新的数据库，对应mysql上的create database
-- 对应oracle要先创建用户然后指定对应的文件
-- 对中生成的表类似 dave.table1
CREATE TABLESPACE dave LOGGING DATAFILE '/opt/oracle/oradb/oradata/wgw/dave.dbf' SIZE 100M AUTOEXTEND ON NEXT 32M MAXSIZE 500M EXTENT MANAGEMENT LOCAL;

CREATE USER dave IDENTIFIED BY dave DEFAULT TABLESPACE dave;

grant connect,resource,dba to dave;
grant create session to dave;
```
