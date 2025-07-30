## 用法

```
grep [选项] [--color=auto] '搜寻字符串' filename

-i 忽略大小写
-n 显示行号
-v 反向匹配，即显示不匹配的行
-r 文件夹下递归查找

^搜索  以搜索开头
搜索$ 以搜索结尾
```

## 样例

#### 样例1：搜索文件内容  
![image](https://user-images.githubusercontent.com/16630659/70960370-d141a400-20b9-11ea-8a46-a41dd89d4a76.png)

#### 样例2：搜索文件夹  
![image](https://user-images.githubusercontent.com/16630659/70960655-968c3b80-20ba-11ea-8cf7-4f398ad6b451.png)

#### 样例3：判断文件是否包含指定字符串 `grep -c hello 1.txt`
```bash
FIND_FILE="./Test.txt"
FIND_STR="Hello Dave"
# 判断匹配函数，匹配函数不为0，则包含给定字符
if [ `grep -c "$FIND_STR" $FIND_FILE` -ne '0' ];then
    echo "The File Has $FIND_STR!"
    exit 0
fi
```

#### 4. grep a或b
`grep 'a\|b'`
```
$ cat 111.txt
aaa
bbb
eee
aabb
ccc
$ cat 111.txt | grep aaa
aaa
$ cat 111.txt | grep bb
bbb
aabb
$
$ cat 111.txt | grep aa|bb
-bash: bb: command not found
$ cat 111.txt | grep aa\|bb
$
$ cat 111.txt | grep 'aa\|bb'
aaa
bbb
aabb
```

#### 5. grep 多个文件
```
grep 'aaa' vs-test*.log
```




## 参考
- https://www.cnblogs.com/ggjucheng/archive/2013/01/13/2856896.html
- https://www.cnblogs.com/peida/archive/2012/12/17/2821195.html

