#!/usr/bin/env python3
# -*- coding:utf-8 -*-

if __name__ == '__main__':
	arr=[1,2,3]
	print(f'arr:{arr}')
	arr[0]=100
	print(f'arr:{arr}')

	# --- 列表转元组: tuple(arr)
	tuple1=tuple(arr)
	print(f'tuple1:{tuple1}')
	#  'tuple' object does not support item assignment
	# tuple1[0]=200

	# --- 元组:不能修改的列表
	tuple1=()
	print(tuple1)
	tuple1=(1,)
	print(tuple1)
	tuple1=(1,2)
	print(tuple1)
