nginx知识
==


### 1. nginx调试

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






