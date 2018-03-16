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

- 启动、停止
```
登录到CentOS，切换到oracle用户权限

# su – oracle

接着输入：
$ sqlplus "/as sysdba"

原本的画面会变为
SQL>

接着请输入
SQL> startup
就可以正常的启动数据库了。

停止数据库的指令如下：
SQL> shutdown immediate
```
参考 https://www.cnblogs.com/mchina/archive/2012/11/27/2782993.html

- 启动监听器
```
$ lsnrctl status  //查询状态
$ lsnrctl stop  //停止
$ lsnrctl  start  //启动
$ lsnrctl  reload  //重新加载

一般对应配置文件  /opt/oracle/oradb/home/network/admin/listener.ora
LISTENER_ORA =
  (DESCRIPTION_LIST =
    (DESCRIPTION =
      (ADDRESS_LIST =
        (ADDRESS = (PROTOCOL = TCP)(HOST = 10.11.12.13)(PORT = 1526))
      )
    )
  )

SID_LIST_LISTENER_ORA =
  (SID_LIST =
    (SID_DESC =
      (ORACLE_HOME = /opt/oracle/oradb/home)
      (SID_NAME = oradb)
    )
  )
注意：上面的配置对应的是 lsnrctl status LISTENER_ORA
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

### 4.oracle操作建议使用plsql，用navicat导出数据出现不兼容
```
1、导出使用的exe
E:\app\dave\product\11.1.0\client_1\BIN\exp.exe

2、导入使用的exe
E:\app\dave\product\11.1.0\client_1\bin\imp.exe
```


