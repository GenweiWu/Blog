
## 入门

```
//查看端口状态
netstat -anp|grep 6006
```

## 用法
netstat用来展示网络连接情况

`netstat` 默认不展示LISTEN相关  
`netstat -l` 只展示LISTENT相关  
`netstat -a` 显示所有(包括LISTENT和非LISTEN的) = `netstat` + `netstat -l`  
`netstat -p` 显示程序名称和进程(PID/Process)  
`netstat -n` 不显示别名，而是直接显示数字  


### 实例
```console
[root@SZX1000538990 ~]# netstat |grep ssh
tcp        0     48 SZX1000538990:ssh       10.11.12.13:7413      ESTABLISHED
[root@SZX1000538990 ~]# netstat -l|grep ssh
tcp        0      0 0.0.0.0:ssh             0.0.0.0:*               LISTEN
tcp6       0      0 [::]:ssh                [::]:*                  LISTEN
[root@SZX1000538990 ~]# netstat -a|grep ssh
tcp        0      0 0.0.0.0:ssh             0.0.0.0:*               LISTEN
tcp        0     48 SZX1000538990:ssh       10.11.12.13:7413      ESTABLISHED
tcp6       0      0 [::]:ssh                [::]:*                  LISTEN
```


