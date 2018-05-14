shell片段
==

- 获取mvn打包结果
```shell
#!/bin/bash
mvn deploy
STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "Deployment Successful"
else
echo "Deployment Failed"
fi
```
