
## 1、xargs有啥用

有些命令可以将标准输入作为参数，比如grep  
`find -name "*.txt" | grep 'test'`
但是也有很多命令只能使用参数，不能直接使用标准输入  
`echo 'hello'|echo`无法打印  

### xargs就是将标准输入转换为参数  
`echo 'hello' | xarga echo`就可以了  

## 2、xargs [-options] [command]

#### xargs [command]将前面的输出，转换成当前命令的参数
#### xargs -t [command] 打印出实际执行的命令

```cmd
[root@SZX1000538971 test]# echo '11hello111' > 1.txt
[root@SZX1000538971 test]# echo '22hello222' > 2.txt
[root@SZX1000538971 test]# echo '33HELLO333' > 3.txt
[root@SZX1000538971 test]#
[root@SZX1000538971 test]# ll
total 12
-rw-------. 1 root root 11 Aug  8 14:33 1.txt
-rw-------. 1 root root 11 Aug  8 14:33 2.txt
-rw-------. 1 root root 11 Aug  8 14:33 3.txt
[root@SZX1000538971 test]# find -name "*.txt"|grep hello
[root@SZX1000538971 test]# find -name "*.txt"|xargs grep hello
./2.txt:22hello222
./1.txt:11hello111
[root@SZX1000538971 test]# find -name "*.txt"|xargs -t grep hello
grep hello ./2.txt ./1.txt ./3.txt
./2.txt:22hello222
./1.txt:11hello111

```

#### xargs -0 [command]  //默认的分割是空格和换行符,-0 表示指定null为分割
```
[root@SZX1000538971 test]# tree | xargs echo
. ├── 1.txt ├── 2.txt └── 3.txt 0 directories, 3 files
[root@SZX1000538971 test]# tree | xargs -0 echo
.
├── 1.txt
├── 2.txt
└── 3.txt

0 directories, 3 files
```

## 3、find xargs遇到空格的问题

有时候以空格进行分割有问题
```console
[root@SZX1000538971 test]# ll
total 0
-rw-------. 1 root root 0 Nov 14 15:15 1.txt
-rw-------. 1 root root 0 Nov 14 15:15 2 22.txt
[root@SZX1000538971 test]# find -type f |xargs ls -l
ls: cannot access ./2: No such file or directory
ls: cannot access 22.txt: No such file or directory
-rw-------. 1 root root 0 Nov 14 15:15 ./1.txt
[root@SZX1000538971 test]# find -type f -print0 |xargs -0 ls -l
-rw-------. 1 root root 0 Nov 14 15:15 ./1.txt
-rw-------. 1 root root 0 Nov 14 15:15 ./2 22.txt
```

