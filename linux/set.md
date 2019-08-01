
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





## 参考
- https://man.linuxde.net/set
- https://blog.csdn.net/longshenlmj/article/details/9495413
