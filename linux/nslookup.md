

### 基本用法
`nslookup domain`

```bash
# nslookup baidu.com

Name:      baidu.com
Address 1: 39.156.66.10
Address 2: 110.242.68.66
```


### 指定dns服务器
`nslookup domain [dns server]`
```bash
# nslookup baidu.com 8.8.8.8
Server:    8.8.8.8
Address 1: 8.8.8.8 dns.google

Name:      baidu.com
Address 1: 39.156.66.10
Address 2: 110.242.68.66

# nslookup baidu.com 114.114.114.114
Server:    114.114.114.114
Address 1: 114.114.114.114 public1.114dns.com

Name:      baidu.com
Address 1: 39.156.66.10
Address 2: 110.242.68.66
```
