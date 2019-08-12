
## 查看端口状态
 `netstat -anp|grep 6006`

- a 显示所有
- n 直接展示ip地址，而不是域名
- p 显示对应的程序program/pid等信息

## telnet localhsot 80
本机测试

## telnet connection refused
要求对应端口是listen状态
```
netstat -anp|grep 3306
```
