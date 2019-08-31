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


## jdk版本
在使用jmap等命令时，最好保持jdk版本一致(不是jdk7和jdk8的区别,是jdk8U131和jdk8u144的区别)
```
Attaching to process ID 7, please wait...
Error attaching to process: sun.jvm.hotspot.runtime.VMVersionMismatchException: Supported versions are 25.131-b11. Target VM is 25.144-b01
sun.jvm.hotspot.debugger.DebuggerException: sun.jvm.hotspot.runtime.VMVersionMismatchException: Supported versions are 25.131-b11. Target VM is 25.144-b01
        at sun.jvm.hotspot.HotSpotAgent.setupVM(HotSpotAgent.java:435)
        at sun.jvm.hotspot.HotSpotAgent.go(HotSpotAgent.java:305)
        at sun.jvm.hotspot.HotSpotAgent.attach(HotSpotAgent.java:140)
        at sun.jvm.hotspot.tools.Tool.start(Tool.java:185)
        at sun.jvm.hotspot.tools.Tool.execute(Tool.java:118)
        at sun.jvm.hotspot.tools.PMap.main(PMap.java:72)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at sun.tools.jmap.JMap.runTool(JMap.java:201)
        at sun.tools.jmap.JMap.main(JMap.java:130)
Caused by: sun.jvm.hotspot.runtime.VMVersionMismatchException: Supported versions are 25.131-b11. Target VM is 25.144-b01
        at sun.jvm.hotspot.runtime.VM.checkVMVersion(VM.java:227)
        at sun.jvm.hotspot.runtime.VM.<init>(VM.java:294)
        at sun.jvm.hotspot.runtime.VM.initialize(VM.java:370)
        at sun.jvm.hotspot.HotSpotAgent.setupVM(HotSpotAgent.java:431)
        ... 11 more
```

```
jdk 25.131-b11 ==>  jdk8u131
jdk 25.144-b01 ==> jdk8u144
```
