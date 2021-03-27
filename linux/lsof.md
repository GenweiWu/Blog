
## 命令参数

```
lsof -i 用以显示符合条件的进程情况
lsof -i[46] [protocol][@hostname|hostaddr][:service|port]
            46 --> IPv4 or IPv6
            protocol --> TCP or UDP
            hostname --> Internet host name
            hostaddr --> IPv4地址
            service --> /etc/service中的 service name (可以不只一个)
            port --> 端口号 (可以不只一个)
```

```
//不将IP转换为hostname，缺省是不加上-n参数
lsof -n 

//不将端口号转换成portNames
lsof -P 
```

```
//显示command中包含指定字符的，所有打开的文件,它不等于 'lsof |grep string'，因为grep会搜索所有列
lsof -c <string>
    
//显示指定进程打开的文件
lsof -p <PID>
```

> man 
```
[root@SZX1000538990 ~]# lsof -h
lsof 4.87
 latest revision: ftp://lsof.itap.purdue.edu/pub/tools/unix/lsof/
 latest FAQ: ftp://lsof.itap.purdue.edu/pub/tools/unix/lsof/FAQ
 latest man page: ftp://lsof.itap.purdue.edu/pub/tools/unix/lsof/lsof_man
 usage: [-?abhKlnNoOPRtUvVX] [+|-c c] [+|-d s] [+D D] [+|-f[gG]] [+|-e s]
 [-F [f]] [-g [s]] [-i [i]] [+|-L [l]] [+m [m]] [+|-M] [-o [o]] [-p s]
[+|-r [t]] [-s [p:s]] [-S [t]] [-T [t]] [-u s] [+|-w] [-x [fl]] [-Z [Z]] [--] [names]
Defaults in parentheses; comma-separated set (s) items; dash-separated ranges.
  -?|-h list help          -a AND selections (OR)     -b avoid kernel blocks
  -c c  cmd c ^c /c/[bix]  +c w  COMMAND width (9)    +d s  dir s files
  -d s  select by FD set   +D D  dir D tree *SLOW?*   +|-e s  exempt s *RISKY*
  -i select IPv[46] files  -K list tasKs (threads)    -l list UID numbers
  -n no host names         -N select NFS files        -o list file offset
  -O no overhead *RISKY*   -P no port names           -R list paRent PID
  -s list file size        -t terse listing           -T disable TCP/TPI info
  -U select Unix socket    -v list version info       -V verbose search
  +|-w  Warnings (+)       -X skip TCP&UDP* files     -Z Z  context [Z]
  -- end option scan
  +f|-f  +filesystem or -file names     +|-f[gG] flaGs
  -F [f] select fields; -F? for help
  +|-L [l] list (+) suppress (-) link counts < l (0 = all; default = 0)
                                        +m [m] use|create mount supplement
  +|-M   portMap registration (-)       -o o   o 0t offset digits (8)
  -p s   exclude(^)|select PIDs         -S [t] t second stat timeout (15)
  -T qs TCP/TPI Q,St (s) info
  -g [s] exclude(^)|select and print process group IDs
  -i i   select by IPv[46] address: [46][proto][@host|addr][:svc_list|port_list]
  +|-r [t[m<fmt>]] repeat every t seconds (15);  + until no files, - forever.
       An optional suffix to t is m<fmt>; m must separate t from <fmt> and
      <fmt> is an strftime(3) format for the marker line.
  -s p:s  exclude(^)|select protocol (p = TCP|UDP) states by name(s).
  -u s   exclude(^)|select login|UID set s
  -x [fl] cross over +d|+D File systems or symbolic Links
  names  select named files or files on named file systems
Anyone can list all files; /dev warnings disabled; kernel ID check disabled.
```


## 实例

#### 1. 根据进程信息找到文件路径 `lsof -p <PID>`
    不知道jenkins.war的路径，可以根据进程查找到

```console
root@SHA1000140068:~# ps -ef|grep jenkins
root      92484  91962  0 10:48 pts/1    00:00:00 grep --color=auto jenkins
root     118468      1  3  2018 ?        18-08:50:03 java -jar jenkins.war --requestHeaderSize=32768 --prefix=/jenkins/main-master
root@SHA1000140068:~# lsof -p 118468|grep 'jenkins\.war'
java    118468 root  mem       REG              202,2 69874457     318888 /home/jenkins/jenkins.war
java    118468 root    4r      REG              202,2 69874457     318888 /home/jenkins/jenkins.war
```

#### 2. 查找端口22的连接信息 `lsof -i:22`
```console
[root@SZX1000538990 ~]# lsof -i:22
COMMAND   PID USER   FD   TYPE   DEVICE SIZE/OFF NODE NAME
sshd     1340 root    3u  IPv4    18012      0t0  TCP *:ssh (LISTEN)
sshd     1340 root    4u  IPv6    18014      0t0  TCP *:ssh (LISTEN)
sshd    55797 root    3u  IPv4 50722423      0t0  TCP SZX1000538990:ssh->10.11.12.13:futrix (ESTABLISHED)
sshd    57463 root    3u  IPv4 50727746      0t0  TCP SZX1000538990:ssh->10.11.12.13:mcs-calypsoicf (ESTABLISHED)
[root@SZX1000538990 ~]# lsof -i:22 -n
COMMAND   PID USER   FD   TYPE   DEVICE SIZE/OFF NODE NAME
sshd     1340 root    3u  IPv4    18012      0t0  TCP *:ssh (LISTEN)
sshd     1340 root    4u  IPv6    18014      0t0  TCP *:ssh (LISTEN)
sshd    55797 root    3u  IPv4 50722423      0t0  TCP 10.21.253.119:ssh->10.11.12.13:futrix (ESTABLISHED)
sshd    57463 root    3u  IPv4 50727746      0t0  TCP 10.21.253.119:ssh->10.11.12.13:mcs-calypsoicf (ESTABLISHED)
[root@SZX1000538990 ~]# lsof -i:22 -nP
COMMAND   PID USER   FD   TYPE   DEVICE SIZE/OFF NODE NAME
sshd     1340 root    3u  IPv4    18012      0t0  TCP *:22 (LISTEN)
sshd     1340 root    4u  IPv6    18014      0t0  TCP *:22 (LISTEN)
sshd    55797 root    3u  IPv4 50722423      0t0  TCP 10.21.253.119:22->10.11.12.13:2358 (ESTABLISHED)
sshd    57463 root    3u  IPv4 50727746      0t0  TCP 10.21.253.119:22->10.11.12.13:3330 (ESTABLISHED)
```

#### 3. 根据command进行查询 `lsof -c java`

> 注意这里jenkins的command实际上是java而不是jenkins
```console
root@SHA1000140068:~# ps -ef|grep jenkins
root      10749   9732  0 14:18 pts/1    00:00:00 grep --color=auto jenkins
root     118468      1  3  2018 ?        18-08:53:41 java -jar jenkins.war --requestHeaderSize=32768 --prefix=/jenkins/main-master
root@SHA1000140068:~# lsof -p 118468|grep 'jenkins\.war'
java    118468 root  mem       REG              202,2 69874457     318888 /home/jenkins/jenkins.war
java    118468 root    4r      REG              202,2 69874457     318888 /home/jenkins/jenkins.war
root@SHA1000140068:~# lsof -c jenkins
root@SHA1000140068:~# lsof -c java |grep 'jenkins\.war'
java    118468 root  mem       REG              202,2 69874457     318888 /home/jenkins/jenkins.war
java    118468 root    4r      REG              202,2 69874457     318888 /home/jenkins/jenkins.war
```

#### 4. 查找文件/目录当前被哪个进程使用
```
root@SHA1000140068:/# lsof  /home/jenkins/
COMMAND    PID USER   FD   TYPE DEVICE SIZE/OFF   NODE NAME
java    118468 root  cwd    DIR  202,2     4096 318885 /home/jenkins
root@SHA1000140068:/# ps -ef|grep 118468
root      13455  12950  0 14:29 pts/1    00:00:00 grep --color=auto 118468
root     118468      1  3  2018 ?        18-08:53:59 java -jar jenkins.war --requestHeaderSize=32768 --prefix=/jenkins/main-master
```


## 参考
- https://linuxtools-rst.readthedocs.io/zh_CN/latest/tool/lsof.html



