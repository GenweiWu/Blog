


## 读取

```
# 单个读取
${array[0]}

# 读取所有元素
${array[@]}
${array[@]}
```

> 如果数组中元素不带空格，用`@`和`*`都可以for遍历
```bash
[root@SZX1000538971 shellT]# array=(1 2 3 4 5)
[root@SZX1000538971 shellT]# echo $array
1
[root@SZX1000538971 shellT]# echo ${array[0]}
1
[root@SZX1000538971 shellT]# echo ${array[-1]}
5
[root@SZX1000538971 shellT]# echo ${array[@]}
1 2 3 4 5
[root@SZX1000538971 shellT]# echo ${array[*]}
1 2 3 4 5
[root@SZX1000538971 shellT]# for i in ${array[@]};do echo "-->: $i" ;done
-->: 1
-->: 2
-->: 3
-->: 4
-->: 5
[root@SZX1000538971 shellT]# for i in ${array[*]};do echo "-->: $i" ;done
-->: 1
-->: 2
-->: 3
-->: 4
-->: 5

```

> 如果数组中元素带空格，则要加引号  
> 遍历的时候，配置`@`要加双引号遍历；配合`*`的话是整个数组内容展示了
```bash
[root@SZX1000538971 shellT]# names=(111 '222 bbb' 333 '444 ccc' 555)
[root@SZX1000538971 shellT]# echo $names
111
[root@SZX1000538971 shellT]# echo ${names[0]}
111
[root@SZX1000538971 shellT]# echo ${names[1]}
222 bbb
[root@SZX1000538971 shellT]# echo ${names[-1]}
555
[root@SZX1000538971 shellT]# echo ${names[@]}
111 222 bbb 333 444 ccc 555
[root@SZX1000538971 shellT]# echo ${names[*]}
111 222 bbb 333 444 ccc 555
[root@SZX1000538971 shellT]# for i in ${names[@]}; do echo "-->$i";done
-->111
-->222
-->bbb
-->333
-->444
-->ccc
-->555
[root@SZX1000538971 shellT]# for i in "${names[@]}"; do echo "-->$i";done
-->111
-->222 bbb
-->333
-->444 ccc
-->555
[root@SZX1000538971 shellT]# for i in '${names[@]}'; do echo "-->$i";done
-->${names[@]}
[root@SZX1000538971 shellT]# for i in ${names[*]}; do echo "-->$i";done
-->111
-->222
-->bbb
-->333
-->444
-->ccc
-->555
[root@SZX1000538971 shellT]# for i in "${names[*]}"; do echo "-->$i";done
-->111 222 bbb 333 444 ccc 555

```

> 数组的长度
```
[root@SZX1000538971 ~]# aa=( 11aabb 2 3 4 )
## 这样读取的实际上是元素1的字符串长度
[root@SZX1000538971 ~]# echo ${#aa}
6
## 这样读取的才是数组长度
[root@SZX1000538971 ~]# echo ${#aa[@]}
4
[root@SZX1000538971 ~]# echo ${#aa[0]}
6
[root@SZX1000538971 ~]# echo ${#aa[1]}
1
```


## 关联数组(用于模拟map)

> 普通数组，模拟map失败
```
[root@SZX1000538971 shellT]# color['red']=red111
[root@SZX1000538971 shellT]# color['green']=green222
[root@SZX1000538971 shellT]# echo $color
green222
[root@SZX1000538971 shellT]# echo ${color[@]}
green222
[root@SZX1000538971 shellT]# echo ${color[*]}
green222
[root@SZX1000538971 shellT]# echo ${#color[@]}
1

```

> 使用declare -A申明关联数组
```
[root@SZX1000538971 shellT]# declare -A hobby
[root@SZX1000538971 shellT]# hobby['run']=run111
[root@SZX1000538971 shellT]# hobby['swim']=swim222
[root@SZX1000538971 shellT]# echo ${hobby[@]}
run111 swim222
[root@SZX1000538971 shellT]# echo ${hobby[*]}
run111 swim222
[root@SZX1000538971 shellT]# echo ${#hobby[@]}
2

```
