

### 1、命令格式
```
printf  format-string  [arguments...]
```

### 2、printf很好玩

- 输出进度条效果的简单shell脚本
> https://blog.csdn.net/ITzhangdaopin/article/details/82965875
```bash
#!/bin/bash
b=''
i=0
while [ $i -le 100 ]
do
 printf "[%-50s] %d%% \r" "$b" "$i";
 sleep 0.2
 ((i=i+2))
 b+='#'
done
echo
```
