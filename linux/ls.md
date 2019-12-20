
## 按照日期排序
-t按日志排序  
-r倒序  
```
ll -t
ll -tr
```

## 当前目录文件数
```
ls |wc -l
```

## 展示文件/文件夹的权限时，展示数字而不是别名
> see the UID of all files or folders
```
ls -n
```

```console
[root@SZX1000538971 gitlab]# ll
total 584
drwxr-xr-x. 18 root    root   4096 Dec 18 17:43 data
drwxrwxr-x.  4 root    root   4096 Dec 18 17:43 etc_gitlab
-rw-r--r--.  1 root    root 573573 Feb  1  2018 gitlabgxy.log
drwxr-xr-x. 21 polkitd root   4096 Oct 24  2017 logs
drwxr-xr-x.  3 root    root   4096 Sep  9  2017 share_files
[root@SZX1000538971 gitlab]# ll -n
total 584
drwxr-xr-x. 18   0 0   4096 Dec 18 17:43 data
drwxrwxr-x.  4   0 0   4096 Dec 18 17:43 etc_gitlab
-rw-r--r--.  1   0 0 573573 Feb  1  2018 gitlabgxy.log
drwxr-xr-x. 21 998 0   4096 Oct 24  2017 logs
drwxr-xr-x.  3   0 0   4096 Sep  9  2017 share_files

```
