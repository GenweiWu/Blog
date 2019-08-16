

## 一、locale系统

### 1.1 组成
```cmd
[root@SZX1000538967 ~]# locale
LANG=en_US.UTF-8
LC_CTYPE="en_US.UTF-8"
LC_NUMERIC="en_US.UTF-8"
LC_TIME="en_US.UTF-8"
LC_COLLATE="en_US.UTF-8"
LC_MONETARY="en_US.UTF-8"
LC_MESSAGES="en_US.UTF-8"
LC_PAPER="en_US.UTF-8"
LC_NAME="en_US.UTF-8"
LC_ADDRESS="en_US.UTF-8"
LC_TELEPHONE="en_US.UTF-8"
LC_MEASUREMENT="en_US.UTF-8"
LC_IDENTIFICATION="en_US.UTF-8"
LC_ALL=

```

主要分为`LANG`,12个`LC_*`和`LC_ALL`
- LANG默认设定
- LC_*分别设定12个方面的locale
- LC_ALL全局设定

### 1.2 优先级：
LC_ALL > LC_* > LANG

> locale命令看到的是起作用的效果，并不表示`LC_*`自己设置了值
```
[root@SZX1000538967 ~]# echo $LANG
en_US.UTF-8
[root@SZX1000538967 ~]# echo $LC_TIME

[root@SZX1000538967 ~]# echo $LC_ALL

[root@SZX1000538967 ~]# locale
LANG=en_US.UTF-8
LC_CTYPE="en_US.UTF-8"
LC_NUMERIC="en_US.UTF-8"
LC_TIME="en_US.UTF-8"
LC_COLLATE="en_US.UTF-8"
LC_MONETARY="en_US.UTF-8"
LC_MESSAGES="en_US.UTF-8"
LC_PAPER="en_US.UTF-8"
LC_NAME="en_US.UTF-8"
LC_ADDRESS="en_US.UTF-8"
LC_TELEPHONE="en_US.UTF-8"
LC_MEASUREMENT="en_US.UTF-8"
LC_IDENTIFICATION="en_US.UTF-8"
LC_ALL=

```

> 当然这样测试，更能看出来此时`LC_*`是使用了`LANG`的值
```
[root@SZX1000538967 ~]# export LANG=de_DE.utf8;locale
LANG=de_DE.utf8
LC_CTYPE="de_DE.utf8"
LC_NUMERIC="de_DE.utf8"
LC_TIME="de_DE.utf8"
LC_COLLATE="de_DE.utf8"
LC_MONETARY="de_DE.utf8"
LC_MESSAGES="de_DE.utf8"
LC_PAPER="de_DE.utf8"
LC_NAME="de_DE.utf8"
LC_ADDRESS="de_DE.utf8"
LC_TELEPHONE="de_DE.utf8"
LC_MEASUREMENT="de_DE.utf8"
LC_IDENTIFICATION="de_DE.utf8"
LC_ALL=

```

## 二、locale组成

`[语言[_地域][.字符集] [@修正值]`

- zh_CN.GB2312 中文 中国 GB2312编码
- zh_TW.BIG5 中文 台湾 BIG5编码
- de_DE.UTF-8@euro 德语 德国 UTF-8编码 按照欧洲习惯修正

```bash
[root@SZX1000538967 222]# LC_TIME=en_US.utf8  date
Fri Aug 16 14:57:14 CST 2019
[root@SZX1000538967 222]# LC_TIME=zh_CN.utf8  date
2019年 08月 16日 星期五 14:57:17 CST
[root@SZX1000538967 222]# LC_TIME=zh_TW.utf8  date
五  8月 16 14:57:21 CST 2019
[root@SZX1000538967 222]# LC_MESSAGES=en_US.utf8 ll
total 68
-rw-------. 1 root root 67989 Aug 13 15:32 gitlab.rb
[root@SZX1000538967 222]# LC_MESSAGES=zh_CN.utf8 ll
总用量 68
-rw-------. 1 root root 67989 Aug 13 15:32 gitlab.rb

```


## 参考
- https://www.cnblogs.com/xlmeng1988/archive/2013/01/16/locale.html
- https://www.cnblogs.com/LCcnblogs/p/6208110.html
