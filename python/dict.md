Python3 字典
==

## 判断key是否在字典中
```py
a = dict()

a['1'] = 1
print(a)
# 判断key是否在字典中
print('1' in a)
print('1' not in a)
print('2' in a)

# {'1': 1}
# True
# False
# False
```

## map中的list
```py
a = dict()

a['1'] = 1

a['2'] = ['2a']
a['2'].append('2b')
print(a)

# {'2': ['2a', '2b'], '1': 1}
```

## map的遍历
```py
a = dict()

a['1'] = 1
a['2'] = ['2a']
a['2'].append('2b')

for (k, v) in a.items():
    print("%s -> %s" % (k, v))
# 2 -> ['2a', '2b']
# 1 -> 1
```
