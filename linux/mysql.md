mysql
==

#### 1. 安装mysql
参考网址：https://dev.mysql.com/doc/refman/5.7/en/binary-installation.html

#### 2. 修改用户密码

```mysql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'Admin1234';
ALTER USER 'user_dev'@'%' IDENTIFIED BY 'pwd1234';
```
参考网址：https://dev.mysql.com/doc/refman/5.7/en/resetting-permissions.html

