#!/usr/bin/env python3
# -*- coding:utf-8 -*-


def return_simple():
	return 'OK'
	pass

def return_dict(hobby = []):
	person = {
		'name':"zhangsan",
		'age':30
	}

	if hobby:
		person['hobby'] = hobby

	return person
	pass

def return_many():
	return 1,'Hello'
	pass	


if __name__ == '__main__':
	# 1.单个返回值
	print(f'single:{return_simple()}')


	# 2.返回dict
	print(f'result_dict:{return_dict()}')
	
	h = ['run','swim']
	result = return_dict(h)
	print(f'{type(result)} result_dict:{result}')


	# 3.返回多个,其实是tuple
	a,b = return_many()
	print(f'a:{a}, b:{b}')
	c = return_many()
	print(f'{type(c)} c:{c}')





