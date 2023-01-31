#!/usr/bin/env python3
# -*- coding:utf-8 -*-

## 1.位置实参(positional argument)
def describe_pet(pet_type,pet_name):
	print(f'I have a {pet_type}, it\'s name is:{pet_name.title()}')
	pass

## 3.默认值
def pow(num,count=2,print_result=False):
	sum = 1 
	while count>0:
		sum = sum * num
		count = count -1

	if print_result:	
		print(f'result:{sum}')	
	
	return sum	
	pass


## 4.使用*args来实现可变参数
# 参数实际上封装成元素tuple了
def join(debug, *strs):
	if debug:
		print(f'debug:{type(strs)}')  ## tuple

	sb=''
	for i in strs:
		sb = sb + i + ','
	if sb.endswith(',')	:
		sb = sb[:-1]

	return sb	
	pass


def build_person(name, age, **others):
	print(f'{type(others)}')  ## dict

	others['name'] = name
	others['age'] = age
	print(f'info: {others}')
	pass


if __name__ == '__main__':
	# describe_pet('hamster','harry')
	# describe_pet('dog','wangcai')
	# describe_pet('wangcai','dog')  #可能出现顺序写错导致的错误


	## 2.关键字实参，即名称对，可以看做dict类型
	# describe_pet(pet_name='yoyo', pet_type='fish')  ## 顺序写错也没关系
	
	# info = {'pet_name':'wahaha', 'pet_type':'fish'}
	# describe_pet(**info) ## 也可以这样写


	## 3.默认值
	# print(pow(2))
	# print(pow(num = 2))

	# print(pow(2,3,True))

	# print(pow(2,4))
	# print(pow(2,print_result=True))  # 默认值有多个时, 设置多个实参可能需要写参数名
	# print(pow(print_result=True,num = 2)) 


	## 4.可变参数(任意数量实参) 
	# print(f"joinTest:{join(False,'aa')}")
	# print(f"joinTest:{join(True)}")
	# print(f"joinTest:{join(False,'aa','bb','cc')}")


	## 5.可变参数(任意数量关键字实参) 
	build_person('zhangsan',30,hobby=['swim'],location='green street')
