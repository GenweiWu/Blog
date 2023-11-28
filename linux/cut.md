## cut

```
cut命令主要是接受三个定位方法：

第一，字节（bytes），用选项-b
第二，字符（characters），用选项-c
第三，域（fields），用选项-f
```


### `-d`指定分隔符

> eg1
```console
# cat 1.txt
version: "V111.0"

## 以冒号进行分割
# cat 1.txt |cut -d: -f1
version
# cat 1.txt |cut -d: -f2
 "V111.0"
# cat 1.txt |cut -d: -f3

# 
```


> eg2
```console
# cat 1.txt
version: "V111.0"

## 以双引号分割
# cat 1.txt |cut -d\" -f1
version:
# cat 1.txt |cut -d\" -f2
V111.0
# cat 1.txt |cut -d\" -f3

#
```


### `-f`指定获取的字段


> f1表示第一个字段

```console
# cat 2.txt
111:222:333:444

# cat 2.txt |cut -d \: -f1
111
# cat 2.txt |cut -d \: -f2
222
# cat 2.txt |cut -d \: -f3
333
# cat 2.txt |cut -d \: -f4
444 
```


> fN-M表示第N个字段到第M个字段

```console
# cat 2.txt
111:222:333:444

# cat 2.txt |cut -d \: -f2-3
222:333
# cat 2.txt |cut -d \: -f1-2
111:222
```

> fN-表示第N个字段到最后
>
> f-M表示开始到第M个字段

```console
# cat 2.txt
111:222:333:444

# cat 2.txt | cut -d \: -f1-
111:222:333:444
# cat 2.txt | cut -d \: -f2-
222:333:444
# cat 2.txt | cut -d \: -f-4
111:222:333:444

## 也支持多个
# cat 2.txt | cut -d \: -f-2,4
111:222:444
```

###  `--complement`补全选择的输出，即反选

```console
# cat 2.txt
111:222:333:444

## 测试反选2
# cat 2.txt|cut -d ':' -f2
222
# cat 2.txt|cut -d ':' -f1,3-4
111:333:444
# cat 2.txt|cut -d ':' --complement -f2
111:333:444

## 测试反选2-3
# cat 2.txt|cut -d ':' -f2-3
222:333
# cat 2.txt|cut -d ':' -f1,4
111:444
# cat 2.txt|cut -d ':' --complement -f2-3
111:444
```

### `--output-delimiter`指定输出分隔符

```console
# cat 2.txt
111:222:333:444

## 默认的输出分隔符就是输入分隔符
# cat 2.txt | cut -d \: -f1-
111:222:333:444

## 指定输出分隔符为|,需要转移\|
# cat 2.txt | cut -d \: -f1- --output-delimiter=|
> ^C
# cat 2.txt | cut -d \: -f1- --output-delimiter=\|
111|222|333|444

## 指定输出分隔符为$
# cat 2.txt | cut -d \: -f1- --output-delimiter=$
111$222$333$444

## 指定输出分隔符为空格，要加单引号
# cat 2.txt | cut -d \: -f1- --output-delimiter=
111222333444
# cat 2.txt | cut -d \: -f1- --output-delimiter='  '
111  222  333  444
```


### `-c` 提取指定位置的字符

```console
# cat 3.txt
123456789
abcdefgh
123 456 7
Ab
  cd
  
# cat 3.txt | cut -c 1
1
a
1
A

# cat 3.txt | cut -c 1-3
123
abc
123
Ab
  c
# cat 3.txt | cut -c 2-
23456789
bcdefgh
23 456 7
b
 cd
# cat 3.txt | cut -c -4
1234
abcd
123
Ab
  cd
```


### `-b`提取指定位置的字节

> 英文时和-c没啥区别

```console
# cat 3.txt
123456789
abcdefgh
123 456 7
Ab
  cd

# cat 3.txt | cut -b 1
1
a
1
A

# cat 3.txt | cut -b 1-3
123
abc
123
Ab
  c
# cat 3.txt | cut -b 2-
23456789
bcdefgh
23 456 7
b
 cd
#  cat 3.txt | cut -b -4
1234
abcd
123
Ab
  cd
```


> 中文时就有区别了

```console
# cat 4.txt
中国
长城长
哈
# cat 4.txt |cut -c 1
中
长
哈
# cat 4.txt |cut -b 1
⚌
⚌
⚌
```

### `--only-delimited`只显示包含分隔符的行

```console
# cat 5.txt
apple,orange,banana
grape
kiwi,lemon
# cat 5.txt | cut -d ',' -f1
apple
grape
kiwi

## 此时第2行不包含分隔符","所以没输出
# cat 5.txt | cut -d ',' -f1 --only-delimited
apple
kiwi
```

## MAN Page

```
cut --help
Usage: cut OPTION... [FILE]...
Print selected parts of lines from each FILE to standard output.

With no FILE, or when FILE is -, read standard input.

Mandatory arguments to long options are mandatory for short options too.
  -b, --bytes=LIST        select only these bytes
  -c, --characters=LIST   select only these characters
  -d, --delimiter=DELIM   use DELIM instead of TAB for field delimiter
  -f, --fields=LIST       select only these fields;  also print any line
                            that contains no delimiter character, unless
                            the -s option is specified
  -n                      with -b: don't split multibyte characters
      --complement        complement the set of selected bytes, characters
                            or fields
  -s, --only-delimited    do not print lines not containing delimiters
      --output-delimiter=STRING  use STRING as the output delimiter
                            the default is to use the input delimiter
  -z, --zero-terminated    line delimiter is NUL, not newline
      --help     display this help and exit
      --version  output version information and exit

Use one, and only one of -b, -c or -f.  Each LIST is made up of one
range, or many ranges separated by commas.  Selected input is written
in the same order that it is read, and is written exactly once.
Each range is one of:

  N     N'th byte, character or field, counted from 1
  N-    from N'th byte, character or field, to end of line
  N-M   from N'th to M'th (included) byte, character or field
  -M    from first to M'th (included) byte, character or field
```



## 样例 

### ✈️ 以空格分割的转义处理
> 2中方法,哪种有用用哪种
```console
command | cut -d' ' -f1
command | cut -d \ -f1
```
