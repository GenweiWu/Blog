Spring cloud config的使用
==

## 配置中心
> 验证配置中心启动成功 
```
 http://127.0.0.1:8888/env 
 ```

> 验证配置中心的配置已经生效  
```
 http://127.0.0.1:8888/sample-cloud-client/default
 ```

> [路径规则](https://cloud.spring.io/spring-cloud-static/spring-cloud-config/1.3.1.RELEASE/)
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

## 组件
> 验证组件的配置信息，来查看是否成功获取配置中心的配置
```
http://127.0.0.1:8080/env
```

## refresh更新配置
> https://spring.io/guides/gs/centralized-configuration/

### 组件

(1) 基础刷新方法  
`curl -XPOST http://127.0.0.1:8081/refresh`

(2)进阶
application.yml
```yaml
server:
    port: 21020
    context-path: /test-service
    
management:
    add-application-context-header: false
    context-path: /admin
    security:
        enabled: false        
```
则对应的请求是 `curl -XPOST http://127.0.0.1:8081/test-service/admin/refresh`

