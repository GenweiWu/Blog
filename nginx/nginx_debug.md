nginx调试技巧
==

### 背景
在使用nginx的过程中，经常遇到的问题就是配置好转发规则后，测试请求发现不是预期的结果(常见的比如404错误)。  

这个时候可以尝试下面的定位方法

### 技巧一：测试是否匹配预期的location
```nginx
  server {
        listen       80;
        server_name  localhost;
        location / {
            root   html;
            index  index.html index.htm;
        }
        location = /helloworld {
                return 602;    //<==通过配置不常见的状态码来测试是否匹配到预期的location
        }
...
```

### 技巧二：nginx转发日志查看

1. 完整配置方法可以参见[官方文档](http://nginx.org/en/docs/debugging_log.html),主要是linux版本nginx要开启debug的编译配置。

2. windows版本默认开启了debug编译配置，所以只需要在配置中设置debug级别
```nginx
 access_log  logs/access.log  main;
 error_log   logs/error.log  debug;
```
3. 开启后主要需要看到日志如下：
可以看出当前请求匹配的location,以及匹配的具体过程。
```txt
2015/02/02 15:38:48 [debug] 5801#0: *60 test location: "/"
2015/02/02 15:38:48 [debug] 5801#0: *60 test location: "ii"
2015/02/02 15:38:48 [debug] 5801#0: *60 test location: "helloworld"
2015/02/02 15:38:48 [debug] 5801#0: *60 test location: ~ "/ii/[^\/]+/[^\/]+"
2015/02/02 15:38:48 [debug] 5801#0: *60 using configuration "/ii/[^\/]+/[^\/]+"
```

### 参考
https://blog.coding.net/blog/tips-in-configuring-nginx-location  
http://nginx.org/en/docs/debugging_log.html  
