curl命令
==

### 1. 自定义header

```
这种写法是错误的
curl --header "username:admin;password:admin123" www.baidu.com

这个才是对的
curl --header "username:admin"  --header "password:admin123" www.baidu.com
```

- 多个header要分开写

### 2. 显示请求速度

1. Create a new file, curl-format.txt, and paste in:
```
    time_namelookup:  %{time_namelookup}\n
       time_connect:  %{time_connect}\n
    time_appconnect:  %{time_appconnect}\n
   time_pretransfer:  %{time_pretransfer}\n
      time_redirect:  %{time_redirect}\n
 time_starttransfer:  %{time_starttransfer}\n
                    ----------\n
         time_total:  %{time_total}\n
```
2.Make a request:
```
curl -w "@curl-format.txt" -o /dev/null -s "http://wordpress.com/"
Or on Windows, it's...

curl -w "@curl-format.txt" -o NUL -s "http://wordpress.com/"
```

[原始链接](https://stackoverflow.com/a/22625150)

### 格式化json
`curl xxxxx |python -m json.tool`
`curl xxxxx |tac |tac|python -m json.tool`

---
### 参考
[curl网站开发指南 阮一峰](http://www.ruanyifeng.com/blog/2011/09/curl.html)
