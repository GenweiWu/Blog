#!/usr/bin/env python3
# -*- coding:utf-8 -*-


def list_slice():
	# --- arr[start:stop] 等于 [start,stop)
	# --- arr[:stop] 等于 [0,stop)
	# --- arr[start:] 等于 [start,)
	# --- arr[:] 等于 复制

	arr=[1,3,5,7,9]
	print(arr)
	print(arr[1:3])  #[3, 5]
	print(arr[:4])   #[1, 3, 5, 7]
	print(arr[2:])   #[5, 7, 9]
	print(arr[-3:])  #[5, 7, 9]

	pass

def list_slice_for():
	arr=list(range(0,11))
	print(arr)

	# -- 遍历切片数据
	for i in arr[:3]:
		print(f'-{i}')

	pass


def list_copy():
	arr=[2,4,6]
	print(arr)

	# --- 通过arr[:]对arr进行深复制
	arr2=arr[:]
	print(f'arr2:{arr2}')
	arr.append(10)
	arr2.append(100)
	print(f'arr:{arr}, arr2:{arr2}')

	# 这种不是复制
	arr=[2,4,6]
	arr2=arr
	arr.append(10)
	arr2.append(100)
	print(f'arr:{arr}, arr2:{arr2}')

	pass


if __name__ == '__main__':
	# 切片
	# list_slice()
	# list_slice_for()
	list_copy()
