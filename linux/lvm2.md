
## 扩容:先扩容磁盘，再扩文件系统

```bash
## 扩容前
[root@SZX1000538971 ~]# df -h
Filesystem                 Size  Used Avail Use% Mounted on
...
/dev/mapper/vg1-lv_docker   20G  274M   19G   2% /var/lib/docker

## 检查剩余空间:检查可用PE
[root@SZX1000538971 ~]# vgdisplay vg1
  --- Volume group ---
  VG Name               vg1
  System ID
  Format                lvm2
  Metadata Areas        1
  Metadata Sequence No  2
  VG Access             read/write
  VG Status             resizable
  MAX LV                0
  Cur LV                1
  Open LV               1
  Max PV                0
  Cur PV                1
  Act PV                1
  VG Size               <100.00 GiB
  PE Size               4.00 MiB
  Total PE              25599
  Alloc PE / Size       5120 / 20.00 GiB
  Free  PE / Size       20479 / <80.00 GiB   //<---看这里，还剩多少闲置空间；如果未0，需要增加pv
  VG UUID               e2ohdB-uMm7-MZ8s-kj8a-WqS9-IcIC-eNhPpv
  

## 先扩容磁盘
[root@SZX1000538971 ~]# lvextend -L 25G /dev/mapper/vg1-lv_docker
  Size of logical volume vg1/lv_docker changed from 20.00 GiB (5120 extents) to 25.00 GiB (6400 extents).
  Logical volume vg1/lv_docker successfully resized.
[root@SZX1000538971 ~]# lvs
  LV        VG  Attr       LSize  Pool Origin Data%  Meta%  Move Log Cpy%Sync Convert
  lv_docker vg1 -wi-ao---- 25.00g


## 再修改文件系统
[root@SZX1000538971 ~]# resize2fs /dev/mapper/vg1-lv_docker
resize2fs 1.44.4 (18-Aug-2018)
Filesystem at /dev/mapper/vg1-lv_docker is mounted on /var/lib/docker; on-line resizing required
old_desc_blocks = 2, new_desc_blocks = 2
The filesystem on /dev/mapper/vg1-lv_docker is now 6553600 (4k) blocks long.

## 扩容完成
[root@SZX1000538971 ~]# df -h
Filesystem                 Size  Used Avail Use% Mounted on
...
/dev/mapper/vg1-lv_docker   25G  274M   24G   2% /var/lib/docker  //<---已变成25G

```
