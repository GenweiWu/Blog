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

## 按照key排序
```py
a = dict()

# 1、正常打印是乱的
a['2018-04-01'] = 'aaa'
a['2018-02-01'] = 'bb'
a['2018-01-01'] = 'cc'
for (k, v) in a.items():
    print("%s ==> %s" % (k, v))
# 2018-02-01 ==> bb
# 2018-01-01 ==> cc
# 2018-04-01 ==> aaa

# 2、升序排序,默认
sorted_key = sorted(a)
for key in sorted_key:
    print("%s ==> %s" % (key, a[key]))
# 2018-01-01 ==> cc
# 2018-02-01 ==> bb
# 2018-04-01 ==> aaa

# 3、降序排序，需要指定
sorted_key = sorted(a, reverse=True)
for key in sorted_key:
    print("%s ==> %s" % (key, a[key]))
# 2018-04-01 ==> aaa
# 2018-02-01 ==> bb
# 2018-01-01 ==> cc
```

## 根据value进行过滤
```py
simple = dict()

# 1、简单类型过滤
simple['2018-04-01'] = 11
simple['2018-02-01'] = 22
simple['2018-01-01'] = 33
print(simple)

simple_filter = {k: v for k, v in simple.items() if v == 22}
print(simple_filter)

# 复杂的
obj = dict()
obj['2018-04-01'] = {"name": "aa"}
obj['2018-02-01'] = {"name": 'bb'}
obj['2018-01-01'] = {"name": 'cc'}
print(obj)

obj_filter = {k: v for k, v in obj.items() if v['name'] == 'cc'}
print(obj_filter)
```

> {'2018-02-01': 22, '2018-01-01': 33, '2018-04-01': 11}
{'2018-02-01': 22}
{'2018-02-01': {'name': 'bb'}, '2018-01-01': {'name': 'cc'}, '2018-04-01': {'name': 'aa'}}
{'2018-01-01': {'name': 'cc'}}

## 修改Value的内容
```py
a = dict()

a['key1'] = {"name": "zhangsan"}
a['key2'] = {"name": "list"}
print(a)

for (k, v) in a.items():
    v['name'] = v['name'].upper()
print(a)

# {'key2': {'name': 'list'}, 'key1': {'name': 'zhangsan'}}
# {'key2': {'name': 'LIST'}, 'key1': {'name': 'ZHANGSAN'}}
```

