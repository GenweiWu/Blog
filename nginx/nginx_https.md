

## 配置参考
```nginx
server {
	listen 443 ssl;
	server_name localhost;

	access_log logs/access.log;
	error_log logs/error.log;

	ssl_certificate ssl\\zzweb.crt;
	ssl_certificate_key ssl\\zzweb.key;

	ssl_session_cache shared:SSL:1m;
	ssl_session_timeout 5m;

	ssl_ciphers HIGH:!aNULL:!MD5;
	ssl_prefer_server_ciphers on;

	location / {
		proxy_pass https://102.9.2:28004$request_uri;
	}
}
```

## 问题 

#### 问题1. `ssl3_check_cert_and_algorithm:dh key too small`
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

#### 问题2：规避抓包https  

> https转http再转https  
> 本来客户端软件直接请求 https://10.2.2.2:28004，但是不好抓包。改成先请求 https://localhost:8443(客户端只能填地址,总是https的),然后转 http://localhost:8449, 然后再转https://10.2.2.2:28004  
> 抓包的时候针对中间的http+端口8449抓包 
```nginx
	# HTTPS server
    server {
        listen       8443 ssl;
        server_name  localhost;
		
		#access_log  logs/access.log main;
		error_log logs/error.log;

        ssl_certificate      ssl222/nj.crt;
        ssl_certificate_key  ssl222/nj.key;

        ssl_session_cache    shared:SSL:1m;
        ssl_session_timeout  5m;

        ssl_ciphers  HIGH:!aNULL:!MD5;
        ssl_prefer_server_ciphers  on;

        location / {
		     ## 配置localhost不行，改成127.0.0.1
		    #proxy_pass         http://loclahost:8449$request_uri; 
			proxy_pass         http://127.0.0.1:8449$request_uri; 
        }
    }
	server {
        listen       8449;
        server_name  localhost;

        location / {
            proxy_pass  https://10.2.2.2:28004$request_uri;
           
        }
    }
```

