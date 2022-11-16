list
==

## 1. list合并

```
a=[1,2,3]
b=[4,5,6]
想得到集合[1,2,3,4,5,6]
```

- 这个方法不满足
```py
a=[1,2,3]
b=[4,5,6]
a.append(b)
a
[1, 2, 3, [4, 5, 6]]
```

- 两种简单方法可以实现
```py
# 方法1:a和b本身不会改变
a=[1,2,3]
b=[4,5,6]
a+b
[1, 2, 3, 4, 5, 6]

# 方法2:a自身会改变
a=[1,2,3]
b=[4,5,6]
a.extend(b)
#此时a变成<class 'list'>: [1, 2, 3, 4, 5, 6]
```

## list过滤元素
```py
a = [1, 2, 3, 4, 5]
print(a)

b = [i for i in a if i > 3]
print(b)

```
> [1, 2, 3, 4, 5]
[4, 5]

## remove all
```py
def test_remove_all():
    arr1 = [1, 2, 4]
    arr2 = [4, 1, 3]
    xx = [i for i in arr1 if i in arr2]
    print(xx)
    xx = [i for i in arr1 if i not in arr2]
    print(xx)
    pass
```
