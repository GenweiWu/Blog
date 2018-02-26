oracle入门.md
==
### 1、查询oracle基本信息
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

- 用户被锁定(一般是密码多次错误导致)
```sql
-- ORA-28000: the account is locked
ALTER USER username ACCOUNT UNLOCK;
```

### 2、如何创建database
```sql
-- 创建新的数据库，对应mysql上的create database
-- 对应oracle要先创建用户然后指定对应的文件
-- 对中生成的表类似 dave.table1
CREATE TABLESPACE dave LOGGING DATAFILE '/opt/oracle/oradb/oradata/wgw/dave.dbf' SIZE 100M AUTOEXTEND ON NEXT 32M MAXSIZE 500M EXTENT MANAGEMENT LOCAL;

CREATE USER dave IDENTIFIED BY dave DEFAULT TABLESPACE dave;

grant connect,resource,dba to dave;
grant create session to dave;
```

### 3数据备份还原
> 利用exp、imp即导入导出来实现备份恢复。
 
 - 从windows本地使用`oracle client`+`sqlplus`连接远程linux环境的oracle服务器，进行导入导出
 - 导出使用`oracle client`和服务器oracle版本的区别，导致导出经常出错。此时建议从linux服务器端直接用`exp`命令进行导出。

```
1、EXP: 
      有三种主要的方式（完全、用户、表） 
      1、完全： 
          EXP SYSTEM/MANAGER BUFFER=64000 FILE=C:\FULL.DMP FULL=Y 
          如果要执行完全导出，必须具有特殊的权限 
      2、用户模式： 
          EXP SONIC/SONIC    BUFFER=64000 FILE=C:\SONIC.DMP OWNER=SONIC 
          这样用户SONIC的所有对象被输出到文件中。 
      3、表模式：
          EXP SONIC/SONIC    BUFFER=64000 FILE=C:\SONIC.DMP OWNER=SONIC TABLES=(SONIC) 
          这样用户SONIC的表SONIC就被导出 
    2、IMP: 
      具有三种模式（完全、用户、表） 
      1、完全： 
          IMP SYSTEM/MANAGER BUFFER=64000 FILE=C:\FULL.DMP FULL=Y 
      2、用户模式： 
          IMP SONIC/SONIC    BUFFER=64000 FILE=C:\SONIC.DMP FROMUSER=SONIC TOUSER=SONIC 
          这样用户SONIC的所有对象被导入到文件中。必须指定FROMUSER、TOUSER参数，这样才能导入数据。 
      3、表模式： 
          EXP SONIC/SONIC    BUFFER=64000 FILE=C:\SONIC.DMP OWNER=SONIC TABLES=(SONIC) 
          这样用户SONIC的表SONIC就被导入。
```
> https://www.cnblogs.com/yugen/archive/2010/07/25/1784763.html
