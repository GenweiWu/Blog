文件下载导致内存飙升
==


## 方法1：问题代码

```java
@GetMapping("/downloadFile")
public ResponseEntity<byte[]> downloadToolFile(@RequestParam("path") String fileId)
{
	final String filePath = "...";
	String fileName = "...";

	//准备下载
	if (StringUtil.isEmpty(filePath))
	{
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	//开始下载
	InputStream in = null;
	try
	{
		in = new FileInputStream(filePath);
		HttpHeaders headers = new HttpHeaders();
		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		headers.setContentDispositionFormData("attachment", fileName);
		//headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		byte[] fileBytes = FileUtil.getBytes(in);

		return new ResponseEntity<>(fileBytes, headers, HttpStatus.CREATED);
	}
	catch (Exception e)
	{
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	finally
	{
		IOUtils.closeQuietly(in);
	}
}
```

## 方法2：优化后的代码

```java
@GetMapping("/downloadFile")
public void downloadToolFile(@RequestParam("path") String fileId, HttpServletResponse response)
{
	final String filePath = "...";
	String fileName = "...";

	//准备下载
	if (StringUtil.isEmpty(filePath))
	{
		response.setStatus(HttpStatus.FORBIDDEN.value());
		return;
	}

	//开始下载
	InputStream in = null;
	try
	{

		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		response.setHeader("attachment", fileName);
		response.setHeader("Content-Disposition", "attachment");

		OutputStream outputStream = response.getOutputStream();
		in = new FileInputStream(filePath);
		IOUtils.copy(in, outputStream);

	}
	catch (Exception e)
	{
		response.setStatus(HttpStatus.FORBIDDEN.value());
	}
	finally
	{
		IOUtils.closeQuietly(in);
	}
}
```

## 总结
- 避免将文件所有字节读取到内存中，可能导致内存溢出。  
- 上面的两种写法，下载小文件都没问题。区别在于：
  - 方法1会在一开始卡顿下，再开始下载。而且一开始就知道文件大小，展示下载速度。
  - 方法2会直接开始下载，但是不展示下载速度，一开始也不知道文件大小。

- 性能对比(测试下载600M文件)
  - 方法1 内存从0.78G--> 2.37G,且直到下次GC内存才会下降。
  - 方法2 内存从600M-->800M
  
