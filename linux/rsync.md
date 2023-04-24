
## 如果scp -p无法满足要求，就使用rsync

> https://serverfault.com/a/371423  
> scp不保留属主信息  
```
That is correct. "-p" does not do that. See the man page:

     -p      Preserves modification times, access times, and modes from the
             original file.
Notice it says times and modes, NOT user/group ownership.
```

## rsync
```bash
rsync -av source 192.0.2.1:/dest/ination
```

```bash
##机器A
[root@SZX1000538971 222]# pwd
/home/dave/111/222
[root@SZX1000538971 222]# ll
total 0
-rw-------. 1 test test 0 Sep 29 14:14 test.txt


## scp -rp拷贝
scp -rp /home/dave/111/222/ root@1.2.3.4:/home/dave
## 使用scp拷贝，B机器上结果权限不对
[root@SZX1000538972 dave]# pwd
/home/dave
[root@SZX1000538972 dave]# ll 222/
total 0
-rw-------. 1 root root 0 Sep 29  2020 test.txt

## rsync拷贝
 rsync -av /home/dave/111/222 root@1.2.3.4:/home/dave
## 此时权限是对的 
[root@SZX1000538972 dave]# pwd
/home/dave
[root@SZX1000538972 dave]# ll 222/
total 0
-rw-------. 1 1000 1000 0 Sep 29  2020 test.txt
```

## windows上通过安装`mobaxterm`软件也可以用
![image](https://user-images.githubusercontent.com/16630659/233879507-b4598ccd-08a1-4d60-b9f4-2198f2fa1f6f.png)


## 参考
- https://www.ruanyifeng.com/blog/2020/08/rsync.html

