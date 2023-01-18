#!/usr/bin/env python3
# -*- coding:utf-8 -*-


def dict_create():
	# --- 创建方式1: dict1 = dict()
	dict1 = dict()
	print(dict1)

	# --- 创建方式2: dict2 = {}
	dict2 = {}
	print(dict2)

	dict3={'name':'dave','age':12}
	print(dict3)

	# 上面的是对象类型的，还有类似map的
	dict4={
		'1':1,
		'2':2
	}
	print(dict4)
	pass


def dict_read():
	dict1={'name':'dave','age':12}
	# -- 读
	print(dict1['name'])
	print(dict1['age'])
	# print(dict1['hobby'])  ## 不存在会报错：KeyError: 'hobby'

	# --- get(),类似于getOrDefault
	print(f"get: {dict1.get('name')}")
	print(dict1.get('age'))
	print(dict1.get('hobby','no vaue here'))
	print(dict1.get('hobby','...'))

	# --- 判断是否key存在
	print('name' in dict1)
	print('age' in dict1)
	print('hobby' in dict1)
	pass


def dict_add():
	dict1 = {'hobby':'swim'}
	print(dict1)

	# --- add
	dict1['name'] = 'zhangsan'.title()
	dict1['age'] = 30
	print(dict1)

	pass

def dict_delete():
	dict1={'name':'dave','age':12}
	print(dict1)

	# --- delete
	del dict1['name']
	print(dict1)

	# del dict1['name'] #KeyError: 'name'

	# --- pop
	dict1={'name':'dave','age':12}
	print(dict1)
	dict1.pop('age')
	print(dict1)

	pass

def dict_modify():
	dict1 = {'hobby':'swim','age': 20}
	print(dict1)

	# --- modify
	dict1['hobby'] = 'football'
	dict1['age'] = dict1['age']+1
	print(dict1)

	pass


def dict_for():
	dict1 = {'hobby':'swim','age': 20}

	# --- items
	for k,v in dict1.items():
		print(f'{k}-->{v}')

	# --- keys
	for k in dict1.keys():
		v = dict1.get(k)
		print(f'{k}-->{v}')

	# --- values
	for v in dict1.values():
		print(f'{v}')

	# --- 默认就是keys
	for k in dict1:
		print(f'default: {k}-->{v}')

	pass

if __name__ == '__main__':
	# dict_create();
	# dict_read();
	# dict_add();
	# dict_delete()
	# dict_modify()
	dict_for()

	
