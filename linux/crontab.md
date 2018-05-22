crontab定时器
==
## 一、crontab命令

- `crontab -l`列出
```
$ crontab -l
0,15,30,45 18-06 * * * /bin/echo `date` > dev/tty1
```

- `rontab -e`编辑crontab文件

## 二、定时规则


## 参考
http://linuxtools-rst.readthedocs.io/zh_CN/latest/tool/crontab.html  
http://www.cnblogs.com/peida/archive/2013/01/08/2850483.html  
