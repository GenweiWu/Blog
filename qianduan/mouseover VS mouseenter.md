
## mouse事件对应关系
- mouseover --> mouseout
- mouseenter --> mouseleave

## 例子

#### 在线演示
[==> 在线演示](https://jsfiddle.net/GenweiWu/0c5qk1t8/)

#### 效果
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


