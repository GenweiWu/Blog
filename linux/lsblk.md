

### 展示磁盘分区信息

```console
[root@SZX1000293349 ~]# lsblk
NAME                                                                                     MAJ:MIN RM  SIZE RO TYPE MOUNTPOINT
sr0                                                                                       11:0    1 1024M  0 rom
xvda                                                                                     202:0    0   40G  0 disk
├─xvda1                                                                                  202:1    0   36G  0 part /
└─xvda2                                                                                  202:2    0    4G  0 part [SWAP]
xvde                                                                                     202:64   0  100G  0 disk
└─xvde1                                                                                  202:65   0  100G  0 part

```

### 可以对比lvm信息看下

```console
# lsblk
NAME                   MAJ:MIN RM  SIZE RO TYPE MOUNTPOINT
vda                    252:0    0  200G  0 disk
├─vda1                 252:1    0   10M  0 part
├─vda2                 252:2    0  100M  0 part /boot/efi
├─vda3                 252:3    0    1G  0 part /boot
├─vda4                 252:4    0  8.1G  0 part
│ ├─vg_sys-lv_root     253:0    0    4G  0 lvm  /
│ ├─vg_sys-lv_tmp      253:1    0    1G  0 lvm  /tmp
│ ├─vg_sys-lv_home     253:2    0    1G  0 lvm  /home
│ └─vg_sys-lv_var      253:3    0    2G  0 lvm  /var
├─vda5                 252:5    0  853M  0 part
└─vda6                 252:6    0   90G  0 part
  └─vg_paasdata-lv_gsl 253:4    0   89G  0 lvm  /paasdata
[root@test ~]#
[root@test ~]#
[root@test ~]# pvs
  PV         VG          Fmt  Attr PSize  PFree
  /dev/vda4  vg_sys      lvm2 a--   8.05g   56.00m
  /dev/vda6  vg_paasdata lvm2 a--  89.99g 1016.00m
[root@test ~]# vgs
  VG          #PV #LV #SN Attr   VSize  VFree
  vg_paasdata   1   1   0 wz--n- 89.99g 1016.00m
  vg_sys        1   4   0 wz--n-  8.05g   56.00m
[root@test ~]# lvs
  LV      VG          Attr       LSize  Pool Origin Data%  Meta%  Move Log Cpy%Sync Convert
  lv_gsl  vg_paasdata -wi-ao---- 89.00g
  lv_home vg_sys      -wi-ao----  1.00g
  lv_root vg_sys      -wi-ao----  4.00g
  lv_tmp  vg_sys      -wi-ao----  1.00g
  lv_var  vg_sys      -wi-ao----  2.00g

```
