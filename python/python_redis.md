python redis操作
==

## 1、redis-py
- 官网
https://github.com/andymccurdy/redis-py
- 资料
http://redis-py.readthedocs.io/en/latest/#

## 2、基础连接
```py
import redis

# redis连接池，注意密码的配置password
pool = redis.ConnectionPool(host='127.0.0.1', port='6379', password='mypwd', decode_responses=True)
r = redis.Redis(connection_pool=pool)
print(r.dbsize())

print(r.get('test'))
```
## 3. 常用
- zset  
[python 操作redis之——有序集合(sorted set) （七）](http://www.cnblogs.com/xuchunlin/p/7097255.html)
