

## `Runtime.getRuntime()`和`ProcessBuilder`执行`command`的区别

> 这里的command是完整字符串
```java
Process process = Runtime.getRuntime().exec("git status", null, dir);
```
> 这里是分开的
```java
ProcessBuilder builder = new ProcessBuilder("git","status");
builder.directory(dir);
builder.redirectErrorStream(true);
Process process = builder.start();
```


