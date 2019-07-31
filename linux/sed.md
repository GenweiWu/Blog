
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
sed 's/oldstring/newstring/' file
使用g替换每行的所有匹配
sed 's/oldstring/newstring/g' file
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

## couldn't open temporary file ./sedAbp74q: Permission denied

### 个人理解：
- sed的过程是，先读取当前文件，然后在当前目录新建一个temp文件，将替换的内容写到temp文件,删除旧文件
- 文件属主都会发生改变了
- 即要求的权限是: 读取旧文件 + 创建新文件

> 如果sed的文件不可读,报错:sed: can't read clearContainer.sh: Permission denied  
```
[test@SZX1000538971 dave]$ ll -a
total 24
drwxr-xr-x. 5 root root 4096 Jul 29 15:36 .
drwxr-xr-x. 9 root root 4096 Jul 31 15:41 ..
-rwxr--r--. 1 root root   30 Jul 24 15:04 clearContainer.sh
...
[test@SZX1000538971 dave]$ sed -i 's/aaa/bbb/' clearContainer.sh 
sed: can't read clearContainer.sh: Permission denied
```

> 如果目录不可写，即使文件是可读可写，还是报错sed: couldn't open temporary file ./sedeXtuss: Permission denied
```
[test@SZX1000538971 dave]$ ll -a
total 24
drwxr-xr-x. 5 root root 4096 Jul 29 15:36 .
drwxr-xr-x. 9 root root 4096 Jul 31 15:41 ..
-rwxrw-rw-. 1 root root   30 Jul 24 15:04 clearContainer.sh
...
[test@SZX1000538971 dave]$  sed -i 's/aaa/bbb/' clearContainer.sh 
sed: couldn't open temporary file ./sedeXtuss: Permission denied
```

> 如果目录可读写，且文件可读，则才能成功
```
[test@SZX1000538971 dave]$ ll -a
total 24
drwxrwxrwx. 5 root root 4096 Jul 29 15:36 .
drwxr-xr-x. 9 root root 4096 Jul 31 15:41 ..
-rwxr--r--. 1 root root   30 Jul 24 15:04 clearContainer.sh
...
[test@SZX1000538971 dave]$ 
[test@SZX1000538971 dave]$  sed -i 's/aaa/bbb/' clearContainer.sh 
[test@SZX1000538971 dave]$ ll -a
total 24
drwxrwxrwx. 5 root root 4096 Jul 31 15:56 .
drwxr-xr-x. 9 root root 4096 Jul 31 15:41 ..
-rwxr--r--. 1 test test   30 Jul 31 15:56 clearContainer.sh
...
```



## 参考
- [sed命令_Linux sed 命令用法详解：功能强大的流式文本编辑器](http://man.linuxde.net/sed)
- [sed 简明教程 | | 酷 壳 - CoolShell](https://coolshell.cn/articles/9104.html)
- [Linux shell脚本之 Sed 简介 正则表达式 - CSDN博客](https://blog.csdn.net/doiido/article/details/43987197)
