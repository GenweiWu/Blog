
## 1. 软链接
软链接有点类似快捷方式

```
//建立软链接
ln -s 源文件/目录 目标文件/目录

//修改软链接
ln -snf 源文件/目录 目标文件/目录

//删除软链接
rm -f 源文件/目录
```

### 1.1 文件的软链接

> 1.建立软链接
```console
[root@SZX1000536229 test]# ll
total 4
-rw-------. 1 root root 16 Jul 10 16:03 log2019.log
[root@SZX1000536229 test]# ln -s log2019.log link2019
[root@SZX1000536229 test]# ll
total 4
lrwxrwxrwx. 1 root root 11 Jul 10 16:03 link2019 -> log2019.log
-rw-------. 1 root root 16 Jul 10 16:03 log2019.log
[root@SZX1000536229 test]# cat log2019.log 

log2019
hahaha
[root@SZX1000536229 test]# cat link2019 

log2019
hahaha
```

> 2.修改软链接
```console
[root@SZX1000536229 test]# ll
total 4
lrwxrwxrwx. 1 root root 11 Jul 10 16:03 link2019 -> log2019.log
-rw-------. 1 root root 16 Jul 10 16:03 log2019.log
[root@SZX1000536229 test]# echo 'new log' > log2020.log
[root@SZX1000536229 test]# ll
total 8
lrwxrwxrwx. 1 root root 11 Jul 10 16:03 link2019 -> log2019.log
-rw-------. 1 root root 16 Jul 10 16:03 log2019.log
-rw-------. 1 root root  8 Jul 10 16:09 log2020.log
[root@SZX1000536229 test]# ln -s log2020.log link2019
ln: failed to create symbolic link 鈥榣ink2019鈥 File exists
[root@SZX1000536229 test]# ln -snf log2020.log link2019
[root@SZX1000536229 test]# ll
total 8
lrwxrwxrwx. 1 root root 11 Jul 10 16:11 link2019 -> log2020.log
-rw-------. 1 root root 16 Jul 10 16:03 log2019.log
-rw-------. 1 root root  8 Jul 10 16:09 log2020.log
[root@SZX1000536229 test]# cat link2019 
new log
```

> 3.删除软链接
```console
[root@SZX1000536229 test]# ll
total 4
lrwxrwxrwx. 1 root root 11 Jul 10 16:18 link2019 -> log2019.log
-rw-------. 1 root root 16 Jul 10 16:03 log2019.log
[root@SZX1000536229 test]# rm -f link2019 
[root@SZX1000536229 test]# ll
total 4
-rw-------. 1 root root 16 Jul 10 16:03 log2019.log
```

### 1.2 目录的软链接

> 1.新建目录软链接
```console
[root@SZX1000536229 test]# ll
total 4
drwx------. 2 root root 4096 Jul 10 16:22 test
[root@SZX1000536229 test]# ll test/
total 4
-rw-------. 1 root root 6 Jul 10 16:22 hello.txt
[root@SZX1000536229 test]# ln -s test/  linktest
[root@SZX1000536229 test]# ll
total 4
lrwxrwxrwx. 1 root root    5 Jul 10 16:25 linktest -> test/
drwx------. 2 root root 4096 Jul 10 16:22 test
[root@SZX1000536229 test]# ll linktest/
total 4
-rw-------. 1 root root 6 Jul 10 16:22 hello.txt
```

> 2.修改目录软链接
```console
[root@SZX1000536229 test]# ll
total 8
lrwxrwxrwx. 1 root root    7 Jul 10 16:31 linktest -> test222
drwx------. 2 root root 4096 Jul 10 16:22 test
drwx------. 2 root root 4096 Jul 10 16:33 test222
[root@SZX1000536229 test]# ll test
total 4
-rw-------. 1 root root 6 Jul 10 16:22 hello.txt
[root@SZX1000536229 test]# ll test222/
total 4
-rw-------. 1 root root 8 Jul 10 16:31 hello222.txt
[root@SZX1000536229 test]# ll linktest
lrwxrwxrwx. 1 root root 7 Jul 10 16:31 linktest -> test222
[root@SZX1000536229 test]# ll linktest/
total 4
-rw-------. 1 root root 8 Jul 10 16:31 hello222.txt
[root@SZX1000536229 test]# ln -snf test linktest
[root@SZX1000536229 test]# ll test
total 4
-rw-------. 1 root root 6 Jul 10 16:22 hello.txt
[root@SZX1000536229 test]# ll test222
total 4
-rw-------. 1 root root 8 Jul 10 16:31 hello222.txt
[root@SZX1000536229 test]# ll linktest
lrwxrwxrwx. 1 root root 4 Jul 10 16:34 linktest -> test
[root@SZX1000536229 test]# ll linktest/
total 4
-rw-------. 1 root root 6 Jul 10 16:22 hello.txt
```

> 3.删除目录软链接
```console
[root@SZX1000536229 test]# ll
total 4
lrwxrwxrwx. 1 root root    5 Jul 10 16:38 linktest -> test/
drwx------. 2 root root 4096 Jul 10 16:22 test
[root@SZX1000536229 test]# rm -f linktest/
rm: cannot remove 鈥榣inktest/鈥 Is a directory
[root@SZX1000536229 test]# ll
total 4
lrwxrwxrwx. 1 root root    5 Jul 10 16:38 linktest -> test/
drwx------. 2 root root 4096 Jul 10 16:22 test
[root@SZX1000536229 test]# rm -f linktest
[root@SZX1000536229 test]# ll
total 4
drwx------. 2 root root 4096 Jul 10 16:22 test
```


## 2. 参考
- https://www.cnblogs.com/peida/archive/2012/12/11/2812294.html
