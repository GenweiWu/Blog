## 转义字符

```bash
## prinf默认是不换行显示的
## \n 换行
[root@SZX1000538971 shellT]# printf "hello"
hello[root@SZX1000538971 shellT]# printf "hello\n"
hello
[root@SZX1000538971 shellT]#


## \b后退删除一个字符
[root@SZX1000538971 shellT]# printf "12345"
12345[root@SZX1000538971 shellT]# printf "12345\b"
1234[root@SZX1000538971 shellT]# printf "12345\b "
1234 [root@SZX1000538971 shellT]# printf "12345\b\b\b*"
12*[root@SZX1000538971 shellT]#


## \r回车，感觉是删除了回车前面的所有字符
[root@SZX1000538971 shellT]# printf "111\r666"
666[root@SZX1000538971 shellT]# printf "111222\r666"
666[root@SZX1000538971 shellT]#
## 实际上左移光标后，如果有新字符就覆盖了之前的内容
#### 实际效果是先展示 66122且光标在1上面，然后1s后显示66
[root@SZX1000538971 shellT]# printf "111222\r66" && sleep 1s
66[root@SZX1000538971 shellT]#


## \t水平制表符  \v垂直制表符
[root@SZX1000538971 shellT]# printf '111\t222'
111     222[root@SZX1000538971 shellT]# printf '111\v222'
111
   222[root@SZX1000538971 shellT]#
```

-r 回车，内容没打印出来



## 格式化

```bash
## %d打印数字
## %5d 打印时5位数显示
## %.5d 打印时5位数显示，不足时用0补齐
## -表示靠左展示，默认靠右展示
[root@SZX1000538971 shellT]# printf "%d\n"  66
66
[root@SZX1000538971 shellT]# printf "%5d\n"  66
   66
[root@SZX1000538971 ~]# printf "%-5d\n"  66
66   
[root@SZX1000538971 shellT]# printf "%.5d\n"  66
00066


## %s显示字符串
## %-15s 表示一个左对齐、宽度为 15 个字符字符串格式
## -居左，默认居右
[root@SZX1000538971 shellT]# printf "%sAAA" 111
111AAA[root@SZX1000538971 shellT]# printf "%-10sAAA" 111
111       AAA[root@SZX1000538971 shellT]# printf "%10sAAA" 111
       111AAA[root@SZX1000538971 shellT]#
```



## 命令格式
```
printf  format-string  [arguments...]
```

- https://wiki.jikexueyuan.com/project/shell-learning/printf-explain-in-detail.html

| printf的转义序列 |                                                              |
| ---------------- | ------------------------------------------------------------ |
| 序列             | 说明                                                         |
| `\a`               | 警告字符,通常为ASCII的BEL字符                                |
| `\b`               | 后退                                                         |
| `\c`               | 抑制(不显示)输出结果中任何结尾的换行字符;而且,任何留在参数里的字符,任何接下来的参数以及任何留在格式字符串中的字符,都被忽略 |
| `\f`               | 换页                                                         |
| `\n`               | 换行                                                         |
| `\r`               | 回车                                                         |
| `\t`               | 水平制表符                                                   |
| `\v`             | 垂直制表符                                                   |
| `\\`               | 一个字面上的反斜杠字符                                       |
| `\ddd`             | 表示1到3位数八进制的字符.尽在格式字符串中有效                |
| `\0ddd`            | 表示1到3位的八进制字符                                       |

| printf格式指示符 |                                         |
| ---------------- | --------------------------------------- |
| `%c`               | ASCII字符.显示相对应参数的第一个字符    |
| `%d,%i`            | 十进制整数                              |
| `%e`               | 浮点格式([-d].precisione [+-dd])        |
| `%E`               | 浮点格式([-d].precisionE [+-dd])        |
| `%g`               | %e或%f转换,看哪一个较短,则删除结尾的零  |
| `%G`               | %E或%f转换,看哪一个较短,则删除结尾的零  |
| `%s`               | 字符串                                  |
| `%u`               | 不带正负号的十进制值                    |
| `%x`               | 不带正负号的十六进制.使用a至f表示10至15 |
| `%%`               | 字面意义的%                             |
| `%X`               | 不带正负号的十六进制.使用A至F表示10至15 |

| 精度的含义        |                                                              |
| ----------------- | ------------------------------------------------------------ |
| 转换              | 精度含义                                                     |
| `%d,%i,%o,%u,%x,%X` | 要打印的最小位数.当值的位数少于此数字时,会在前面补零.默认精度为1 |
| `%e,%E`             | 要打印的最小位数.当值的位数少于此数字时,会在小数点后面补零,默认为精度为6.精度为0则表示不显示小数点小数点右边的位数 |
| `%f`                | 小数点右边的位数                                             |
| `%g,%G`            | 有效位数的最大数目                                           |
| `%s`                | 要打印字符的最大数目                                         |


## printf很好玩

- 输出进度条效果的简单shell脚本
> https://blog.csdn.net/ITzhangdaopin/article/details/82965875
```bash
#!/bin/bash
b=''
i=0
while [ $i -le 100 ]
do
 printf "[%-50s] %d%% \r" "$b" "$i";
 sleep 0.2
 ((i=i+2))
 b+='#'
done
echo
```

- 输出进度条2
```bash 
function show_process()
{
  current=$1
  all=$2

  aa=''
  for((i=1;i<=$1;i++));
  do
    aa=${aa}'#'
  done

  printf "[%-${all}s] $current/$all \r" $aa
}


show_process 4 10
sleep 0.1
show_process 5 10
sleep 0.2
show_process 8 10
sleep 0.1
show_process 10 10
```

- 格式化打印
```bash
# !/bin/bash

printf "%-10s %-8s %-4s\n" Name Sex Weight-kg
printf "%-10s %-8s %-4.2f\n" ZhangSan male 66.1234
printf "%-10s %-8s %-4.2f\n" LiSi male 48.6985
printf "%-10s %-8s %-4.2f\n" Wangwu female 47.5197
```

> 对比下未格式化和格式化的
```bash
# ./table_print.sh
## 未格式化的
Name Sex Weight-kg
ZhangSan male 66.1234
LiSi male 48.6985
Wangwu female 47.5197

# ./table_print.sh
## 格式化的
Name       Sex      Weight-kg
ZhangSan   male     66.12
LiSi       male     48.70
Wangwu     female   47.52

```



