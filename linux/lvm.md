lvm
==


### 基本概念

- 物理分区  
  `例如 /dev/xvde,/dev/xvdf等，是实际的硬盘`
  
- pv(physical volume)物理卷  
  `只是在物理分区中划出了一个特殊的区域，用于记载与LVM相关的管理参数`
  
- VG (volume group)卷组  
  `一个或者多个物理卷的组合，即将多个pv组合成一个大的vg`
  
- LV (logical volume)逻辑卷  
  `在VG上就可以创建LV,拿来使用`
  
```
VG= ( PV1 + PV2 + PV3 )
相当于整合多个PV成一个大的卷，然后可以创建LV进行使用，便于多个磁盘的使用
```

- PE(physical extent)：物理区域
物理区域是物理卷中可用于分配的最小存储单元，物理区域大小在建立卷组时指定，一旦确定不能更改，同一卷组所有物理卷的物理区域大小需一致，新的pv加入到vg后，pe的大小自动更改为vg中定义的pe大小


### 使用

> PV
```bash
[root@shap000109594 ~]# pvs
File descriptor 3 (/etc/passwd) leaked on pvs invocation. Parent PID 51370: -bash
[root@shap000109594 ~]# pvcreate /dev/xvde
File descriptor 3 (/etc/passwd) leaked on pvcreate invocation. Parent PID 51370: -bash
WARNING: dos signature detected on /dev/xvde at offset 510. Wipe it? [y/n]: y
  Wiping dos signature on /dev/xvde.
  Physical volume "/dev/xvde" successfully created.
[root@shap000109594 ~]# pvs
File descriptor 3 (/etc/passwd) leaked on pvs invocation. Parent PID 51370: -bash
  PV         VG Fmt  Attr PSize  PFree
  /dev/xvde     lvm2 ---  90.00g 90.00g
```

> VG
```bash
[root@shap000109594 ~]# vgcreate vg1 /dev/xvde
File descriptor 3 (/etc/passwd) leaked on vgcreate invocation. Parent PID 51370: -bash
  Volume group "vg1" successfully created
[root@shap000109594 ~]# vgs
File descriptor 3 (/etc/passwd) leaked on vgs invocation. Parent PID 51370: -bash
  VG  #PV #LV #SN Attr   VSize   VFree
  vg1   1   0   0 wz--n- <90.00g <90.00g
```

> LV
```bash
[root@shap000109594 ~]# lvcreate -n lv_docker -L 30G vg1
File descriptor 3 (/etc/passwd) leaked on lvcreate invocation. Parent PID 51370: -bash
WARNING: ext3 signature detected on /dev/vg1/lv_docker at offset 1080. Wipe it? [y/n]: y
  Wiping ext3 signature on /dev/vg1/lv_docker.
  Logical volume "lv_docker" created.
[root@shap000109594 ~]# lvs
File descriptor 3 (/etc/passwd) leaked on lvs invocation. Parent PID 51370: -bash
  LV        VG  Attr       LSize  Pool Origin Data%  Meta%  Move Log Cpy%Sync Convert
  lv_docker vg1 -wi-a----- 30.00g
```


```bash
## fdisk
...
Disk /dev/mapper/vg1-lv_docker: 32.2 GB, 32212254720 bytes, 62914560 sectors
Units = sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes

## 格式化+挂载
[root@shap000109594 ~]# mkdir -p /var/lib/docker
[root@shap000109594 ~]# mkfs.ext3 /dev/mapper/vg1-lv_docker
[root@shap000109594 ~]# mount /dev/mapper/vg1-lv_docker /var/lib/docker/

## df -h
/dev/mapper/vg1-lv_docker   30G   45M   28G   1% /var/lib/docker
```

### 备注
- lv
```bash
## 可以指定使用功能100%或70%空间的方法
lvcreate -n lv_nfs -l 100%free vg1
## 或直接-L 指定大小
lvcreate -L 2G -n lv_2 vg_1
```

### 参考
- https://blog.csdn.net/lenovouser/article/details/54233570
- http://iamfangle.blogspot.com/2016/05/lvmssm.html
- https://blog.51cto.com/13584122/2113585



