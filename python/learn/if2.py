#!/usr/bin/env python3
# -*- coding:utf-8 -*-

def if_elif_else():
	# --- if, elif , else

	for age in [1,15,45,70]:
		if age < 4:
			price = 0
		elif age < 18:
			price = 25
		elif age < 65:
			price = 40
		else:
			price = 20
		print(f'age of:{age} is price:{price}')	
	pass


def if_list():
	hobbies = ['run','swim','football']	
	for i in hobbies:
		if i == 'swim':
			print(f'Sorry, closed')
		else:
			print(f'hobby:{i}')	
	pass


def if_list_empty():
	# --- 判断不是空列表	
	for arr in [[],[1,2]]:
		if not arr:
			print('empty list')
		else:
			print(f'arr: {arr}')	

	# --- 判断是空列表
	for arr in [[],[1,2]]:
		if arr:
			print(f'arr: {arr}')	
		else:
			print('empty list')
	pass	


if __name__ == '__main__':
	# if_elif_else()

	# if_list()

	if_list_empty()
