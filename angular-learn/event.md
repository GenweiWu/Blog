
## video事件绑定

#### 1、总结
**绑定事件，类似`onerror`要写成`on-error`**  

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

#### 2、参考
- https://stackoverflow.com/a/40442742/6182927
- https://stackoverflow.com/questions/40360174/playing-html-5-video-from-angular-2-typescript



