
# 基本命令

>  检查防火墙状态。输出应该是 running 或者 not running
```CMD
firewall-cmd --state
```

> 得到默认区域
```CMD
 firewall-cmd --get-default-zone
```

> 列出指定区域的规则信息
```CMD
firewall-cmd --zone=public --list-all
```

> 添加指定端口;分别对应`运行时规则集` 和 `持久规则集`
```CMD
sudo firewall-cmd --zone=public --add-port=12345/tcp --permanent
sudo firewall-cmd --zone=public --remove-port=12345/tcp --permanent
```

> 重新加载运行时规则集
```CMD
firewall-cmd --reload
```


## 参考
- https://www.cnblogs.com/yangxunwu1992/p/6091422.html
- https://linux.cn/article-8098-1.html
