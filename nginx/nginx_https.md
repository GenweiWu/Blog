nginx配置https遇到的问题
==
## 问题1. `ssl3_check_cert_and_algorithm:dh key too small`
这个问题是由于客户端使用的OpenSSL的版本较高（因为产品要修复openssl的一些漏洞），但服务器端的OpenSSL版本较低。
*个人理解：浏览器使用的Openssl版本高了，但是nginx作为服务器的ssl版本低。*

#### :construction: 解决方法：
server.xml中找到Default_HTTPS的Connector 节点，添加或修改ciphers参数。
```xml
<Connector port="8542" protocol="org.apache.coyote.http11.Http11NioProtocol" SSLEnabled ="true" sslProtocol ="TLS" maxThreads="150" 
  ciphers="TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,TLS_RSA_WITH_AES_128_CBC_SHA256,TLS_RSA_WITH_AES_128_CBC_SHA,TLS_RSA_WITH_AES_256_CBC_SHA256,TLS_RSA_WITH_AES_256_CBC_SHA"  .../>     
```
另参考：https://www.jamf.com/jamf-nation/articles/384/configuring-supported-ciphers-for-tomcat-https-connections

---

