#!/usr/bin/env python3
# -*- coding:utf-8 -*-

# 在列表之间移动元素
# 将每个元素大写后，放到元素后
def change_list():
	arr = ['alice','bob','green']
	newArr = []
	while arr:
		name = arr.pop()
		newName = name.upper()
		newArr.append(name)
		newArr.append(newName)
	
	print(f'name:{arr}')
	print(f'name:{newArr}')

	pass

# 删除特定值的所有列表元素
def list_remove():
	arr= [1,2,1,3,1] 

	arr.remove(1)
	print(f'arr after delete:{arr}')

	# remove all
	arr= [1,2,1] 
	while 1 in arr:
		arr.remove(1)
	print(f'arr after delete:{arr}')

	pass


def list_from_input():

	arr = []
	need_continue = True

	while need_continue:
		name = input("what's your name?")
		arr.append(name)
		
		continue_input = input("do you need to continue? (yes/no)")
		if continue_input != 'yes':
			need_continue = False	

	print(f'all name are:{arr}')
			
	pass


if __name__ == '__main__':
	# change_list()
	# list_remove()
	list_from_input()
