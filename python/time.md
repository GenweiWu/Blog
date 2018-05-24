
## 格式化输出当前时间
```py
import time

print(time.localtime())

print(time.strftime("%Y-%m-%d %H:%M:%S"))
print("time is %s" % time.strftime("%Y-%m-%d %H:%M:%S"))
```

>time.struct_time(tm_year=2018, tm_mon=5, tm_mday=22, tm_hour=9, tm_min=21, tm_sec=38, tm_wday=1, tm_yday=142, tm_isdst=0)
2018-05-22 09:21:38
time is 2018-05-22 09:21:38


## 讲ms转换成时间字符串
