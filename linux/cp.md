

### `cp -p`拷贝时保留权限信息
```
 -p     same as --preserve=mode,ownership,timestamps
```

### `cp -r`拷贝文件夹
```console
[root@SZX1000538971 dave]# ll dir111/dir222/222.log
-rw-------. 1 root root 0 Mar 13 17:00 dir111/dir222/222.log
[root@SZX1000538971 dave]# cp dir111/ dir111_bak/
cp: omitting directory ‘dir111/’

//dir111目录下存在其他目录或文件存在，不可只使用cp命令实现复制操作

[root@SZX1000538971 dave]# cp -r dir111/ dir111_bak/
[root@SZX1000538971 dave]# ll dir111_bak/dir222/222.log
-rw-------. 1 root root 0 Mar 13 17:01 dir111_bak/dir222/222.log
```
