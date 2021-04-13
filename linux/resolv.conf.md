

```bash
# cat /etc/resolv.conf
search local.dev local.test
nameserver 202.96.128.86
nameserver 202.96.128.166
```

- `search` 搜索列表  
```bash
# host  -a tttt
Trying "tttt.local.dev"
Trying "tttt.local.test"
Trying "tttt"
Host tttt not found: 3(NXDOMAIN)
Received 97 bytes from 10.XX.YY.ZZ#53 in 225 ms
```

- `nameserver` 配置DNS解析服务器的IP

