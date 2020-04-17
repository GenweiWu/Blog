
## 常用命令
```
systemctl start docker
systemctl stop docker 
systemctl restart docker

//查看服务的启动状态
systemctl status docker
//查看服务的配置信息
systemctl show docker
```

```
//查看、修改服务的配置文件
systemctl cat docker
----
systemctl cat docker
# /usr/lib/systemd/system/docker.service
[Unit]
....
-----

//通过上面的方法看到文件路径后进行修改
vim xxx
systemctl daemon-reload
```



## 参考
- http://www.ruanyifeng.com/blog/2016/03/systemd-tutorial-commands.html