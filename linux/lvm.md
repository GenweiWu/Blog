


### 基本概念

- 物理分区
  例如 /dev/xvde,/dev/xvdf等，是实际的硬盘
  
- pv物理卷
  只是在物理分区中划出了一个特殊的区域，用于记载与LVM相关的管理参数
  
- VG 卷组
  一个或者多个物理卷的组合，即将多个pv组合成一个大的vg
  
- LV 逻辑卷
  在VG上就可以创建LV,拿来使用
  

VG= ( PV1 + PV2 + PV3 )
相当于整合多个PV成一个大的卷，然后可以创建LV进行使用，便于多个磁盘的使用


### 使用



### 参考
- https://blog.csdn.net/lenovouser/article/details/54233570
- http://iamfangle.blogspot.com/2016/05/lvmssm.html



