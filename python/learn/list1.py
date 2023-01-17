#!/usr/bin/env python3
# -*- coding:utf-8 -*-

def list_add():
	# 初始赋值
	arr1=[1,True,'Dave',2.5]
	print(arr1)

	# 初始为空
	arr2=[]
	# append方法
	arr2.append(1)
	arr2.append(2)
	arr2.append(3)
	print(arr2)
	# insert方法
	arr2.insert(1,1.5)
	print(arr2)

	pass

def list_read():
	arr3=[1,2,3,4]
	print(arr3[0])
	print(arr3[1])
	print(arr3[2])
	print(arr3[3])
	
	print(arr3[-1])
	print(arr3[-2])
	print(arr3[-3])
	print(arr3[-4])

	arr4=[1,2,['3A','3B'],4]
	print(arr4[0])
	print(arr4[1])
	print(arr4[2][0])
	print(arr4[2][1])
	print(arr4[3])

	pass


def list_delete():
	arr5=[1,2,3,4,5]
	print(arr5)

	## del直接删除，无返回值
	del arr5[1]
	print(arr5)

	## pop删除最后一个,且有返回值
	poped=arr5.pop();
	print(f"poped:{poped}")
	print(arr5)

	## pop(_num)删除指定位置
	arr5.pop(-3)
	print(arr5) #3,4

	## 根据值删除
	arr6=[1,2,3,4,3]
	print(f'remove test:{arr6}')
	arr6.remove(1)
	print(f'remove test:{arr6}')
	# 重复的则只删除一个
	arr6.remove(3)
	print(f'remove test:{arr6}')
	# 不存在会报错
	# arr6.remove(10) # ValueError: list.remove(x): x not in list

	pass

def list_modify():
	arr5=[1,2,3]
	print(arr5)
	arr5[-1]=True
	print(arr5)
	arr5[0] = arr5[0]+10
	print(arr5)
	pass	


if __name__ == '__main__':
	# list_add()
	# list_read()
	list_delete()
	# list_modify()
