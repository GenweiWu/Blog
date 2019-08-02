
## 分区缩小
```
/dev/xvde1 100G改成25G
mount信息: /dev/xvde1  /var/lib/docker
```
### 操作步骤
##### 0.准备工作

> 查看分区信息
```CMD
fdisk -l 
```

> 查看mount信息
```CMD
df -h
```

##### 步骤1：umount目录
```
umount /var/lib/docker
```

##### 步骤2:先减少文件系统大小
```
resize2fs /dev/xvde1 25G
```

##### 步骤3:减少分区大小
```
通过fdisk /dev/xvde进入分区管理，先删除旧分区(d)，然后新建一个分区(修改大小，保留起始柱面)
因为删除分区，数据还是在的！！！
```

##### 步骤4:重新mount回来(这一步我遇到的是自动就重新mount了)

### 参考
- https://askubuntu.com/a/954777
- https://blog.51cto.com/loofeer/792739



