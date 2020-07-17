


### 基本概念

- 物理分区  
  `例如 /dev/xvde,/dev/xvdf等，是实际的硬盘`
  
- pv物理卷  
  `只是在物理分区中划出了一个特殊的区域，用于记载与LVM相关的管理参数`
  
- VG 卷组  
  `一个或者多个物理卷的组合，即将多个pv组合成一个大的vg`
  
- LV 逻辑卷  
  `在VG上就可以创建LV,拿来使用`
  
```
VG= ( PV1 + PV2 + PV3 )
相当于整合多个PV成一个大的卷，然后可以创建LV进行使用，便于多个磁盘的使用
```

### 使用

> PV
```
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
```
[root@shap000109594 ~]# vgcreate vg1 /dev/xvde
File descriptor 3 (/etc/passwd) leaked on vgcreate invocation. Parent PID 51370: -bash
  Volume group "vg1" successfully created
[root@shap000109594 ~]# vgs
File descriptor 3 (/etc/passwd) leaked on vgs invocation. Parent PID 51370: -bash
  VG  #PV #LV #SN Attr   VSize   VFree
  vg1   1   0   0 wz--n- <90.00g <90.00g
```

LV
```
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


### 参考
- https://blog.csdn.net/lenovouser/article/details/54233570
- http://iamfangle.blogspot.com/2016/05/lvmssm.html



