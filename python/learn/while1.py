#!/usr/bin/env python3
# -*- coding:utf-8 -*-

# 计算1+...+100
def while_sum():
	# --- for
	sum = 0 
	for i in list(range(0,101)):
		sum = sum + i
	print(f'sum = {sum}')		

	# --- while
	i = 1
	sum2 = 0
	while i<=100:
		sum2 = sum2 + i
		i = i + 1
	print(f'sum2 = {sum2}')

	pass


def while_input():
	QUIT = 'quit'
	message = ''

	while message != QUIT:
		message = input('input here: ')
		if message != QUIT:
			print(f'---{message}')
	pass


# abc一直循环到20个字符
def while_flag():
	
	# 基础
	print(chr(ord('a')+1))	
	print(chr(ord('a')+2))


	# --- for实现
	sb = ''
	for i in list(range(0,20)):
		j = i%3
		sb = sb + chr(ord('a')+j)
	print(f'sb={sb}, with len:{len(sb)}')	


	# --- while实现
	sb2 = ''
	x = 0
	while len(sb2) < 20 :
		y = x%3
		sb2 = sb2 + chr(ord('a')+y)
		x = x+1	
	print(f'sb2={sb2}, with len:{len(sb2)}')


	# --- while用flag实现
	sb3 = ''
	x = 0
	finish = False
	while finish == False :
		y = x%3
		sb3 = sb3 + chr(ord('a')+y)
		x = x+1	
		if len(sb3) == 20:
			finish = True
	print(f'sb3={sb3}, with len:{len(sb3)}')

	pass	

if __name__ == '__main__':
	# while_sum()
	# while_input()
	# while_flag()
