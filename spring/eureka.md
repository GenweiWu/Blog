Spring Cloud Eureka
==

## 增加SpringBoot应用监控能力

#### 1、实现说明
```
1. 需要配置让支持监控
 (1)增加依赖 + (2)指定content-path
 此时可以得到监控的url为 
 - http://localhost:8001/test-service/admin/info
 - http://localhost:8001/test-service/admin/env
 但是这些链接你得自己手工输入
 
2. 组件注册到eureka上之后，也会生成一条超链接可以点击，那么刚好配置成上面的链接就可以方便的监控组件了
 (3)通过设置`management.context-path`达到上述目的
```

#### 2、样例

> (1)pom.xml中添加
```pom.xml
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

> application.yml
```yaml
server:
    port: 8001
    context-path: /test-service

management:
    add-application-context-header: false
    # (2)指定了监控请求的content-path
    context-path: /admin
    security:
        enabled: false
        
eureka:
    client:
        service-url:
            registryFetchIntervalSeconds: 5
    instance:
        statusPageUrlPath: ${server.context-path:}${management.context-path:}/info
        healthCheckUrlPath: ${server.context-path:}${management.context-path:}/health
        metadata-map:
            # (3)这里是eureka注册到eureka上之后，可以点击的那个了解，这里这样设置刚好访问到监控内容
            management.context-path: ${server.context-path:}${management.context-path:}
        lease-renewal-interval-in-seconds: 10
        lease-expiration-duration-in-seconds: 20
        instanceId: ${spring.application.name}:${spring.application.instance_id:${random.value}}        
```
