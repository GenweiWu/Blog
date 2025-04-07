
```
nsenter [选项] [--] [程序 [参数...]]]
```
## 常用选项
```
-t, --target <pid>：指定目标进程的PID

-m, --mount：进入挂载命名空间

-u, --uts：进入UTS命名空间

-i, --ipc：进入IPC命名空间

-n, --net：进入网络命名空间

-p, --pid：进入PID命名空间

-C, --cgroup：进入Cgroup命名空间

-U, --user：进入用户命名空间

-S, --setuid <uid>：设置用户ID

-G, --setgid <gid>：设置组ID

-r, --root[=dir]：设置根目录

-w, --wd[=dir]：设置工作目录
```

## 样例
```bash
nsenter --target <Pid> -n bash 
```
