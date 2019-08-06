

## nohup实现后台运行

> 可以实行后台运行，但是退出终端后会退出
```cmd
java -jar hello.jar &
```

> nohup方式可以安全的退出终端后继续使用 
```cmd
nohup java -jar hello.jar &

//退出的方式是找出进程杀掉
ps -ef|grep java
kill -9 xxx
```
