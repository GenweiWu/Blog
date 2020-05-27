

## 递归修改文件夹权限

To change all the directories to 755 (drwxr-xr-x):
```
find /opt/lampp/htdocs -type d -exec chmod 755 {} \;
```

To change all the files to 644 (-rw-r--r--):
```
find /opt/lampp/htdocs -type f -exec chmod 644 {} \;
```

### chmod -R /dir111 755
不过这个效果是，目录下的所有目录和文件的权限都会被改成755(/dir111也被改成755)

## `chmod +x ` VS `chmod a+x`

> 一个会依赖umask值，一个不会  
> https://askubuntu.com/a/1075089
```
chmod +x is equal to chmod ugo+x (Based on umask value)
chmod a+x is equal to chmod ugo+x (Without considering umask value)
```

#### 样例
```console
[root@SZX1000538971 test]# umask
0077
[root@SZX1000538971 test]# umask -S
u=rwx,g=,o=
[root@SZX1000538971 test]# chmod +x 111.sh
[root@SZX1000538971 test]# ll
total 0
-rwx------. 1 root root 0 Mar 14 15:57 111.sh
```

```console
[root@SZX1000538971 test]# ll
total 0
-rw-------. 1 root root 0 Mar 14 15:58 111.sh
[root@SZX1000538971 test]# umask 0007
[root@SZX1000538971 test]# umask -S
u=rwx,g=rwx,o=
[root@SZX1000538971 test]# chmod +x 111.sh
[root@SZX1000538971 test]# ll
total 0
-rwx--x---. 1 root root 0 Mar 14 15:58 111.sh
```

```console
[root@SZX1000538971 test]# ll
total 0
-rw-------. 1 root root 0 Mar 14 16:00 111.sh
[root@SZX1000538971 test]# umask 0777
[root@SZX1000538971 test]# umask -S
u=,g=,o=
[root@SZX1000538971 test]# chmod +x 111.sh
[root@SZX1000538971 test]# ll
total 0
-rw-------. 1 root root 0 Mar 14 16:00 111.sh

//注意对比这里
[root@SZX1000538971 test]# chmod a+x 111.sh
[root@SZX1000538971 test]# ll
total 0
-rwx--x--x. 1 root root 0 Mar 14 16:00 111.sh
```

