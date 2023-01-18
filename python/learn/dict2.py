#!/usr/bin/env python3
# -*- coding:utf-8 -*-

def list_dict():
	stu1 = {'ID':1,'name':'zhangsan'}
	stu2 = {'ID':2,'name':'lisi'}
	stu3 = {'ID':3,'name':'shenxian'}

	list1 = [stu1,stu2,stu3]

	for i in list1:
		print(f'{i}')

	for i in list1[:1]:
		print(f'top:{i}')

	pass

def dict_list():
	pizza = {
		'crust':'thick',
		'topings':['mushrooms', 'extrl cheese']
	}

	print(f"You ordered a {pizza['crust']}-crust "
		"with the following topings:")
	for toping in pizza['topings']:
		print(f"\t{toping}")
	pass

def dict_dict():
	users = {
		'zhangsan':{
		   'name':'zhangsan',
		   'age':30,
		   'hobby':'run'
		},
		'lisi':{
		   'name':'lisi',
		   'age':23,
		   'hobby':'sing'
		},
	}


	for k,v in users.items():
		print(f'UserName:{k}')

		name = v['name'];
		age = v['age'];
		hobby = v['hobby'];
		print(f'\t name:{name}')
		print(f'\t age:{age}')
		print(f'\t hobby:{hobby}')
	pass


if __name__ == '__main__':
	# list_dict()
	# dict_list()
	dict_dict()
