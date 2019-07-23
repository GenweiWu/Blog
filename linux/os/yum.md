## yum install localrpm

> 好处就是：  
(1)大的软件可以提前下载好，不会因为没有合适的repo无法下载;   
(2)同时还能自动下载依赖的包  
```console
 yum install -y gitlab-ce-12.0.3-ce.0.el7.x86_64.rpm
```


## disableplugin
如果不想使用某个插件，可以临时禁用

> https://www.tecmint.com/enable-disable-and-install-yum-plug-ins/
```
yum install --disableplugin=fastestmirror httpd
```

## 查找命令对应的包
```
yum provides find
```
