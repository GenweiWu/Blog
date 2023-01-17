#!/usr/bin/env python3
# -*- coding:utf-8 -*-

## --- 遍历list
def list_for():
	arr =['alice','bob','dave']
	for i in arr:
		print(f'this is:{i}')
		print(f'this is:{i.title()} \n')
	print("after all")	

	pass


def range_test():
	## --- range(a,b) 等于 [a,b) 
	for i in range(1,4):
		print(i)

	# --- range(a) 等于 [0,a)
	for i in range(5):
		print(f'-{i}')	

	# -- range(start,stop,step)	 等于 [start,stop),间隔step
	for i in range(1,10,3):
		print(f'--{i}')			

	for i in range(1,6):
		print(' '*(6-i) + ' *'*i)	

	pass

## 数值列表
def list_number():
	# --- 从range创建list
	arr3=list(range(1,5))
	print(f'-{arr3}')

	arr=[1,2,3,4]
	# --- 列表解析：list的map
	arr2=[i*2 for i in arr]
	print(f'{arr2}')

	# --- 统计
	print(min(arr2))
	print(max(arr2))
	print(sum(arr2))

	pass

if __name__ == '__main__':
	# list_for()
	# range_test()
	list_number()
