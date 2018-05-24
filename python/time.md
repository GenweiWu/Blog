
## 格式化输出当前时间
```py
import time

# 当前时间
print(time.localtime())

# 当前时间字符串
print(time.strftime("%Y-%m-%d %H:%M:%S"))
print("time is %s" % time.strftime("%Y-%m-%d %H:%M:%S"))
```

>time.struct_time(tm_year=2018, tm_mon=5, tm_mday=22, tm_hour=9, tm_min=21, tm_sec=38, tm_wday=1, tm_yday=142, tm_isdst=0)
2018-05-22 09:21:38
time is 2018-05-22 09:21:38


## long转换成时间字符串
```py
import datetime
import time

# long转换到时间
print(datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S'))
print(datetime.datetime.fromtimestamp(1519695947430 / 1000).strftime('%Y-%m-%d %H:%M:%S'))
print(datetime.datetime.fromtimestamp(int("1519695947430") / 1000).strftime('%Y-%m-%d %H:%M:%S'))
```

## 字符串转换成时间
```py
import datetime

d1 = datetime.datetime.strptime("2018-07-08", "%Y-%m-%d")
d2 = datetime.datetime.strptime("2018-07-08 11:12:13", "%Y-%m-%d %H:%M:%S")
# d1 = {datetime} 2018-07-08 00:00:00
# d2 = {datetime} 2018-07-08 11:12:13
```

## 时间比较
```py
import datetime

d1 = datetime.datetime.strptime("2018-07-08", "%Y-%m-%d")
d2 = datetime.datetime.strptime("2018-07-08 11:12:13", "%Y-%m-%d %H:%M:%S")
# d1 = {datetime} 2018-07-08 00:00:00
# d2 = {datetime} 2018-07-08 11:12:13
print(d1 > d2)
# False
```

