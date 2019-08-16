

## 一、locale的组成

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


