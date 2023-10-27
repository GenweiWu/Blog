


## 基本用法
```console
ssh 1.2.3.4

//-l指定用户
ssh -l root 1.2.3.4
```

## 执行命令
`ssh d@1.2.3.4 [command]`

### ✈️ 注意单引号和双引号
```console 
# 这里hostname是在本地执行的
[root@paas-controller-1 pict]# ssh -t ubuntu@minion-4 "sudo echo $(hostname)"
All activities performed on this system will be monitored.
paas-controller-1
Connection to minion-4 closed.

# 这里hostname是在ssh主机上执行的
[root@paas-controller-1 pict]# ssh -t ubuntu@minion-4 'sudo echo $(hostname)'
All activities performed on this system will be monitored.
minion-4
Connection to minion-4 closed.
```

### ✈️ docker restart 容器
> -d后面用双引号  
> 最外面用单引号  
```
[root@paas-controller-1 pict]# ssh -t ubuntu@minion-4 'sudo docker inspect $(echo `sudo docker ps |grep camera-img`|cut -d" " -f1)'
```
