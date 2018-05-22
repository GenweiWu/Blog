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


## 三、打印日志
```
47 11 * * * /usr/bin/python3 /home/aaa/clean.py >> /home/aaa/run.log 2>&1
```
> https://unix.stackexchange.com/a/52332


## 参考
http://linuxtools-rst.readthedocs.io/zh_CN/latest/tool/crontab.html  
http://www.cnblogs.com/peida/archive/2013/01/08/2850483.html  
