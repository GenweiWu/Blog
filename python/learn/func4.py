#!/usr/bin/env python3
# -*- coding:utf-8 -*-


## 1.列表作为参数

def hello_users(users):
	for i in users:
		print(f'hello {i}')
	pass

## 2.对形参进行修改(list会被修改，简单类型不能被修改) 
def modify_list_params(users,count=0):
	users.append('end')
	count = count+10
	pass


if __name__ == '__main__':
	users = ['Alice','Bob']
	hello_users(users)


	users= ['Green']
	count = 100
	print(f'users:{users}, count:{count}')
	modify_list_params(users, count)
	print(f'users:{users}, count:{count}')


	## 3.如果不想改变list，则实参这里拷贝一份
	users= ['Dave']
	modify_list_params(users[:])
	print(f'users222:{users}')

