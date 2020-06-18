
## 常用命令
```bash
systemctl start docker
systemctl stop docker 
systemctl restart docker

//查看服务的启动状态
systemctl status docker
//查看服务的配置信息
systemctl show docker
```

```bash
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

```bash
## 批量查看自启动状态
root@shap000108982:~# systemctl list-unit-files |grep kube
kube-apiserver.service                     disabled
kube-controller-manager.service            disabled
kube-scheduler.service                     disabled

## 批量查看运行状态
root@shap000108982:~# systemctl list-units |grep kube
kube-apiserver.service                                                                                loaded active running   Kubernetes API Service
kube-controller-manager.service                                                                       loaded active running   Kubernetes Controller Manager
kube-scheduler.service 
```



## 参考
- http://www.ruanyifeng.com/blog/2016/03/systemd-tutorial-commands.html
