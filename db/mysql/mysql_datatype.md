
## mysql 与 java数据类型对照
> https://www.cnblogs.com/JemBai/archive/2009/08/20/1550683.html

注意：mysql的int对应java的long，因为java的int字段长度有限(像`6111111111`这个已经超出了java的int的长度了)


## mysql的integer和int

- 两者没啥区别
> https://dev.mysql.com/doc/refman/8.0/en/integer-types.html

- 但是`PRIORITY` integer DEFAULT NULL 执行后得到的是 `PRIORITY`(11) DEFAULT NULL，暂且认为是默认值

- int(11)并不是说只能存储11位，只是说展示的时候有区别(比如补齐到XX位),所以没有int(11)比int(5)存储的数据长一说
> https://www.virendrachandak.com/techtalk/mysql-int11-what-does-it-means/
> https://blog.csdn.net/allenjay11/article/details/76549503

- 如果int(5)字段太短，那么换成int(11)是没用的，换成decimal才有用
