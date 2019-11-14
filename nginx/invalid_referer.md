
## nginx防盗链

```conf
valid_referers none blocked server_names
               *.example.com example.* www.example.org/galleries/
               ~\.google\.;

if ($invalid_referer) {
    return 403;
}
```

### 语法
```
Syntax:	valid_referers none | blocked | server_names | string ...;
Default:	—
Context:	server, location
```


#### > none
the “Referer” field is missing in the request header;  
禁止空referer，即不允许用户直接访问该资源，因为直接在浏览器的地址栏中输入一个资源的URL地址，请求是不会包含Referer字段的

#### > blocked
the “Referer” field is present in the request header, but its value has been deleted by a firewall or proxy server; such values are strings that do not start with “http://” or “https://”;
#### > server_names
the “Referer” request header field contains one of the server names;  
我的理解是，server_names会转而去读取`server_name`的值，相当于把`server_name`放到`valid_referers`中

#### > arbitrary string
defines a server name and an optional URI prefix. A server name can have an “*” at the beginning or end. During the checking, the server’s port in the “Referer” field is ignored;
#### > regular expression
the first symbol should be a “~”. It should be noted that an expression will be matched against the text starting after the “http://” or “https://”.


### 参考
- http://nginx.org/en/docs/http/ngx_http_referer_module.html#valid_referers
- http://www.nginx.cn/657.html
- https://www.centos.bz/2017/09/nginx-ngx_http_referer_module-hotlink-protect/
- https://blog.csdn.net/senlin1202/article/details/50800146

## 样例分析
### 0) 准备文件如下
```
dave@PC MINGW64 /d/2222/nginxDemo
$ ll
total 2
-rw-r--r-- 1 w00284248 1049089  24 Nov 14 17:21 111.js
-rw-r--r-- 1 w00284248 1049089 188 Nov 14 17:20 index.html

$ cat index.html
<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <title>Hello</title>
</head>
<body>
        <h2>Hello</h2>
</body>
<script type="text/javascript" src="111.js"/>
</html>
```

在index.html中会去请求111.js



### 1) 只设置简单的none和blocked是不被允许的
```
###### 测试nginx invalid_refer用

> 只设置简单的none和blocked是不被允许的
server {
	listen      5000;
	
	root D:/2222/nginxDemo;
	
	valid_referers none blocked;
	if ($invalid_referer) {
	  return 403;
	}

}
```
> 报错如下
```
nginx: [emerg] the "none" or "blocked" referers are specified in the "valid_referers" directive without any valid referer
```

### 2) server_names的用法
我的理解是配置了server_names就会去把server_name的当前域名加到valid_referers中一样

注意：
```
127.0.0.1 nginx.test.com
```

```
###### 测试nginx invalid_refer用
server {
	listen      5000;
	
	root D:/2222/nginxDemo;
	
	server_name nginx.test.com;
	
	valid_referers none blocked server_names;
	if ($invalid_referer) {
	  return 403;
	}

}
```

相当于
```
###### 测试nginx invalid_refer用
server {
	listen      5000;
	
	root D:/2222/nginxDemo;

	valid_referers none blocked nginx.test.com;
	if ($invalid_referer) {
	  return 403;
	}

}
```

### 3) 自行设置的IP或域名
```
###### 测试nginx invalid_refer用
server {
	listen      5000;
	
	root D:/2222/nginxDemo;
	
	server_name nginx.test.com;
	
	valid_referers none blocked 127.0.0.1 localhost server_names;
	if ($invalid_referer) {
	  return 403;
	}

}
```

- none 允许直接请求
  - http:/127.0.0.1:5000/index.html获取html本身 ok
  - http:/127.0.0.1:5000/index.html中请求111.js failed
  - http:/127.0.0.1:5000/index.js ok  只要是直接请求，无论请求IP还是域名都会ok

- 127.0.0.1
  - http:/127.0.0.1:5000/index.html获取html本身 ok
  - http:/127.0.0.1:5000/index.html中请求111.js ok
  - http:/127.0.0.1:5000/index.js ok
  - http:/localhost:5000/index.html中请求111.js failed
  - http:/nginx.test.com:5000/index.html中请求111.js failed

- localhost
  - http:/localhost:5000/index.html获取html本身 ok
  - http:/localhost:5000/index.html中请求111.js ok
  - http:/localhost:5000/index.js ok  
  - http:/127.0.0.1:5000/index.html中请求111.js failed
  - http:/nginx.test.com:5000/index.html中请求111.js failed
  
- server_names
  - http:/nginx.test.com:5000/index.html获取html本身 ok
  - http:/nginx.test.com:5000/index.html中请求111.js ok
  - http:/nginx.test.com:5000/index.js ok 
  - http:/127.0.0.1:5000/index.html中请求111.js failed
  - http:/localhost:5000/index.html中请求111.js failed
  
