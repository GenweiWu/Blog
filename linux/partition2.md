
## 分区扩大
```
/dev/xvde1 20G改成40G
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

##### 步骤2:增加分区大小
```
通过fdisk /dev/xvde进入分区管理，先删除旧分区(d)，然后新建一个分区(修改大小，保留起始柱面)
因为删除分区，数据还是在的！！！
```

##### 步骤3:检查文件系统，并增大文件系统
```
[root@SZX1000538990 ~]# e2fsck -f /dev/xvde1
e2fsck 1.44.4 (18-Aug-2018)
Pass 1: Checking inodes, blocks, and sizes
Pass 2: Checking directory structure
Pass 3: Checking directory connectivity
Pass 4: Checking reference counts
Pass 5: Checking group summary information
/dev/xvde1: 43/1310720 files (4.7% non-contiguous), 184968/5242880 blocks

[root@SZX1000538990 ~]# resize2fs /dev/xvde1
resize2fs 1.44.4 (18-Aug-2018)
Resizing the filesystem on /dev/xvde1 to 10485760 (4k) blocks.
The filesystem on /dev/xvde1 is now 10485760 (4k) blocks long.

```



##### 步骤4:重新mount回来(这一步我遇到的是自动就重新mount了)
```
mount /dev/xvde1 /var/lib/docker
```

### 参考
- https://blog.51cto.com/sandshell/2119523



