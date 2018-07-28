## wc命令


## 基础
```
查看文件的行数、字数(word count)、字节数
wc file

查看文件的行数
wc -l file
查看文件的字数
wc -w file
查看文件的字节数
wc -c file
```

## 例子
> 文件内容
```
$ cat 111.txt
aaa
bbbccc gggggg
ddddd.eeee.fffff
end

```

> 查看文件的行数、字数(word count)、字节数
```
$ wc 111.txt
 4  5 43 111.txt

```
> 查看文件的行数
```
$ wc -l 111.txt
4 111.txt

```
> 查看文件的字数(word count)
```
$ wc -w 111.txt
5 111.txt

```
> 查看文件的字节数
```
$ wc -c 111.txt
43 111.txt

```

> 统计当前目录下文件数
```
$ ls
111.txt  222.txt  temp.txt  tmp.txt

$ ls |wc -l
4

```

## 参考
- [每天一个linux命令（40）：wc命令 - peida - 博客园](https://www.cnblogs.com/peida/archive/2012/12/18/2822758.html)
