读文件
==
### 1、常用方法

#### 1.1 逐行读文件
```shell
cat $fileName | while read LINE
do echo ${LINE}
done
```

### 2、样例

- 逐行读文件
```shell
SOURCE=D:/table-name.result
TARGET=D:/table-name222.txt
> $TARGET
cat $SOURCE | while read LINE; do echo ${LINE/*table-name=/} >> $TARGET;done

#这样也行(貌似分号;等价换行写)

cat $SOURCE | while read LINE
do echo ${LINE/*table-name=/} >> $TARGET
done
```
