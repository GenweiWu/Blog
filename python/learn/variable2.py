#!/usr/bin/env python3
# -*- coding: utf-8 -*-


## bool类型
def boolTest():
	print(True and True)
	print(True or False)
	print(not False)

	pass


## 数字
def digitTest():
	print(2**3)

	print('value is %.2f!' % 2.345)  # 2.35
	print('value is %.1f!' % 2.345)  # 2.3
	print('value is %f!' % 2.345)    # 2.345000
	print('value is %.0f!' % 2.345)    # 2

	print('%f' % (1/2))

	# practise2-8:得到8
	print(2+6)
	print(10-2)
	print(2*4)
	print('%.0f' % (24/3))

	# practise 
	COUNT=666
	print('my lucky number is %d' % COUNT)
	print(f'my lucky number is {COUNT}')
	pass


def practise01():
	aa='abc'
	bb=aa
	print(f'aa={aa}, bb={bb}')
	aa='XYZ'
	# 此时bb的值仍然是abc
	print(f'aa={aa}, bb={bb}')

	pass


### 输出下面的内容
# n = 123
# f = 456.789
# s1 = 'Hello, world'
# s2 = 'Hello, \'Adam\''
# s3 = r'Hello, "Bart"'
# s4 = r'''Hello,
# Lisa!'''

# Python还允许用r''表示''内部的字符串默认不转义
def practise02():
	print(123)
	print(456.789)
	s1 = 'Hello, world'
	print(s1)
	s2 = 'Hello, \'Adam\''
	print(s2)
	s3 = r'Hello, "Bart"'
	print(s3)
	s4 = r'''Hello,\'xx\'
	Lisa!'''
	print(s4)

	pass


if __name__ == '__main__':
	# boolTest()
	# digitTest()
	# practise01()
	practise02()
