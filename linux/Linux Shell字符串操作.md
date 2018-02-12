Linux Shell字符串操作
===

### 1. 判断读取字符串值
`
TODO
`

### 2. 字符串长度、截取、替换

|命令|说明|备注|
|---|---|---|
|${#str}|获取字符串str的长度|得到|
|${str:index}|从字符串str的index位置开始截取子串|<li>从0开始;</li> <li>包括index本身</li>|

#### 2.1 字符串长度
```
$ var=12345.txt

$ echo ${var}
12345.txt

$ echo ${#var}
9
```


#### 2.2 字符串截取

#### 2.3 字符串替换


---
### 参考
https://www.cnblogs.com/chengmo/archive/2010/10/02/1841355.html
