
## 流编辑器
  sed是流编辑器，它是按行处理的，每次读取一行放到缓冲区，然后用sed命令进行处理，然后把结果输出到屏幕，处理完成这行后继续处理下一行。
  sed命令不会改变文件本身的内容，除非我们自己重定向处理。

## 命令的格式
```
sed [options] 'script' file(s)
sed [options] -f 'scriptFiles' file(s)
```

## 替换命令s

#### 基本
```
替换每行的第一个匹配
sed '/s/oldstring/newstring/' file
使用g替换每行的所有匹配
sed '/s/oldstring/newstring/g' file
```

> 例1：替换第一个`aaa`到`---`
```
$ cat 3.txt
abc111def222ghi111z
$ sed 's/111/---/' 3.txt
abc---def222ghi111z
```

> 例2：替换所有`aaa`到`---`
```
$ cat 3.txt
abc111def222ghi111z
$ sed 's/111/---/g' 3.txt
abc---def222ghi---z
```

## 删除命令d

> 准备:原始文件内容
```
$ cat testD.txt
111
222

testAAA
ccc
AAAtestAAA
ddd
AAAtest

999
end
```

> 例1：删除空白行
```
$ sed '/^$/d' testD.txt
111
222
testAAA
ccc
AAAtestAAA
ddd
AAAtest
999
end
```

> 例2：删除第一行
```
$ sed '1d' testD.txt
222

testAAA
ccc
AAAtestAAA
ddd
AAAtest

999
end
```

> 例3：删除最后一行(最后变成空行了)
```
$ sed '$d' testD.txt
111
222

testAAA
ccc
AAAtestAAA
ddd
AAAtest

999

```

> 例4：删除2到最后一行
```
$ sed '2,$d' testD.txt
111

```

> 例5：删除包含test的行
```
$ sed '/test/d' testD.txt
111
222

ccc
ddd

999
end
```

> 例6：删除以test开头的行
```
$ sed '/^test/d' testD.txt
111
222

ccc
AAAtestAAA
ddd
AAAtest

999
end
```

> 例7：删除以test结尾的行
```
$ sed '/test$/d' testD.txt
111
222

testAAA
ccc
AAAtestAAA
ddd

999
end
```



## 参考
- [sed命令_Linux sed 命令用法详解：功能强大的流式文本编辑器](http://man.linuxde.net/sed)
- [sed 简明教程 | | 酷 壳 - CoolShell](https://coolshell.cn/articles/9104.html)
- [Linux shell脚本之 Sed 简介 正则表达式 - CSDN博客](https://blog.csdn.net/doiido/article/details/43987197)
