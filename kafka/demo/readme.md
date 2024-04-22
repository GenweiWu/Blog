## 依赖
```pom.xml
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka_2.13</artifactId>
    <version>2.7.0</version>
</dependency>
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>2.7.0</version>
</dependency>
```

## 监听topic不存在自动创建？  
### 自动生成topic(不推荐)
- kafka服务的配置: `auto.create.topics.enable`
- kafka consumer的配置: `allow.auto.create.topics`

> 前提：topic一开始不存在
>
> 测试点：topic是否会被自动创建

|                                 | 消费者的properties设置          | 生产者向topic生产数据时 | 消费者监听topic时 |
| ------------------------------- | ------------------------------- | ----------------------- | ----------------- |
| auto.create.topics.enable=true  | /                               | YES                     | YES               |
| auto.create.topics.enable=false | allow.auto.create.topics = true(感觉没生效) | NO                      | NO                |



但是要依赖服务器的配置，涉及到kafka的版本和kafkaClient版本问题，所以感觉不太可靠

### 推荐方式
[官方不推荐使用自动生成topic相关属性](https://cwiki.apache.org/confluence/display/KAFKA/KIP-487%3A+Client-side+Automatic+Topic+Creation+on+Producer)  
使用AdminClient手工创建  


