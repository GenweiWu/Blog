
### Cache-control的妙用

nginx ---> jenkins
- 用jenkins可以登录
- 通过nginx代理jenkins后，点击登陆输入密码后，刷新页面发现跟没登录一样

```
add_header Cache-Control no-cache;
add_header Cache-Control private;
add_header Cache-Control no-store;
```






### 参考
- https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cache-Control
