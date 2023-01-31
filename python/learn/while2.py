#!/usr/bin/env python3
# -*- coding:utf-8 -*-


def while_break():
	# 找出所有重复的数字
	set1 = set()
	for i in [1,2,3,4,5,2,10,5]:
		if i not in set1:
			set1.add(i)
		else:
			print(f'duplicate found:{i}')	
	
	# 找出第一个重复的数字
	set1 = set()
	for i in [1,2,3,4,5,2,10,5]:
		if i not in set1:
			set1.add(i)
		else:
			print(f'---duplicate found:{i}')	
			break

	# 找出最后一个重复的数字
	set1 = set()
	arr = [1,2,3,4,5,2,10,5]
	arr.reverse()
	for i in arr:
		if i not in set1:
			set1.add(i)
		else:
			print(f'---duplicate found:{i}')	
			break		
	pass		



def while_continue():
	# 判断1~10中的偶数
	n=0
	while n<10:
		n = n + 1
		if n%2!=0:
			continue
		print(f'{n}')	
	pass



if __name__ == '__main__':
	# while_break()
	while_continue()
