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
