
## 主题 

- [添加监听（addListener）](ListenableFutureDemo.java)
- [添加回调（Futures.addCallBack）](ListenableFutureDemo2.java)

## 参考
#### - 依赖
```pom.xml
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>23.0</version>
</dependency>
```

#### - 日志打印(主要是为了打印出线程名称)

> log4j.properties

```properties
# Root logger option
log4j.rootLogger=INFO, stdout

log4j.logger.org.redisson=DEBUG
#log4j.logger.org.redisson.command.RedisExecutor=DEBUG

# Direct log messages to stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c{1}:%L - %m%n
```
