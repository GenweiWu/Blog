
## umask

1. umask会屏蔽掉不想给予的权限
2. 文件/文件夹的权限 = 文件/文件夹的全权限值 - umask的值
3. 文件的全权限值是666(所有用户可读可写),文件夹的全权限值是777(所有用户可读可写可执行)

## 样例
```cmd
[root@SZX1000538970 test]# umask
0077
[root@SZX1000538970 test]# touch 111
[root@SZX1000538970 test]# mkdir 222
[root@SZX1000538970 test]# ll
total 4
-rw-------. 1 root root    0 Aug  5 14:22 111
drwx------. 2 root root 4096 Aug  5 14:22 222
```

可以看出:
- 文件111的权限为 666 - 077 = 600 rw-------
- 文件夹的权限为 777 - 077 = 700 rwx------

## cp时会创建一个新文件，默认不会拷贝权限，而是根据umask计算

```
[root@SZX1000538970 test]# ll
total 4
-rwxrwxrwx. 1 root root    0 Aug  5 14:22 111
drwx------. 2 root root 4096 Aug  5 14:22 222
[root@SZX1000538970 test]# 
[root@SZX1000538970 test]# cp 111 111bak
[root@SZX1000538970 test]# ll
total 4
-rwxrwxrwx. 1 root root    0 Aug  5 14:22 111
-rwx------. 1 root root    0 Aug  5 14:35 111bak
drwx------. 2 root root 4096 Aug  5 14:22 222
[root@SZX1000538970 test]# cp -p 111 111bakbak
[root@SZX1000538970 test]# ll
total 4
-rwxrwxrwx. 1 root root    0 Aug  5 14:22 111
-rwx------. 1 root root    0 Aug  5 14:35 111bak
-rwxrwxrwx. 1 root root    0 Aug  5 14:22 111bakbak
drwx------. 2 root root 4096 Aug  5 14:22 222
```
