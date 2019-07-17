

## fdisk -l

```console
10:~ # fdisk -l
Disk /dev/xvda: 40 GiB, 42949672960 bytes, 83886080 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x00048a0c

Device     Boot   Start      End  Sectors Size Id Type
/dev/xvda1         2048  8386559  8384512   4G 82 Linux swap / Solaris
/dev/xvda2 *    8386560 83886079 75499520  36G 83 Linux


Disk /dev/xvde: 100 GiB, 107374182400 bytes, 209715200 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x42b283e1

Device     Boot Start       End   Sectors Size Id Type
/dev/xvde1       2048 125827890 125825843  60G 83 Linux
```

上面表示有两块磁盘，分别是`/dev/xvda`和`/dev/xvde`;  
其中`/dev/xvda`大小为40G，且已经分区为 `/dev/xvda1` 4G和 `/dev/xvda2` 36G  
而`/dev/xvde`大小为100G,目前只有分区`/dev/xvde1`为60G,剩余40G没有分区  


## fdisk /dev/xcde 进行分区操作
http://man.linuxde.net/fdisk
