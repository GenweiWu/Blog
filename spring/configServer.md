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
