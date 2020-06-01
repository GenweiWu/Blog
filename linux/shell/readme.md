
### 1.变量加引号，否则为空时会报错

> wrong
```bash
if [ $var == $var1 ]; then
  do something
else
  do something
fi
```

> right
```bash
if [ "$var" == "$var1" ]; then
  do something
else
  do something
fi
```



## 参考
- https://wangdoc.com/bash/condition.html
