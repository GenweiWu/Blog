

### tail -n NUM 读取最后NUM行
### tail -n +NUM 读取第NUM行(包括)之后的所有行

```bash
[root@SZX1000538971 test]# cat 1.txt
Line 1
Line 2
Line 3
Line 4
Line 5
Line 6
Line 7
Line 8
Line 9
Line 10
Line 11
[root@SZX1000538971 test]# tail -n 3 1.txt
Line 9
Line 10
Line 11
[root@SZX1000538971 test]# tail -n +10 1.txt
Line 10
Line 11

```

## 实践

> 批量查看  
> cat无法显示文件名，可以通过tail -n +1实现
```bash
# ll
total 8
-rw-r--r--. 1 root root 15 Apr 21 17:18 1.txt
-rw-r--r--. 1 root root 15 Apr 21 17:18 2.txt
# cat 1.txt
11
12
13
14
15
# cat 2.txt
21
22
23
24
25
# cat *.txt
11
12
13
14
15
21
22
23
24
25
# tail -n +1 *.txt
==> 1.txt <==
11
12
13
14
15

==> 2.txt <==
21
22
23
24
25
```

