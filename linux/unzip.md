

## 解压缩

### 直接解压

> unzip test.zip
```bash
# ll
total 4
-rw-r--r--. 1 root root 298 Jul 24 10:35 test.zip
#
#
# unzip test.zip
Archive:  test.zip
 extracting: 1.txt
 extracting: 2.txt
# ll
total 4
-rw-r--r--. 1 root root   0 Jul 24 10:35 1.txt
-rw-r--r--. 1 root root   0 Jul 24 10:35 2.txt
-rw-r--r--. 1 root root 298 Jul 24 10:35 test.zip
```

### 解压到文件夹
> unzip test.zip -d test  
```bash
# unzip test.zip -d test
Archive:  test.zip
 extracting: test/1.txt
 extracting: test/2.txt
# ll
total 8
drwxr-xr-x. 2 root root 4096 Jul 24 10:52 test
-rw-r--r--. 1 root root  298 Jul 24 10:35 test.zip
# ll test
total 0
-rw-r--r--. 1 root root 0 Jul 24 10:35 1.txt
-rw-r--r--. 1 root root 0 Jul 24 10:35 2.txt
```
