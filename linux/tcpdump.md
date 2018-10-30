tcpdump抓包
--
##  入门
1、抓包  
`
tcpdump src host 127.0.0.1 and dst host 10.10.10.120 and port 443  -c 60 -w ./target.cap
`
> -c 60: 只抓取60个包  
> -w ./target.cap: 保存成cap文件，方便用ethereal(即wireshark)分析

## 总结
TODO


## 参考
－　https://my.oschina.net/xianggao/blog/678644
