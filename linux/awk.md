awk是一个行数据处理命令
==

## 基本用法

```bash
## $0表示全部
## $1,$2表示第一个第二个元素
## $NF表示最后一个元素,$(NF-1)表示倒数第二个元素
[root@SZX1000538971 ~]# echo '10 20 30 40 50'|awk '{print $0}'
10 20 30 40 50
[root@SZX1000538971 ~]# echo '10 20 30 40 50'|awk '{print $1}'
10
[root@SZX1000538971 ~]# echo '10 20 30 40 50'|awk '{print $5}'
50
```

```bash
## awk -F 指定分隔符；默认分隔符是空格是tab
[root@SZX1000538971 shellT]# echo '1,2,3,4,5'|awk '{print NF}'
1
[root@SZX1000538971 shellT]# echo '1,2,3,4,5'|awk '{print $1}'
1,2,3,4,5
[root@SZX1000538971 shellT]# echo '1,2,3,4,5'|awk -F ',' '{print NF}'
5
[root@SZX1000538971 shellT]# echo '1,2,3,4,5'|awk -F ',' '{print $1}'
1
```

## 变量

> `NF`表示当前行有多少个字段，则`$NF`表示当前最后一个元素
```bash
[root@SZX1000538971 ~]# echo '10 20 30 40 50'|awk '{print $NF}'
50
# 注意$(NF-1)不是$NF-1
[root@SZX1000538971 ~]# echo '10 20 30 40 50'|awk '{print $NF-1}'
49
[root@SZX1000538971 ~]# echo '10 20 30 40 50'|awk '{print $(NF-1)}'
40
```



> `NR`表示当前行

```bash
[root@SZX1000538971 shellT]# cat 111.txt
10
20
30
40
50
## 注意是NR而不是$NR
[root@SZX1000538971 shellT]# awk '{print $NR")" $1}' 111.txt
10)10
)20
)30
)40
)50
[root@SZX1000538971 shellT]# awk '{print NR")" $1}' 111.txt
1)10
2)20
3)30
4)40
5)50
```



## 条件

`awk 条件  动作  file`

```bash
[root@SZX1000538971 shellT]# cat 111.txt
10
20
30
40
50
## 输出奇数行
[root@SZX1000538971 shellT]# awk 'NR%2==1 {print $1}' 111.txt
10
30
50
## 输出>第三行
[root@SZX1000538971 shellT]# awk 'NR>3 {print NR ":"$1}' 111.txt
4:40
5:50
## 输出>=第四行
[root@SZX1000538971 shellT]# awk 'NR>=4 {print NR ":"$1}' 111.txt
4:40
5:50
```



## if

> 可以if else
```bash
[dave@SZX1000538971 shellT]# cat 333.txt
aa:10
bb:20
cc:30
dd:40
ee:50
[dave@SZX1000538971 shellT]# awk -F ':' '$2>=30 {print $0}' 333.txt
cc:30
dd:40
ee:50
[dave@SZX1000538971 shellT]# awk -F ':' '{if($2>=30) print $0;else print $1":--"}' 333.txt
aa:--
bb:--
cc:30
dd:40
ee:50
```


## awk很好玩
```bash
# 获取IP地址
ifconfig eth0 | grep "inet addr" | awk -F ":" '{print $2}' | awk '{print $1}'
```

## 参考
- https://www.ruanyifeng.com/blog/2018/11/awk.html


