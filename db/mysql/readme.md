## yum 安装mysql
要只安装mysql(客户端)，你应该执行  
`yum install mysql`  

安装mysql客户端和mysql服务器：  
`yum install mysql mysql-server`  

## mysql -h 127.0.0.1 -u root -p 回车输入密码
```
[root@SZX1000538971 ~]# mysql -h 127.0.0.1 -u root -p
Enter password: 
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MySQL connection id is 49
Server version: 5.7.19-log MySQL Community Server (GPL)

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MySQL [(none)]> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.00 sec)
```

## 批量插入
```sql
INSERT INTO `t_test` (
`id`,
`type`,
`tool`
)
VALUES
(
  'a111',
  'type1',
  'tool1'
),
(
  'a222',
  'type2',
  'tool2'
)
```  
