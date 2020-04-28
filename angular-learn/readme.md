
## 官方资料
https://angular.cn/guide/quickstart




## angular中css的使用

### 1、css中引用图片资源，建议用绝对路径(错，是从根目录开始的相对路径)

[Maybe the easiest workaround is to insert the full path in the css](https://stackoverflow.com/a/35744829/6182927)
```css
/*(1)相对路径，不好维护*/   
.sample { background-image: url(../img/bg.jpg); }

/*(2)部署后有问题*/
.sample { background-image: url(/assets/plugins/test/img/bg.jpg); }

/*(3)本地编译报错*/
.sample { background-image: url(./assets/plugins/test/img/bg.jpg); }
```
#### - 问题
> index.html
```html
    <!-- <base href="/test-service/"> -->
    <base href="/">
```
由于index.html中的开发时和部署后的href的差别，如果写成(2)的绝对路径是不行,在环境上不会自动加上`/test-service`，会有问题

#### - 结论
css使用相对路径(1)
```css
.title-icon {
  background-image: url('../../../assets/imgs/hi_icon.png') -79px -209px no-repeat;
  width: 50px;
  height: 28px;
  float: left;
}
```

html使用绝对路径
```html
<div>
<img src="assets/imgs/111.png>
</div>
```

ts中绝对路径
```ts
logoPath:any = this.domSanitizer.bypassSecurityTrustResourceUrl("assets/imgs/system_profile_pic/system_profile_pic_big_8.png");
```


