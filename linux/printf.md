

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

- 格式化打印
```bash
# !/bin/bash

printf "%-10s %-8s %-4s\n" Name Sex Weight-kg
printf "%-10s %-8s %-4.2f\n" ZhangSan male 66.1234
printf "%-10s %-8s %-4.2f\n" LiSi male 48.6985
printf "%-10s %-8s %-4.2f\n" Wangwu female 47.5197
```

> 对比下未格式化和格式化的
```bash
# ./table_print.sh
## 未格式化的
Name Sex Weight-kg
ZhangSan male 66.1234
LiSi male 48.6985
Wangwu female 47.5197

# ./table_print.sh
## 格式化的
Name       Sex      Weight-kg
ZhangSan   male     66.12
LiSi       male     48.70
Wangwu     female   47.52

```


