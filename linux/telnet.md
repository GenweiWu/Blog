telnet是用来探测指定ip是否开放指定端口
==

> 测试ip 1.2.3.4的端口是不是通的
```
telnet 1.2.3.4 50
```

> 以下是3306通,但是33061不通
```CMD
[root@SZX1000538970 ~]# telnet 1.2.3.4 3306
Trying 1.2.3.4...
Connected to 1.2.3.4.
Escape character is '^]'.
N
5.7.19-log$T^mze8spOx*iU2Wy\`/mysql_native_password

^CConnection closed by foreign host.
[root@SZX1000538970 ~]# telnet 1.2.3.4 33061
Trying 1.2.3.4...
telnet: connect to address 1.2.3.4: No route to host
```

## 技巧：本机测试
本机测试可以小排除网络问题、防火墙问题
```
telnet localhsot 80
```


## telnet connection refused
注意：要求对应端口是listen状态
```
netstat -anp|grep 3306
```
