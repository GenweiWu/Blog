#!/usr/bin/env python3
# -*- coding:utf-8 -*-

def set_create():
	s = set([])
	print(s)

	s = set([1,2])
	print(s)

	# -- dict也是类似的{}
	s= {3,4}
	print(s)

	# --- 即{}认为是dict而不是set
	dict1 = {}
	print(type(dict1)) # <class 'dict'>

	# ---set中元素是不重复的
	s = set([1,2,3,2,1])
	print(s)
	pass


def set_add():
	# --- add
	s = set()
	s.add('ABC')
	print(s)
	pass


def set_delete():
	# --- remove(key)
	s = set([1,2,'ABC'])
	s.remove('ABC')
	print(s)
	pass


def set_read():
	# --- 无法通过s['key']的方式来读取
	s = set([1,2,'ABC'])
	# TypeError: 'set' object is not subscriptable
	# print(s['ABC'])
	pass

def set_merge():
	s1 = set('spam')
	s2 = set(['h','a','m'])
	print(f's1:{s1}, s2:{s2}')

	# 交集
	print(f'- {s1&s2}')

	# 并集
	print(f'-- {s1|s2}')

	# 差集
	print(f'--- {s1-s2}')
	print(f'--- {s2-s1}')


	pass

if __name__ == '__main__':
	# set_create()
	# set_add()
	# set_delete()
	# set_read()
	set_merge()
	
