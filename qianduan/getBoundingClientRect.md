

## 获取相对视窗的位置信息
```ts
_el.getBoundingClientRect()
```
![image](https://user-images.githubusercontent.com/16630659/53393294-f6eca800-39d6-11e9-8862-b5b40d42e355.png)

```ts
element.getBoundingClientRect().bottom;
```

## 如果需要的不是相对视图，而是类似于absolute position
https://stackoverflow.com/a/28222246
```ts
function getOffset(el) {
  const rect = el.getBoundingClientRect();
  return {
    left: rect.left + window.scrollX,
    top: rect.top + window.scrollY
  };
}
```

## [含义](http://www.webhek.com/post/getclientrects-getboundingclientrect.html)
![image](https://user-images.githubusercontent.com/16630659/53394088-1c7ab100-39d9-11e9-85eb-46f383842352.png)
![image](https://user-images.githubusercontent.com/16630659/53394108-2997a000-39d9-11e9-8d58-4ed1aba21bf6.png)
