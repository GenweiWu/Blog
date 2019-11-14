
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

```
none
the “Referer” field is missing in the request header;
blocked
the “Referer” field is present in the request header, but its value has been deleted by a firewall or proxy server; such values are strings that do not start with “http://” or “https://”;
server_names
the “Referer” request header field contains one of the server names;
arbitrary string
defines a server name and an optional URI prefix. A server name can have an “*” at the beginning or end. During the checking, the server’s port in the “Referer” field is ignored;
regular expression
the first symbol should be a “~”. It should be noted that an expression will be matched against the text starting after the “http://” or “https://”.
```

### 参考
- http://nginx.org/en/docs/http/ngx_http_referer_module.html#valid_referers
- http://www.nginx.cn/657.html
- https://www.centos.bz/2017/09/nginx-ngx_http_referer_module-hotlink-protect/
- https://blog.csdn.net/senlin1202/article/details/50800146

### example

- [ ] TODO自我测试进行学习
