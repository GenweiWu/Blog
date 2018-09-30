
## mouse事件对应关系
- mouseover --> mouseout
- mouseenter --> mouseleave

## 例子

#### 在线演示
[==> 在线演示](https://jsfiddle.net/GenweiWu/0c5qk1t8/)  


#### 效果
> (说明：`div2`是`div1`的子元素)  
![](./img/mouseover.png)

```
//(1)
2018/9/30 上午10:16:55: div1 ==> mouseover
2018/9/30 上午10:16:55: div1 ==> mouseenter

//(2)
2018/9/30 上午10:17:02: div1 ==> mouseout
2018/9/30 上午10:17:02: div2 ==> mouseover
2018/9/30 上午10:17:02: div1 ==> mouseover
2018/9/30 上午10:17:02: div2 ==> mouseenter

//(3)
2018/9/30 上午10:17:11: div2 ==> mouseout
2018/9/30 上午10:17:11: div1 ==> mouseout
2018/9/30 上午10:17:11: div2 ==> mouseleave
2018/9/30 上午10:17:11: div1 ==> mouseover

//(4)
2018/9/30 上午10:17:27: div1 ==> mouseout
2018/9/30 上午10:17:27: div1 ==> mouseleave
```

#### 解说
1. `mouseover`和`mouseout`会进行冒泡，而`mouseenter`和`mouseleave`不会冒泡  
    进入`div1`和进入`子元素div2`时，都会触发`div1`的`mouseover`，因为进入`子元素div2`时,`div2`的mouseover事件会冒泡给父元素`div1`  
  

