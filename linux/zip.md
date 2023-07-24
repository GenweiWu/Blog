

## 压缩

### - 压缩文件
```bash
# ll
total 0
-rw-r--r--. 1 root root 0 Jul 24 10:35 1.txt
-rw-r--r--. 1 root root 0 Jul 24 10:35 2.txt
#
# zip test.zip *.txt
  adding: 1.txt (stored 0%)
  adding: 2.txt (stored 0%)
```

### - 压缩文件夹
```bash
# ll ddd
total 0
-rw-r--r--. 1 root root 0 Jul 24 10:37 dir1.txt
-rw-r--r--. 1 root root 0 Jul 24 10:37 dir2.txt
#
# zip -r ddd.zip ./ddd
updating: ddd/ (stored 0%)
  adding: ddd/dir2.txt (stored 0%)
  adding: ddd/dir1.txt (stored 0%)
```




