


### 读取

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
