
## css中的url
参考：https://developer.mozilla.org/en-US/docs/Web/CSS/url  
url可以是绝对路径，也可以是相对路径(相对是相对css文件的，不是相对页面的)

### demo
例如这种的在css中加载图片资源
```css
.new{	
	background: url(new.png)  0px 0px  no-repeat;
}
```

假如这个css的访问路径是  
`http://localhost:8080/xxx/yyy.css`  
则图片对应路径为  
`http://localhost:8080/xxx/new.png`

假如这个css的访问路径是rest请求：    
`http://localhost:8080/xxx/yyy?path=111.css`  
则图片对应路径为  
`http://localhost:8080/xxx/new.png`  

