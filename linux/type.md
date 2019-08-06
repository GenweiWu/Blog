type命令用来显示指定命令的类型，判断给出的指令是内部指令还是外部指令。


## 可以用来查看实际执行的命令
> http://linux.vbird.org/linux_basic/0320bash.php#settings_path
```
[root@SZX1000538970 test222]# type -a cp
cp is aliased to `cp -i'
cp is /usr/bin/cp
[root@SZX1000538970 test222]# type -a ll
ll is aliased to `ls -l --color=auto'
```
