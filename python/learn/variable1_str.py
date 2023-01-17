#!/usr/bin/env python3
# -*- coding: utf-8 -*-

print('hello world')

# 字符串转义
print("I'm good")
print("I'm good,and test \" end")

# 多行打印
print('''11
22
33''')

# 中文
print('中文')

### 带变量打印
message="Hello"
print(message)

# 使用%方式
print('this is %s,he is %d years old' % ('dave',13) )
# 使用f方式
radius=2.5
s=3.14*radius**2
print(f'the area of circle with radius:{radius} is {s:.2f}')

