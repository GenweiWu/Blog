uniq
==

## 发现文件中的重复行

### （1）uniq发现重复行，要求文件中重复行是靠在一起的 
> a1.txt
```txt
1
2
1

```

> a2.txt
```txt
1
1
2

```

> 测试结果:a1中重复行不在一起所以没有去重，a2去重了
```
$ uniq a1.txt
1
2
1

$ uniq a2.txt
1
2
```

### （2）针对(1)的情况，一般结合sort一起使用

```
$ sort a1.txt |uniq
1
2

$ sort a2.txt |uniq
1
2
```

### （3）uniq对于文件末尾不换行的不兼容

> b1.txt文件内容
```
1
2
2 
  <== 注意这里有个空行
```

> b2.txt文件内容
```
1
2
2  <== 后面没有空行
```

> 对比下结果发现，结尾没有空行的识别是有问题的
```shell
$ uniq 1.txt
1
2

$ uniq 2.txt
1
2
2
```

### （4）对于(3)的原因分析

参考:https://unix.stackexchange.com/a/235151
> 这个问题在window上才有,在linux上可以正常去重

- windows
```
$ cat -A b1.txt
1^M$
2^M$
2^M$

$ cat -A b2.txt
1^M$
2^M$
2

$ sort b1.txt |uniq|cat -A
1^M$
2^M$

$ sort b2.txt |uniq|cat -A
1^M$
2$
2^M$
```

- linux
```
# cat -A b1.txt 
1$
2$
2$
$

# cat -A b2.txt 
1$
2$
2$

# sort b1.txt |uniq|cat -A
$
1$
2$

# sort b2.txt |uniq|cat -A
1$
2$
```




