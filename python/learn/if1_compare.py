#!/usr/bin/env python3
# -*- coding:utf-8 -*-

def compare_str():
	# --- 字符串比较
	car = 'bmw'
	print(car == 'bmw')
	car = 'audi'
	print(car == 'bmw')

	# ---字符串比较忽略大小写
	car = 'Audi'
	print(car.lower() == 'audi')
	print(car.upper() == 'AUDI')

	# --- 字符串不相等
	car = 'Audi'
	print(car != 'bmw')

pass


def compare_number():
	num = 12
	print(num == 12)
	print(num != 2)
	print(num >=12)
	pass

def compare_and_or():
	num1 = 12
	num2 = 14
	print(num1 > 12 and num2 > 12)
	print(num1 > 12 or num2 > 12)
	pass

def compare_list():
	arr= list(range(0,5)) 
	print(4 in arr)
	print(5 not in arr)
	pass


if __name__ == '__main__':
	# compare_str()
	# compare_number();
	# compare_and_or();
	compare_list()
