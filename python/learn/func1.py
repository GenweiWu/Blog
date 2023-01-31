#!/usr/bin/env python3
# -*- coding:utf-8 -*-

# 无参数无返回
def hello():
	print('Hello World!')
	pass

# 带参数
# 默认返回值是None
def hello_user(user):
	print(f'Hello:{user}')
	pass


if __name__ == '__main__':
	hello()
	result = hello_user('Dave')
	print(f'result:{result}')
