


### 读取

```
# 单个读取
${array[0]}

# 读取所有元素
${array[@]}
${array[@]}
```

> demo
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
