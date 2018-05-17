linux环境性能定位工具 greys
==
## 一、官网
https://github.com/oldmanpushcart/greys-anatomy/wiki/greys-pdf

## 二、问题记录

#### :anger: 环境设置：配置java_home
##### > 问题
执行 `./greys`后提示 `illegal ENV, please set $JAVA_HOME to JDK6+`

##### > 解决
修改 `greys.sh` 添加java_home路径配置
```
JAVA_HOME=/root/jdk/jdk1.8.0_131

# define greys's home
GREYS_HOME=${HOME}/.greys
```
