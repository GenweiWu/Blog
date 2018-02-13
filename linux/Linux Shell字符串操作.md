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
|${str%%subString}|从字符串str的右边开始匹配,删除最长匹配|<li>从右边开始匹配,但是还是从左向右看</li><li>subString不是正则,`a*b`中的`*`指任意字符</li>|
| | | |
|${str/substring/relacement}|替换字符串str中的substring为replacement,只替换第一个匹配||
|${str//substring/relacement}|替换字符串str中的substring为replacement,替换所有匹配||
|${str/#substring/relacement}|如果字符串str中前缀匹配substring,则替换subtring为replacement|只替换当前匹配|
|${str/$substring/relacement}|如果字符串str中后缀匹配substring,则替换subtring为replacement|只替换当前匹配|


#### 2.1 字符串长度
```
$ var=12345.txt

$ echo ${var}
12345.txt

$ echo ${#var}
9
```

#### 2.2 字符串截取
- position截取和长度截取
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

- 匹配截取
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

- 匹配截取
```
$ test=a11c22a33c

$ echo ${test#a*c}
22a33c

$ echo ${test##a*c}


$ echo ${test%a*c}
a11c22

$ echo ${test%%a*c}
```

#### 2.3 字符串替换

- 匹配替换
```
$ test=1ab2ab3

$ echo ${test/ab/AB}
1AB2ab3

$ echo ${test//ab/AB}
1AB2AB3

$ echo ${test/#ab/AB}
1ab2ab3

$ echo ${test/%ab/AB}
1ab2ab3
```

- 匹配替换
```
$ test=ab22ab

$ echo ${test/ab/AB}
AB22ab

$ echo ${test//ab/AB}
AB22AB

$ echo ${test/#ab/AB}
AB22ab  //只替换当前匹配

$ echo ${test/%ab/AB}
ab22AB  //只替换当前匹配
```

### 3.练习
- 修改文件后缀,名称不变;如从1.txt到1.log
```shell
for f in *.txt;do
   target=${f/%.txt/.log}
   mv $f $target
done   
```

---
### 参考
https://www.cnblogs.com/chengmo/archive/2010/10/02/1841355.html
