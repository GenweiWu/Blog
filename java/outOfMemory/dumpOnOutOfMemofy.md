
### HeapDumpOnOutOfMemoryError 内存溢出时自动dump内存信息
```
java -XX:+HeapDumpOnOutOfMemoryError abc.jar
```

### HeapDumpPath指定dump文件保存的文件路径
```
-XX:HeapDumpPath=/disk2/dumps
-XX:HeapDumpPath=data
```
