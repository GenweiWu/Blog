#!/usr/bin/env python3
# -*- coding:utf-8 -*-

def input_str():
	msg = input('What is your name? ')
	print(f'Hello, {msg}')
	pass

def input_number():
	age_str = input('How old are you? ')
	age = int(age_str)
	if age >= 18:
		print(f'You are a teenager')
	elif age < 18:	
		print(f'You are a child')
	pass

if __name__ == '__main__':
	# input_str()
	input_number()
