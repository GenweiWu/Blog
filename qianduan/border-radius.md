

## 1. 多种表示方法

### 1.1 缩写

> 1个属性
```css
border-radius:2em;

is equivalent to:

border-top-left-radius:2em;
border-top-right-radius:2em;
border-bottom-right-radius:2em;
border-bottom-left-radius:2em;
```

> 2个属性
```css
border-radius: 10px 50px;

is equivalent to:

border-top-left-radius:10px;
border-top-right-radius:50px;
border-bottom-right-radius:10px;
border-bottom-left-radius:50px;
```

> 3个属性
```css
border-radius: 1px 10px 60px;

is equivalent to:

border-top-left-radius:1px;
border-top-right-radius:10px;
border-bottom-right-radius:60px;
border-bottom-left-radius:10px;
```

### 1.2百分号表示法
```css
border-radius: 10% 30% 50% 70%;
```

### 1.3 圆形(一个半径)/椭圆()

> 椭圆表示
```css
border-top-right-radius: 58px 36px;

//长轴、短轴
```

> /表示长轴/短轴
```css
border-radius: 2em 1em 4em / 0.5em 3em;

is equivalent to:

border-top-left-radius: 2em 0.5em;
border-top-right-radius: 1em 3em;
border-bottom-right-radius: 4em 0.5em;
border-bottom-left-radius: 1em 3em;
```




## FAQ
### 1. 父div设置border-radius，如何让子元素遵从

> https://stackoverflow.com/a/3724210  
设置父元素 `overflow:hidden`
