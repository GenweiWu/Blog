

## 循环打印数字

```bash
i=0 && while(true) do echo $i;i=$(($i+1));sleep 0.01 ;done
```

```bash
#!/bin/bash
#set -x

number=0
while [ $number -lt 10 ]; do
 echo "num:$number"
 number=$(($number+1))
done
```
