
## 入门

```
//查看端口状态
netstat -anp|grep 6006
//grep ssh就不要用n了，因为n展示的是数字
netstat -ap|grep ssh
```

## 用法
netstat用来展示网络连接情况

`netstat` 默认不展示LISTEN相关  
`netstat -l` 只展示LISTENT相关  
`netstat -a` 显示所有(包括LISTENT和非LISTEN的) = `netstat` + `netstat -l`  
`netstat -p` 显示程序名称和进程(PID/Process)  
`netstat -n` 不显示别名，而是直接显示数字  


## 实例

#### 1) `netstat -a` = `netstat` + `netstat -l`
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

#### 2) -n不展示别名展示数字 -p展示进程号/程序名称
```console
[root@SZX1000538990 ~]# netstat -a|grep 6379
tcp        0      0 SZX1000538990:26379     0.0.0.0:*               LISTEN
tcp        0      0 SZX1000538990:6379      0.0.0.0:*               LISTEN
tcp        0      0 SZX1000538990:6379      172.17.0.3:54924        ESTABLISHED
tcp        0      0 SZX1000538990:49916     172.17.0.2:6379         ESTABLISHED
tcp        0      0 SZX1000538990:6379      172.17.0.3:54944        ESTABLISHED
tcp        0      0 SZX1000538990:49914     172.17.0.2:6379         ESTABLISHED
[root@SZX1000538990 ~]# netstat -an|grep 6379
tcp        0      0 10.21.253.119:26379     0.0.0.0:*               LISTEN
tcp        0      0 10.21.253.119:6379      0.0.0.0:*               LISTEN
tcp        0      0 10.21.253.119:6379      172.17.0.3:54924        ESTABLISHED
tcp        0      0 172.17.0.1:49916        172.17.0.2:6379         ESTABLISHED
tcp        0      0 10.21.253.119:6379      172.17.0.3:54944        ESTABLISHED
tcp        0      0 172.17.0.1:49914        172.17.0.2:6379         ESTABLISHED
[root@SZX1000538990 ~]# netstat -anp|grep 6379
tcp        0      0 10.21.253.119:26379     0.0.0.0:*               LISTEN      45863/docker-proxy
tcp        0      0 10.21.253.119:6379      0.0.0.0:*               LISTEN      45294/docker-proxy
tcp        0      0 10.21.253.119:6379      172.17.0.3:54924        ESTABLISHED 45294/docker-proxy
tcp        0      0 172.17.0.1:49916        172.17.0.2:6379         ESTABLISHED 45294/docker-proxy
tcp        0      0 10.21.253.119:6379      172.17.0.3:54944        ESTABLISHED 45294/docker-proxy
tcp        0      0 172.17.0.1:49914        172.17.0.2:6379         ESTABLISHED 45294/docker-proxy
```

