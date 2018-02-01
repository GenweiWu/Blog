mysql权限
==

#### 1. `select host,user from mysql.user;  `  
  查看当前用户和对应的主机信息(可以针对某一个`database`.`某张表`针对`用户`@`IP`做限制)

```
mysql> select host,user from mysql.user; 
+---------------+-----------+
| host          | user      |
+---------------+-----------+
| %             | zhangsan  |
| 10.12.14.16   | zhangsan  |
| localhost     | mysql.sys |
| localhost     | root      |
+---------------+-----------+
4 rows in set (0.00 sec)  
```
