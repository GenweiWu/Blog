

> http://cn.linux.vbird.org/linux_basic/0210filepermission.php

权限对文件的重要性
```
文件是实际含有数据的地方，包括一般文本文件、数据库内容文件、二进制可执行文件(binary program)等等。 因此，权限对于文件来说，他的意义是这样的：

r (read)：可读取此一文件的实际内容，如读取文本文件的文字内容等；
w (write)：可以编辑、新增或者是修改该文件的内容(但不含删除该文件)；
x (execute)：该文件具有可以被系统执行的权限。
那个可读(r)代表读取文件内容是还好了解，那么可执行(x)呢？这里你就必须要小心啦！ 因为在Windows底下一个文件是否具有执行的能力是藉由『 扩展名 』来判断的， 例如：.exe, .bat, .com 等等，但是在Linux底下，我们的文件是否能被执行，则是藉由是否具有『x』这个权限来决定的！跟档名是没有绝对的关系的！

至于最后一个w这个权限呢？当你对一个文件具有w权限时，你可以具有写入/编辑/新增/修改文件的内容的权限， 但并不具备有删除该文件本身的权限！对于文件的rwx来说， 主要都是针对『文件的内容』而言，与文件档名的存在与否没有关系喔！因为文件记录的是实际的数据嘛！
```


权限对目录的重要性
```
文件是存放实际数据的所在，那么目录主要是储存啥玩意啊？目录主要的内容在记录文件名列表，文件名与目录有强烈的关连啦！ 所以如果是针对目录时，那个 r, w, x 对目录是什么意义呢？

r (read contents in directory)：

表示具有读取目录结构列表的权限，所以当你具有读取(r)一个目录的权限时，表示你可以查询该目录下的文件名数据。 所以你就可以利用 ls 这个指令将该目录的内容列表显示出来！

w (modify contents of directory)：

这个可写入的权限对目录来说，是很了不起的！ 因为他表示你具有异动该目录结构列表的权限，也就是底下这些权限：

建立新的文件与目录；
删除已经存在的文件与目录(不论该文件的权限为何！)
将已存在的文件或目录进行更名；
搬移该目录内的文件、目录位置。

总之，目录的w权限就与该目录底下的文件名异动有关就对了啦！

x (access directory)：

咦！目录的执行权限有啥用途啊？目录只是记录文件名而已，总不能拿来执行吧？没错！目录不可以被执行，目录的x代表的是用户能否进入该目录成为工作目录的用途！ 所谓的工作目录(work directory)就是你目前所在的目录啦！举例来说，当你登入Linux时， 你所在的家目录就是你当下的工作目录。而变换目录的指令是『cd』(change directory)啰！
```

## `bash script.sh` vs `./script.sh`

> https://unix.stackexchange.com/a/136550
- bash script.sh只需要文件的读权限
- ./script.sh 则需要读权限+执行权限

> run.sh对于test用户是只读的，所以只能`bash run.sh`，而无法`./run.sh`
```
[root@SZX1000538970 test222]# ll -a
total 12
drwx-----x.  2 root root 4096 Aug  5 16:40 .
drwxr-xr-x. 10 root root 4096 Aug  5 16:38 ..
-rwxr--r--.  1 root root   13 Aug  5 16:40 run.sh
```

```
[test@SZX1000538970 test222]$ ll run.sh
-rwxr--r--. 1 root root 13 Aug  5 16:40 run.sh
[test@SZX1000538970 test222]$ ll -a
ls: cannot open directory .: Permission denied
[test@SZX1000538970 test222]$ bash run.sh
hello
[test@SZX1000538970 test222]$ ./run.sh
-bash: ./run.sh: Permission denied
```

> run.sh对于test用户是可读可执行的，所以`./run.sh`可以正常执行了
```
[root@SZX1000538970 test222]# ll -a
total 12
drwx-----x.  2 root root 4096 Aug  5 16:40 .
drwxr-xr-x. 10 root root 4096 Aug  5 16:38 ..
-rwxr-xr-x.  1 root root   13 Aug  5 16:40 run.sh
```

```
[test@SZX1000538970 test222]$ ll run.sh
-rwxr-xr-x. 1 root root 13 Aug  5 16:40 run.sh
[test@SZX1000538970 test222]$ ll -a
ls: cannot open directory .: Permission denied
[test@SZX1000538970 test222]$ ./run.sh
hello
```




