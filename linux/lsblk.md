

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
