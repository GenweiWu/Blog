
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





