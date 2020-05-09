

## 如何拦截https证书

- 1. 导出证书
```
Proxy -> Options -> export CA certificate -> 
-> Certificate in DER format（保存成111.der即可）
```

- 2. 安装
```
可以直接双击安装到电脑，也可以通过浏览器的证书管理安装；
注意要添加到受信任的根证书区域中
```

### 注意：
- 用新版的bursuit，旧版生成的证书+新版的chrome可能不兼容  
- 安装后不生效的话，要重启浏览器;以及卸载旧浏览器再重新安装新浏览器  
 

