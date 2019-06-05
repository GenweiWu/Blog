## 基础
![image](https://user-images.githubusercontent.com/16630659/35807167-ebc93492-0abc-11e8-8f84-1a552cafa7ff.png)



## animation vs transition

https://www.kirupa.com/html5/css3_animations_vs_transitions.htm
> If what I want requires the flexibility provided by having multiple keyframes and easy looping, then I go with an animation.
If I am looking for a simple from/to animation, I go with a transition.
If I want to manipulate the property values that I wish to animate using JavaScript, I go with a transition.




## pre
```css
pre{
  word-break: break-all;
  word-wrap: break-word;
}
```

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

