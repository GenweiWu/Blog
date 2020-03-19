

### 可以用来测试网络
`nc -zv 1.2.3.4 80`

```console
[root@SZX1000538971 ~]# nc -v 1.2.3.4 80
Ncat: Version 6.40 ( http://nmap.org/ncat )
Ncat: Connected to 1.2.3.4:80.
^C
[root@SZX1000538971 ~]# nc -v 1.2.3.4 50000
Ncat: Version 6.40 ( http://nmap.org/ncat )
Ncat: Connection refused.
```
