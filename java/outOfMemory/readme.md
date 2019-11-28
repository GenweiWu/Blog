
```
-Xms1g -Xmx1g
```

```
XX:+PrintGC 输出GC日志
-XX:+PrintGCDetails 输出GC的详细日志
-XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）

-Xloggc:../logs/gc.log 日志文件的输出路径
```

```console
-Xms3g -Xmx3g -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=oom
```
