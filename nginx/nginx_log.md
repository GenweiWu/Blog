配置nginx log
==

主要两种日志，访问日志`access_log`和错误日志`error_log`

### 1. access_log

> 可以设置路径，以及日志格式
```
access_log path [format [buffer=size] [gzip[=level]] [flush=time] [if=condition]]; # 设置访问日志
```

> 样例:通过log_format定义日志格式
```
access_log /var/logs/nginx-access.log main
log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                  '$status $body_bytes_sent "$http_referer" '
                  '"$http_user_agent" "$http_x_forwarded_for"';
```

### 2. error_log

> 可以设置路径，以及日志级别
```
error_log file [level];
Default:    
error_log logs/error.log error;
```

```
第一个参数指定日志的写入位置。
第二个参数指定日志的级别。level可以是debug, info, notice, warn, error, crit, alert,emerg中的任意值。可以看到其取值范围是按紧急程度从低到高排列的。只有日志的错误级别等于或高于level指定的值才会写入错误日志中。默认值是error。
```

### 参考
- https://segmentfault.com/a/1190000013377493
