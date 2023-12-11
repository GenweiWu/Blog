## 入门

> https://github.com/redisson/redisson#quick-start

### 1.引入依赖
```pom.xml
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <version>3.25.0</version>
</dependency>
```


### 3.基本代码

```java
package com.njust.redisson;

import org.junit.Assert;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonTest {

    /**
     * 默认连接
     * <ul>
     *     <li>127.0.0.1:6379</li>
     *     <li>不设置密码</li>
     * </ul>
     */
    @Test
    public void hello() {
        RedissonClient redissonClient = Redisson.create();
        Config config = redissonClient.getConfig();
        Assert.assertTrue(config.isSingleConfig());
    }

}

```
