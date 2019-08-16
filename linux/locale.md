

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

## 二、locale组成

`[语言[_地域][.字符集] [@修正值]`

- zh_CN.GB2312 中文 中国 GB2312编码
- zh_TW.BIG5 中文 台湾 BIG5编码
- de_DE.UTF-8@euro 德语 德国 UTF-8编码 按照欧洲习惯修正

```bash
[root@SZX1000538967 ~]# export LC_TIME=en_US.utf8; date
Fri Aug 16 14:38:45 CST 2019
[root@SZX1000538967 ~]# export LC_TIME=zh_CN.utf8; date
2019年 08月 16日 星期五 14:38:50 CST
[root@SZX1000538967 ~]# export LC_TIME=zh_TW.utf8; date
五  8月 16 14:38:54 CST 2019
```
