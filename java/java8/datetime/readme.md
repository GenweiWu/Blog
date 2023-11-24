
## offsetDateTime


> https://stackoverflow.com/questions/30234594/whats-the-difference-between-zoneddatetime-and-offsetdatetime
> https://blog.csdn.net/woshiren123ew/article/details/82858665

```
- OffsetDateTime和ZonedDateTime之间的差异在于后者包括涵盖夏令时调整的规则
- 在写数据库是采用OffsetDateTime
```



|                  | DateTime                                    | 时区/偏移量                                                  |
| ---------------- | ------------------------------------------- | ------------------------------------------------------------ |
| 不支持，简单偏移 | `OffsetDateTime`                            | `ZoneOffset`    只能用于表示具体的时间偏移                   |
| 支持夏令时       | `ZonedDateTime`    包括涵盖夏令时调整的规则 | `ZoneId`   是一个时区标识符，可以表示一个时区或者一个时区规则，例如 "Asia/Shanghai" 表示中国上海的时区；可以用于表示更复杂的时区规则，例如夏令时 |







## 参考

- https://www.liaoxuefeng.com/wiki/1252599548343744/1303905346519074

- https://www.boraji.com/java-8-offsetdatetime-example

- [datetimeFormatter写法](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)
