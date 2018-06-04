内存溢出
==

## dump导出及分析

### 1、导出JVM 中内存信息

导出整个JVM 中内存信息
jmap -dump:file=文件名.dump [pid]

https://blog.csdn.net/hemin1003/article/details/71425209

### 2、用jprofile分析dump文件
JProfiler有个小技巧，dump后缀要改成.hprof才能被JProfiler打开。

https://blog.csdn.net/AlbertFly/article/details/78686408
