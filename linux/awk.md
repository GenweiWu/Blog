awk是一个行数据处理命令
==

```bash
# 获取IP地址
ifconfig eth0 | grep "inet addr" | awk -F ":" '{print $2}' | awk '{print $1}'
```




