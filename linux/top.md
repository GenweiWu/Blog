
## 说明
>https://www.cnblogs.com/peida/archive/2012/12/24/2831353.html
```
[root@TG1704 log]# top

top - 14:06:23 up 70 days, 16:44,  2 users,  load average: 1.25, 1.32, 1.35

Tasks: 206 total,   1 running, 205 sleeping,   0 stopped,   0 zombie

Cpu(s):  5.9%us,  3.4%sy,  0.0%ni, 90.4%id,  0.0%wa,  0.0%hi,  0.2%si,  0.0%st

Mem:  32949016k total, 14411180k used, 18537836k free,   169884k buffers

Swap: 32764556k total,        0k used, 32764556k free,  3612636k cached


```

- 纳入内核管理的内存不见得都在使用中，还包括过去使用过的现在可以被重复利用的内存，内核并不把这些可被重新使用的内存交还到free中去，因此在linux上free内存会越来越少，但不用为此担心

## 查看cpu核数
```
运行top命令后，键入数字1
```

## 按进程的CPU使用率排序

```
运行top命令后，键入大写P
```

## 按进程的内存使用率排序
```
运行top命令后，键入大写M
```


## 参考
- https://blog.csdn.net/daiyudong2020/article/details/52760846
