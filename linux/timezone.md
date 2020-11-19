

## 查看当前时区
```bash
date
ls -l /etc/localtime
```

## 查看可选时区
```bash
tzselect
```

## 修改时区

### 修改整个系统的时区
```
sudo rm -f /etc/localtime
sudo ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
```



## 参考
- https://www.sysgeek.cn/change-timezone-linux/
