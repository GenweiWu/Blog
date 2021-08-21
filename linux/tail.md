

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
