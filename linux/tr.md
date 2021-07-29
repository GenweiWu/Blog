> https://wangchujiang.com/linux-command/c/tr.html


## MAN
<details>
<summary>
tr --help
</summary>

```
Usage: tr [OPTION]... SET1 [SET2]
Translate, squeeze, and/or delete characters from standard input,
writing to standard output.

  -c, -C, --complement    use the complement of SET1
  -d, --delete            delete characters in SET1, do not translate
  -s, --squeeze-repeats   replace each input sequence of a repeated character
                            that is listed in SET1 with a single occurrence
                            of that character
  -t, --truncate-set1     first truncate SET1 to length of SET2
      --help     display this help and exit
      --version  output version information and exit

SETs are specified as strings of characters.  Most represent themselves.
Interpreted sequences are:

  \NNN            character with octal value NNN (1 to 3 octal digits)
  \\              backslash
  \a              audible BEL
  \b              backspace
  \f              form feed
  \n              new line
  \r              return
  \t              horizontal tab
  \v              vertical tab
  CHAR1-CHAR2     all characters from CHAR1 to CHAR2 in ascending order
  [CHAR*]         in SET2, copies of CHAR until length of SET1
  [CHAR*REPEAT]   REPEAT copies of CHAR, REPEAT octal if starting with 0
  [:alnum:]       all letters and digits
  [:alpha:]       all letters
  [:blank:]       all horizontal whitespace
  [:cntrl:]       all control characters
  [:digit:]       all digits
  [:graph:]       all printable characters, not including space
  [:lower:]       all lower case letters
  [:print:]       all printable characters, including space
  [:punct:]       all punctuation characters
  [:space:]       all horizontal or vertical whitespace
  [:upper:]       all upper case letters
  [:xdigit:]      all hexadecimal digits
  [=CHAR=]        all characters which are equivalent to CHAR

```

</details>

## 用法

### 0. 替换
`tr 字符集1  字符集2`
`tr 'a' 'b'`

```bash
## 替换空格为换行
[root@SZX1000538971 ~]# echo '1 2 3 4' | tr ' ' '\n'
1
2
3
4

## 替换空格为分号
[root@SZX1000538971 ~]# echo '1 2 3 4' | tr ' ' ';'
1;2;3;4

## 替换数字为*
[root@SZX1000538971 ~]# echo '1234abc'|tr 1-9 '*'
****abc
```


### 1. tr -d  SET1: 删除
`tr -d或-delete: 删除所有属于第一字符集的字符`

```bash
## 替换所有3
[root@SZX1000538971 test]# echo '1 2 3 3 abc' |tr -d '3'
1 2   abc
## 替换1-9的数字
[root@SZX1000538971 test]# echo '1 2 3 3 abc' |tr -d '1-9'
    abc
```

### 2. tr -s  SET1 SET2: 替换重复字符
`tr -s或--squeeze-repeats：将1或多个重复的字符，替换为其他1个字符`

```bash 
[root@SZX1000538971 test]# echo '1  2      4    5' | tr ' ' '-'
1--2------4----5
[root@SZX1000538971 test]# echo '1  2      4    5' | tr -s ' ' '-'
1-2-4-5
```

```bash 
[root@SZX1000538971 test]# echo '123  4545   5657  '|tr '0-9' '-'
---  ----   ----
[root@SZX1000538971 test]# echo '123  4545   5657  '|tr -s '0-9' '-'
-  -   -
```



### 3. tr -c :对SET1取反集，要搭配使用

```bash
## 删除所有数字
[root@SZX1000538971 test]# echo '123sdfdsf#@*&(%$' |tr -d '0-9'
sdfdsf#@*&(%$
## 删除所有非数字 / 删除所有非数字+非换行
[root@SZX1000538971 test]# echo '123sdfdsf#@*&(%$' |tr -d -c '0-9'
123[root@SZX1000538971 test]# echo '123sdfdsf#@*&(%$' |tr -d -c '0-9\n'
123
```



## `tr` vs `sed`

> sed是面向字符串的，tr是面向字符的

```bash
## 对于tr,g->1,o->3,d->4，o由于重复映射所有2失效3生效；而且boy中的o同样被替换
[root@SZX1000538971 test]# echo 'I am a good boy' | tr 'good' '1234'
I am a 1334 b3y
## 对于sed，是识别字符串替换的
[root@SZX1000538971 test]# echo 'I am a good boy' | sed 's/good/1234/'
I am a 1234 boy

```



## 例

```bash
## 替换中括号到大括号
[root@SZX1000538971 test]# echo  '[[1,2],[3,4]]' |tr '[]' '{}'
{{1,2},{3,4}}
```

```bash
## 删除空行
[root@SZX1000538971 test]# echo -e "1\n\n\n2"
1


2
[root@SZX1000538971 test]# echo -e "1\n\n\n2"|tr -s '\n'
1
2
[root@SZX1000538971 test]# echo -e "1\n\n\n2"|tr -s '\n' ''
tr: when not truncating set1, string2 must be non-empty
```

```bash
## 删除重复字符
[root@SZX1000538971 test]# echo "Hellooo    Javaaa"
Hellooo    Javaaa
[root@SZX1000538971 test]# echo "Hellooo    Javaaa"|tr -s 'a-zA-Z'
Helo    Java
[root@SZX1000538971 test]# echo "Hellooo    Javaaa"|tr -s 'oa'
Hello    Java
[root@SZX1000538971 test]# echo "Hellooo    Javaaa"|tr -s ' oa'
Hello Java
```

```bash
## 转大写
[root@SZX1000538971 test]# echo 'Hello World'|tr 'a-z' 'A-Z'
HELLO WORLD
## 转小写
[root@SZX1000538971 test]# echo 'Hello World'|tr 'A-Z' 'a-z'
hello world
## 大小写互换
[root@SZX1000538971 test]# echo 'Hello World'|tr 'A-Za-z' 'a-zA-Z'
hELLO wORLD

## --------------------------------------------------------------------------------------------
## shell编程中可用于忽略大小写的字符串判断场景
[root@SZX1000538971 test]# cat 3.sh
#! /bin/bash

#set -x
input=$1
input=$(echo $1|tr 'a-z' 'A-Z')

if [ "$input" = 'OK' ];then
  echo 'ok'
else
  echo 'not ok'
fi
[root@SZX1000538971 test]# bash 3.sh ok
ok

```

```bash
## 删除数字或字母
[root@SZX1000538971 test]# echo 'hello 12323 sdfdsf' |tr -d '0-9'
hello  sdfdsf
[root@SZX1000538971 test]# echo 'hello 12323 sdfdsf' |tr -d 'a-zA-Z'
 12323

## 可在shell编程中判断是否全是字母和数字
```

```bash
## 合并多个空格为1个空格
[root@SZX1000538971 test]# echo '1   2   34' |tr -s ' '
1 2 34
## 合并多个空格为1个-
[root@SZX1000538971 test]# echo '1   2   34' |tr -s ' ' '-'
1-2-34
```

```bash
## 只保留数字/删除所有非数字
[root@SZX1000538971 test]# echo "2018abcdefdf06zzz01" | tr -d -c '0-9'
20180601
```





