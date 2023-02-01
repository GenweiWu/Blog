#!/usr/bin/env python3
# -*- coding:utf-8 -*-

## 测试标准库

from random import randint,choice



if __name__ == '__main__':
	for i in range(0,10):
		print(randint(1,6))


		player= ['Alice','Bob','Green','Yellow']
	for i in range(0,10):
		print(choice(player))
