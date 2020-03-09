centos
==


## 配置全局proxy

> 参考：https://www.cnblogs.com/cocowool/archive/2012/07/05/2578487.html  
> 修改后需要重启终端
```
如果需要为某个用户设置一个系统级的代理，可以在~/.bash_profile中设置：
 
http_proxy="http://username:password@proxy_ip:port"
export http_proxy
 
上面的设置只对某个用户生效，如果要对所有系统用户生效，写在/etc/profile中就可以了。
```

## epel软件源
https://mirror.tuna.tsinghua.edu.cn/help/epel/

