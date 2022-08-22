

### 设置老化时间

> https://stackoverflow.com/a/29137045    

`log.retention.hour`s is a property of a broker which is used as a default value when a topic is created. When you change configurations of currently running topic using kafka-topics.sh, you should specify a topic-level property.

A topic-level property for log retention time is `retention.ms`.

