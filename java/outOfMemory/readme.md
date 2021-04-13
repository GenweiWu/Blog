## 设置堆大小
```
-Xms1g -Xmx1g
```

## gc日志配置
```
-XX:+PrintGC 输出GC日志
-XX:+PrintGCDetails 输出GC的详细日志
-XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）

-Xloggc:../logs/gc.log 日志文件的输出路径
```

```console
-Xms3g -Xmx3g -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=oom
```

## gc日志分析
```
[GC (System.gc()) [PSYoungGen: 3686K->664K(38400K)] 3686K->672K(125952K), 0.0016607 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[Full GC (System.gc()) [PSYoungGen: 664K->0K(38400K)] [ParOldGen: 8K->537K(87552K)] 672K->537K(125952K), [Metaspace: 2754K->2754K(1056768K)], 0.0059024 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
Heap
 PSYoungGen      total 38400K, used 333K [0x00000000d5c00000, 0x00000000d8680000, 0x0000000100000000)
  eden space 33280K, 1% used [0x00000000d5c00000,0x00000000d5c534a8,0x00000000d7c80000)
  from space 5120K, 0% used [0x00000000d7c80000,0x00000000d7c80000,0x00000000d8180000)
  to   space 5120K, 0% used [0x00000000d8180000,0x00000000d8180000,0x00000000d8680000)
 ParOldGen       total 87552K, used 537K [0x0000000081400000, 0x0000000086980000, 0x00000000d5c00000)
  object space 87552K, 0% used [0x0000000081400000,0x00000000814864a0,0x0000000086980000)
 Metaspace       used 2761K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K
```



1. 日志开头的`[GC`和`[Full GC]`表示此次gc的类型，且人为调用`system.gc`都显示`[GC (System.gc())`
2.  接下来的`PSYoungGen`和`ParOldGen`表示发生gc的区域，对应新生代和老年代
3.  `3686K->664K(38400K)`表示  gc前占用的内存->gc后占用的内存(当前区域总的内存容量)
4.  `3686K->672K(125952K), 0.0016607 secs`表达整个堆的  gc前占用内存->gc后占用内存，gc花费的时间，单位是秒
5.  `Times: user=0.00 sys=0.00, real=0.00 secs` gc所用的时间 ，user表示用户态消耗的CPU时间，sys表示内核态消耗的CPU时间，real表示消耗的时钟时间



另参考，另一段gc日志
> gc时间都好几秒了，要优化了
```
13388.482: [GC (Allocation Failure) [PSYoungGen: 1305579K->92139K(1059840K)] 2223678K->1107624K(3856384K), 20.1559754 secs] [Times: user=117.64 sys=6.11, real=20.15 secs]
13492.499: [GC (Allocation Failure) [PSYoungGen: 1059819K->143927K(1112064K)] 2075304K->1334784K(3908608K), 16.4424211 secs] [Times: user=79.08 sys=5.90, real=16.45 secs]
13521.945: [GC (Allocation Failure) [PSYoungGen: 982287K->145201K(1150976K)] 2173144K->1336267K(3947520K), 27.0758402 secs] [Times: user=167.14 sys=8.89, real=27.08 secs]
13563.313: [GC (Allocation Failure) [PSYoungGen: 847875K->217618K(1141760K)] 2038941K->1671028K(3938304K), 23.5122855 secs] [Times: user=107.77 sys=7.45, real=23.51 secs]
13635.968: [GC (Allocation Failure) [PSYoungGen: 1075409K->213008K(1062912K)] 2528819K->2243439K(3859456K), 53.0776733 secs] [Times: user=280.23 sys=17.15, real=53.08 secs]
13751.232: [GC (Allocation Failure) [PSYoungGen: 992840K->294770K(1101824K)] 3023272K->2325250K(3898368K), 32.2246300 secs] [Times: user=173.02 sys=10.45, real=32.22 secs]
13791.999: [GC (Allocation Failure) [PSYoungGen: 1101682K->219355K(979968K)] 3132162K->2648469K(3776512K), 71.8469352 secs] [Times: user=308.01 sys=30.45, real=71.85 secs]
13863.849: [Full GC (Ergonomics) [PSYoungGen: 219355K->0K(979968K)] [ParOldGen: 2429114K->915152K(2796544K)] 2648469K->915152K(3776512K), [Metaspace: 114437K->114421K(1157120K)], 92.3828415 secs] [Times: user=18.34 sys=21.35, real=92.39 secs]
14006.014: [GC (Allocation Failure) [PSYoungGen: 623923K->292722K(976896K)] 1539075K->1420482K(3773440K), 26.2527100 secs] [Times: user=131.06 sys=9.31, real=26.25 secs]
14082.440: [GC (Allocation Failure) [PSYoungGen: 976754K->293204K(949248K)] 2104514K->1858229K(3745792K), 73.7175793 secs] [Times: user=408.50 sys=27.41, real=73.72 secs]
14282.434: [GC (Allocation Failure) [PSYoungGen: 850260K->1424K(558592K)] 2415285K->1856453K(3355136K), 28.7319265 secs] [Times: user=176.94 sys=10.44, real=28.73 secs]
14373.718: [GC (Allocation Failure) [PSYoungGen: 558480K->3776K(976896K)] 2413509K->1859021K(3773440K), 0.5588496 secs] [Times: user=0.20 sys=0.05, real=0.56 secs]
14420.966: [GC (Allocation Failure) [PSYoungGen: 557248K->1141K(974336K)] 2412493K->1858131K(3770880K), 0.1030844 secs] [Times: user=0.10 sys=0.02, real=0.11 secs]
14472.454: [GC (Allocation Failure) [PSYoungGen: 554613K->1201K(990720K)] 2411603K->1858351K(3787264K), 0.0421240 secs] [Times: user=0.05 sys=0.01, real=0.04 secs]
15003.280: [GC (Allocation Failure) [PSYoungGen: 576177K->3168K(982016K)] 2433327K->1860544K(3778560K), 0.3547834 secs] [Times: user=0.15 sys=0.01, real=0.36 secs]
```


