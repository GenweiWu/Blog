nginx 知识
==


## 1. nginx调试

#### 方法1.为了测试location是否匹配成功,可以巧妙使用错误码进行测试
```
location /XXX{
    return 600;
}
```

#### 方法2.增加日志

```conf
location ~* \.(js|css|png|html)$ {
  proxy_pass https://10.20.30.40:8542;
  error_log    logs/mydebug.log debug;
}
```

此时在日志中,可以看到类似的内容,方便定位(关注图中的host 10.20.30.40)
```
2018/03/06 20:09:28 [debug] 11852#13828: *199 http proxy header:
"GET /aaa/bbb/ccc.js HTTP/1.0
Host: 10.20.30.40:8542
Connection: close
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
Cookie: u-debug=true; irtt_locale=zh; _ha_id..1fff=f7ebb26d78cd5035; u-locale=en_US
If-None-Match: W/"3934-1520335439116"
If-Modified-Since: Tue, 06 Mar 2018 11:23:59 GMT
```


## 2. windows下https证书
```
openssl genrsa -des3 -out openssl.key 2048  
  
openssl req -new -x509 -key openssl.key -out openssl.crt -days 3650  
  
openssl rsa -in openssl.key -out openssl_nopass.key  
```
http://nassir.iteye.com/blog/1983667

## 3. nginx中location的写法

#### 匹配符号
```
= : 表示精确匹配后面的url
~ : 表示正则匹配，但是区分大小写
~* : 正则匹配，不区分大小写
^~ : 表示普通字符匹配，如果该选项匹配，只匹配该选项，不匹配别的选项，一般用来匹配目录
```

#### nginx正则的语法：
| .|换行符以外的其他字符|
|--|--|
|*|重复0次或多次|
|+|重复1次或多次|
|?|重复0次或1次|
|^|匹配开头|
|$|匹配结束|

所以，使用 `. `的时候要转义处理为 `\.` 

[另参见Issues](https://github.com/GenweiWu/Blog/issues/22)

## 4. nginx的root用法
本地静态资源转发，可以方便的用于测试nginx

```conf
server {
	listen      5000;
	
	root D:/2222/nginxDemo;	
}
```

> 目录信息如下
```
dave@PC MINGW64 /d/2222/nginxDemo
$ ll
total 2
-rw-r--r-- 1 w00284248 1049089  24 Nov 14 17:21 111.js
-rw-r--r-- 1 w00284248 1049089 188 Nov 14 17:20 index.html
```

则通过 `http://127.0.0.1:5000/index.html` 和 `http://127.0.0.1:5000/111.js` 就可以请求到目录下的文件了







