
## 学习

### 1. sort 将文件的每一行作为一个元素进行对比，对比时从首字符往后，依次比较as

```bash
## 默认sort时，不考虑位数，直接从头开始对比
[root@SZX1000538971 test]# echo '100 12 4 3'|tr ' ' '\n'|sort
100
12
3
4
## 字符默认升级sort
[root@SZX1000538971 test]# echo 'c bb aaa'|tr ' ' '\n'|sort
aaa
bb
c
```

### 2. `sort -u` 去除重复行

```bash
[root@SZX1000538971 sort]# cat 1.txt
c1
a
b2d
z4
a
c1
[root@SZX1000538971 sort]# sort 1.txt
a
a
b2d
c1
c1
z4
[root@SZX1000538971 sort]# sort -u 1.txt
a
b2d
c1
z4
```



### 3.`sort -r`降序
`-r, --reverse               reverse the result of comparisons`

```bash
[root@SZX1000538971 sort]# echo '100 12 4 3'|tr ' ' '\n'|sort -r
4
3
12
100
[root@SZX1000538971 sort]# echo 'c bb aaa'|tr ' ' '\n'|sort -r
c
bb
aaa

```

### 4. `sort -o`将结果重定向
` -o, --output=FILE         write result to FILE instead of standard output`

```bash
[root@SZX1000538971 sort]# cat 2.txt
4
22
100
3
## 1.可以使用重定向将结果写入到其他文件
[root@SZX1000538971 sort]# sort -n 2.txt > 2b.txt
[root@SZX1000538971 sort]# cat 2b.txt
3
4
22
100

## 2.但是重定向到源文件本身则会导致文件清空
[root@SZX1000538971 sort]# cat 2.txt
4
22
100
3
[root@SZX1000538971 sort]# sort -n 2.txt > 2.txt
[root@SZX1000538971 sort]# cat 2.txt

## 此时可以使用-o
[root@SZX1000538971 sort]# cat 2.txt
4
22
100
3
[root@SZX1000538971 sort]# sort -n 2.txt -o 2.txt
[root@SZX1000538971 sort]# cat 2.txt
3
4
22
100
```

### 5. `sort -n`按照数字排序而不是字符串

`-n, --numeric-sort          compare according to string numerical value`

```bash
[root@SZX1000538971 ~]# echo '100 20 3' |tr ' ' '\n'| sort
100
20
3
[root@SZX1000538971 ~]# echo '100 20 3' |tr ' ' '\n'| sort -n
3
20
100

```



### 6. `sort -k1`指定列进行排序
`-k, --key=KEYDEF          sort via a key; KEYDEF gives location and type`

```bash
[root@SZX1000538971 sort]# cat 3a.txt
apple 10 2.5
orange 20 3.4
banana 30 5.5
pear 90 2.3
## 按照第1列排序，字符串方式
[root@SZX1000538971 sort]# sort -k1 3a.txt
apple 10 2.5
banana 30 5.5
orange 20 3.4
pear 90 2.3
## 按照第2列排序，数字排序
[root@SZX1000538971 sort]# sort -k2 -n 3a.txt
apple 10 2.5
orange 20 3.4
banana 30 5.5
pear 90 2.3
## 按照第3列排序，数字方式
[root@SZX1000538971 sort]# sort -k3 -n 3a.txt
pear 90 2.3
apple 10 2.5
orange 20 3.4
banana 30 5.5
```

### 7. `sort -t` 结合`sort -k`用来指定分割符，默认是空格(1或多个空格)
`-t, --field-separator=SEP  use SEP instead of non-blank to blank transition`

```bash
[root@SZX1000538971 sort]# cat 3b.txt
apple:10:2.5
orange:20:3.4
banana:30:5.5
pear:90:2.3
[root@SZX1000538971 sort]# sort -t: -k1 3b.txt
apple:10:2.5
banana:30:5.5
orange:20:3.4
pear:90:2.3
[root@SZX1000538971 sort]# sort -t: -k2 -n 3b.txt
apple:10:2.5
orange:20:3.4
banana:30:5.5
pear:90:2.3
[root@SZX1000538971 sort]# sort -t: -k3 -n 3b.txt
pear:90:2.3
apple:10:2.5
orange:20:3.4
banana:30:5.5
```


## 例

```bash
## 假设为  公司名称-人数-工资
[root@SZX1000538971 sort]# cat 4.txt
google 110 5000
baidu 100 5000
guge 50 3000
sohu 100 4500


## 公司名排序
[root@SZX1000538971 sort]# sort -k1 4.txt
baidu 100 5000
google 110 5000
guge 50 3000
sohu 100 4500


## 按照公司人数排序 ，人数相同的按照员工平均工资升序排序
### 对比，只按照人数排序时，baidu在sohu前面
[root@SZX1000538971 sort]# sort -k2n 4.txt
guge 50 3000
baidu 100 5000
sohu 100 4500
google 110 5000
### 方式1
[root@SZX1000538971 sort]# sort -k2n -k3n 4.txt
guge 50 3000
sohu 100 4500
baidu 100 5000
google 110 5000
### 方式2，将n当做公共的
[root@SZX1000538971 sort]# sort -n -k2 -k3 4.txt
guge 50 3000
sohu 100 4500
baidu 100 5000
google 110 5000


## 按照员工工资降序排序，如果员工工资相同的，则按照公司人数升序排序
### 方式1
[root@SZX1000538971 sort]# sort -k3nr -k2n 4.txt
baidu 100 5000
google 110 5000
sohu 100 4500
guge 50 3000
### 方式2
[root@SZX1000538971 sort]# sort -n -k3r -k2 4.txt
baidu 100 5000
google 110 5000
sohu 100 4500
guge 50 3000

```



