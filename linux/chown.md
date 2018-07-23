
## chown 改变文件属主

## 1、改变文件拥有者和群组

> 同时指定拥有者和群组  
```
chown mail:mailGroup 111.txt
```

> 只指定拥有者，则默认使用对应的群组  
```
chown <user>: <file>
```

> 只指定群组，则只改变群组  
```
chown :<group> <file>
```

## 2、递归改变
> 递归改变文件夹以及文件夹中的文件的属主
```
chown -R username:group directory
```

## 3、root转普通用户，需要使用sudo
```
sudo chown username:group directory
```

## 参考
- [每天一个linux命令（30）: chown命令](http://www.cnblogs.com/peida/archive/2012/12/04/2800684.html)
- [change-folder-permissions-and-ownership](https://askubuntu.com/a/6727)
