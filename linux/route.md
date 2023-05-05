
### 查看路由
```
route -n
```

> example
```bash
[root@localhost ~]# route
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
192.168.120.0   *               255.255.255.0   U     0      0        0 eth0
e192.168.0.0     192.168.120.1   255.255.0.0     UG    0      0        0 eth0
10.0.0.0        192.168.120.1   255.0.0.0       UG    0      0        0 eth0
default         192.168.120.240 0.0.0.0         UG    0      0        0 eth0
[root@localhost ~]# route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
192.168.120.0   0.0.0.0         255.255.255.0   U     0      0        0 eth0
192.168.0.0     192.168.120.1   255.255.0.0     UG    0      0        0 eth0
10.0.0.0        192.168.120.1   255.0.0.0       UG    0      0        0 eth0
0.0.0.0         192.168.120.240 0.0.0.0         UG    0      0        0 eth0
```

### 添加路由

```bash
# 例如路由：
172.168.8.0     0.0.0.0         255.255.255.0   U     0      0        0 eth_MANA.810

## 添加
route add -net 172.168.8.0 netmask 255.255.255.0 gw 172.168.10.254 dev eth_MANA.810
```


### 删除路由
```bash
route del -net 172.168.8.0 netmask 255.255.255.0
```
