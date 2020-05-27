
### 含义
```
sudo = Super user do 超级用户do  
su = Shift user  切换用户
```
- su用来切换用户，如`su - oracle`，此时要求输入切换到的用户的密码
- sudo是

### 对比
针对要切换到root的权限的情况
- su - root会导致所有用户都会知道root的密码，很不安全，而且权限不受控制了
- sudo 可以进行下放部分权限，让某些用户有部分权限(需要进行相关配置，针对不同用户，不同权限)


### sudo配置


### 参考
- https://blog.51cto.com/fuwenchao/1340685
- https://blog.csdn.net/kmust20093211/article/details/43565391
- https://blog.csdn.net/suwu150/article/details/71554302
