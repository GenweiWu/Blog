批量上传
==

参考 `org.springframework.web.multipart.MultipartRequest`
```java
 MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
 //关键代码，key对应的list即为批量上传的文件 key->v1,v2
 MultiValueMap<String, MultipartFile> multiFileMap = multiRequest.getMultiFileMap();
 
 //这个方法就无法得到批量上传的文件 key->v
 multiRequest.getFileMap()
```        
