sftp命令
==

#### 1. 连接远程主机

```
sftp -P 2018 root@127.0.0.3
```
- [-P port]  指定端口
- sftp [user@]host[:dir[/]]  指定登录用户名+IP
- 输入后会要求输入密码

#### 2.获取帮助
```
$ sftp
usage: sftp [-1246aCfpqrv] [-B buffer_size] [-b batchfile] [-c cipher]
          [-D sftp_server_path] [-F ssh_config] [-i identity_file] [-l limit]
          [-o ssh_option] [-P port] [-R num_requests] [-S program]
          [-s subsystem | sftp_server] host
       sftp [user@]host[:file ...]
       sftp [user@]host[:dir[/]]
       sftp -b batchfile [user@]host

```
