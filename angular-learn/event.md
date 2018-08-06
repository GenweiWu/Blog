
## video事件绑定

**绑定事件，类似`onerror`要写成`on-error`**  
[==> 参考这里](https://stackoverflow.com/a/40442742/6182927)  

> js写法
```html
<video onpause="doc.videoPause(this)" onplay="doc.videoPlay(this)" onclick="doc.videoClick(this)" onerror="doc.videoError()"
    src="" width="100%" preload='auto' controls style="max-height: 600px;">
</video>
```

> angular2写法
```html
<video on-pause="videoPause(this)" on-play="videoPlay(this)" on-click="videoClick(this)" on-error="videoError()"
    src="" width="100%" preload='auto' controls style="max-height: 600px;">
</video>
```



