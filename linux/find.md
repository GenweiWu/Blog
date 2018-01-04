find用于查找文件或目录
==


#### 1. 根据类型查找

`find -type f ` 查找文件  
`find -type d`  查找文件夹  

#### 2. 根据文件名查找

`find -name *.log` 查找文件名匹配.log的，比如1.log等

#### 3. 查找文件并排序

`find -type d|sort`  排序是根据文件名的字母顺序不是文件大小

#### 4. 根据大小查找文件
`find -size 248k`  查找大小刚好是248K的文件<br>
`find -size +248k` 查找大小大于248K的文件<br>
`find -size -248k` 查找大小小于248K的文件<br>

```man
-size n[cwbkMG]
    File uses n units of space.  The following suffixes can be used:

    `b'    for 512-byte blocks (this is the default if no suffix is used)

    `c'    for bytes

    `w'    for two-byte words

    `k'    for Kilobytes (units of 1024 bytes)

    `M'    for Megabytes (units of 1048576 bytes)

    `G'    for Gigabytes (units of 1073741824 bytes)
```
