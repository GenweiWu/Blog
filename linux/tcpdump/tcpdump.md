tcpdump 抓包
--
##  入门
#### 1. 抓包并写入文件
`
tcpdump src host 127.0.0.1 and dst host 10.10.10.120 and port 443  -c 60 -w ./target.cap
`
> -c 60: 只抓取60个包  
> -w ./target.cap: 保存成cap文件，方便用ethereal(即wireshark)分析

### 2. 抓包直到你终止(Ctrl+C)
`tcpdump host 1.2.3.4 -w ./target.dump`
> 不过这时候看不到内容,不好停止,可以同时开两个窗口,一边看,一边写入文件

## 总结
TODO


## 参考
- https://my.oschina.net/xianggao/blog/678644
