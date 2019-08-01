
## set -x方便调试脚本

> 未打开调试
```CMD
[root@SZX1000538971 test]# cat 111.sh 
#!/bin/bash

#set -x
echo 'this is $date'
echo "this is `date`"
[root@SZX1000538971 test]# ./111.sh 
this is $date
this is Thu Aug  1 16:40:23 CST 2019
```

> 打开调试
```CMD
[root@SZX1000538971 test]# cat 111.sh 
#!/bin/bash

set -x
echo 'this is $date'
echo "this is `date`"
[root@SZX1000538971 test]# ./111.sh 
+ echo 'this is $date'
this is $date
++ date
+ echo 'this is Thu Aug  1 16:41:12 CST 2019'
this is Thu Aug  1 16:41:12 CST 2019
```

## set -e 返回值非0就失败

> set -e不忽略失败
```CMD
[root@SZX1000538971 test]# cat 111.sh 
#!/bin/bash

set -e

cat noExist.log
echo 'success...'
[root@SZX1000538971 test]# ./111.sh 
cat: noExist.log: No such file or directory
```

> set +e忽略失败(有些系统默认就是不忽略，才会用到set +e包裹，这里只是为了演示)
```CMD
[root@SZX1000538971 test]# cat 111.sh 
#!/bin/bash

set -e


set +e
cat noExist.log
set -e


echo 'success...'
[root@SZX1000538971 test]# ./111.sh 
cat: noExist.log: No such file or directory
success...
```


## 参考
- https://man.linuxde.net/set
- https://blog.csdn.net/longshenlmj/article/details/9495413


```
-a：标示已修改的变量，以供输出至环境变量。
-b：使被中止的后台程序立刻回报执行状态。
-C：转向所产生的文件无法覆盖已存在的文件。
-d：Shell预设会用杂凑表记忆使用过的指令，以加速指令的执行。使用-d参数可取消。
-e：若指令传回值不等于0，则立即退出shell。
-f：取消使用通配符。
-h：自动记录函数的所在位置。
-H Shell：可利用"!"加<指令编号>的方式来执行history中记录的指令。
-k：指令所给的参数都会被视为此指令的环境变量。
-l：记录for循环的变量名称。
-m：使用监视模式。
-n：只读取指令，而不实际执行。
-p：启动优先顺序模式。
-P：启动-P参数后，执行指令时，会以实际的文件或目录来取代符号连接。
-t：执行完随后的指令，即退出shell。
-u：当执行时使用到未定义过的变量，则显示错误信息。
-v：显示shell所读取的输入值。
-x：执行指令后，会先显示该指令及所下的参数。
```
