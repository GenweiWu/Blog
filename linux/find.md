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

#### 5. 根据时间查找文件
 `find -mmin 58` 查找修改了58分钟的文件  
 `find -mmin +58` 查找修改了超过58分钟的文件  
 `find -mmin -58` 查找58分钟内修改的文件  


```
-amin n   查找系统中最后N分钟访问的文件
-atime n  查找系统中最后n*24小时访问的文件
-cmin n   查找系统中最后N分钟被改变文件状态的文件
-ctime n  查找系统中最后n*24小时被改变文件状态的文件
-mmin n   查找系统中最后N分钟被改变文件数据的文件
-mtime n  查找系统中最后n*24小时被改变文件数据的文件
```
 - 即a表示访问access,c表示改变状态change,m表示修改文件modify
 - min表示以分钟计算,time表示以24小时计算(天计算)

---
#### 常用记录
`find . -type d -empty -maxdepth 1`    找到当前目录下所有空文件夹(也可以不限制文件层级，则包括空的子目录)


---
#### 参考 
http://www.cnblogs.com/peida/archive/2012/11/13/2767374.html
