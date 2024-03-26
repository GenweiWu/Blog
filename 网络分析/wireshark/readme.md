
### 关键字

1. `eq` 和 ``==`` 等同
2. 与：`and`
3. 或：`or`
4. 非：not和`!`

```bash
http and ip.addr == 1.2.3.4
http or ip.addr == 1.2.3.4
http and !ip.addr == 1.2.3.4
```



### 针对ip过滤

```bash
## 针对源地址过滤
ip.src == 1。2.3.4

## 针对目标地址过滤
ip.dst == 1.2.3.4

## 针对源地址或目标地址过滤
ip.addr == 1.2.3.4
```



### 针对域名过滤

```bash
## 使用contains模糊匹配
http.host contains baidu.com
## 使用 == 精确匹配
http.host == "hm.baidu.com:443"
```



### 针对协议过滤

```bash
##过滤某一个
http
## 过滤多个
http or telnet
## 排除
!http
```



## 针对端口过滤

```bash
## 精确过滤
tcp.port == 80
tcp.port == 80 || udp.port == 80
## 端口范围
tcp.port >= 80
```



### 针对长度和内容过滤

```bash
## 针对长度过滤
http.content_length >=20
## 针对内容过滤:部分匹配
http.request.uri matches "/translate" 
```

