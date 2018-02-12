Linux Shell字符串操作
===

### 1. 判断读取字符串值
`
TODO
`

### 2. 字符串长度、截取、替换

|命令|说明|备注|
|---|---|---|
|${#str}|获取字符串str的长度| |
| | | |
|${str:position}|从字符串str的position位置开始截取子串|<li>从0开始;</li> <li>包括position本身</li>|
|$(str:position:length)|从字符串str的position位置开始截取长度为length子串|<li>长度超过字符串长度，则展示所有剩余字符</li>|
| | | |
|${str#subString}|从字符串str的左边开始匹配,删除最短匹配||
|${str##subString}|从字符串str的左边开始匹配,删除最长匹配||
|${str%subString}|从字符串str的右边开始匹配,删除最短匹配||
|${str%%subString}|从字符串str的右边开始匹配,删除最长匹配|从右边开始匹配,但是还是从左向右看|


#### 2.1 字符串长度
```
$ var=12345.txt

$ echo ${var}
12345.txt

$ echo ${#var}
9
```

#### 2.2 字符串截取
```
$ var=1234567890

$ echo ${var:0}
1234567890

$ echo ${var:2}
34567890

$ echo ${var:2:3}
345

$ echo ${var:2:20}
34567890
```

```
$ test=a1111c2222c3333a

$ echo ${test#a*c}
2222c3333a

$ echo ${test##a*c}
3333a

$ echo ${test%a*c}
a1111c2222c3333a   //注意:没有截取

$ echo ${test%%a*c}
a1111c2222c3333a  //注意:没有截取
```

#### 2.3 字符串替换


---
### 参考
https://www.cnblogs.com/chengmo/archive/2010/10/02/1841355.html
