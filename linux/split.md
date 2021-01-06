
## 分割

```
split -b 500m nginx.tar.gz
```

```bash
// -b指定分割大小
// -d指定分割后的文件名用数字命名
// nginx是指定分割后的文件的前缀
split -b 500m nginx.tar.gz -d nginx

-rw-------. 1 root root 500M Jan  6 10:08 nginx00
-rw-------. 1 root root 148M Jan  6 10:08 nginx01
-rw-------. 1 root root 648M Jan  6 09:53 nginx.tar.gz

```

```bash
# split -b 500m nginx.tar.gz -d nginx.
# ll -h
-rw-------. 1 root root 500M Jan  6 10:46 nginx.00
-rw-------. 1 root root 148M Jan  6 10:46 nginx.01

```

## 合并+md5校验

```bash
cat nginx0* > nginx222.tar.gz

# ll -h
-rw-------. 1 root root 648M Jan  6 10:11 nginx222.tar.gz
-rw-------. 1 root root 648M Jan  6 09:53 nginx.tar.gz

# md5sum nginx.tar.gz
4c835cc100e90f568ba493e7103d6859  nginx.tar.gz
# md5sum nginx222.tar.gz
4c835cc100e90f568ba493e7103d6859  nginx222.tar.gz

```
