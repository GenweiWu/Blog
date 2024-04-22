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

但是要依赖服务器的配置，涉及到kafka的版本和kafkaClient版本问题，所以感觉不太可靠

### 推荐方式
[官方不推荐使用自动生成topic相关属性](https://cwiki.apache.org/confluence/display/KAFKA/KIP-487%3A+Client-side+Automatic+Topic+Creation+on+Producer)  
使用AdminClient手工创建  


