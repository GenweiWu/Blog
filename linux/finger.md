
## finger可以用来查看用户信息(方便查看用户登录情况)

```CMD
[root@SZX1000535079 ~]# finger
Login     Name       Tty      Idle  Login Time   Office     Office Phone   Host
root      root       pts/0     10d  Jul  9 19:26                           (1.2.3.4)
root      root       pts/1      17  Aug  5 09:11                           (1.2.3.5)
root      root       pts/2          Aug  5 11:39                           (1.2.3.6)


[root@SZX1000535079 ~]# finger root
Login: root                             Name: root
Directory: /root                        Shell: /bin/bash
On since Tue Jul  9 19:26 (CST) on pts/0 from 1.2.3.4
   10 days 19 hours idle
On since Mon Aug  5 09:11 (CST) on pts/1 from 1.2.3.5
   18 minutes 21 seconds idle
On since Mon Aug  5 11:39 (CST) on pts/2 from 1.2.3.6
   5 seconds idle
No mail.
No Plan.
```

## 需要先安装下
```
yum install finger
```
