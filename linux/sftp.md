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

也可以在进入sftp后，这样查看
```
sftp> ?
Available commands:
bye                                Quit sftp
cd path                            Change remote directory to 'path'
chgrp grp path                     Change group of file 'path' to 'grp'
chmod mode path                    Change permissions of file 'path' to 'mode'
chown own path                     Change owner of file 'path' to 'own'
df [-hi] [path]                    Display statistics for current directory or
                                   filesystem containing 'path'
exit                               Quit sftp
get [-afPpRr] remote [local]       Download file
reget [-fPpRr] remote [local]      Resume download file
reput [-fPpRr] [local] remote      Resume upload file
help                               Display this help text
lcd path                           Change local directory to 'path'
lls [ls-options [path]]            Display local directory listing
lmkdir path                        Create local directory
ln [-s] oldpath newpath            Link remote file (-s for symlink)
lpwd                               Print local working directory
ls [-1afhlnrSt] [path]             Display remote directory listing
lumask umask                       Set local umask to 'umask'
mkdir path                         Create remote directory
progress                           Toggle display of progress meter
put [-afPpRr] local [remote]       Upload file
pwd                                Display remote working directory
quit                               Quit sftp
rename oldpath newpath             Rename remote file
rm path                            Delete remote file
rmdir path                         Remove remote directory
symlink oldpath newpath            Symlink remote file
version                            Show SFTP version
!command                           Execute 'command' in local shell
!                                  Escape to local shell
?                                  Synonym for help

```

#### 3.一些ls命令
```
sftp> ls *02-01*runtime.gz
sftp> cd /home/test
sftp> find *.log
Invalid command.
```
- 可以用ls、cd命令，但是find貌似不可以用

#### 4.下载文件，可以批量下载
```
sftp> get *02-01*runtime.gz
```

#### 5.上传文件
> -r用来上传文件夹 -P表示要保存文件权限以及修改时间
```
sftp>put -r -P gitlab
```
