#!/usr/bin/env python3
# -*- coding:utf-8 -*-

class Dog:
    """模拟狗"""

	# 1.类似构造函数
    def __init__(self,name,age):
        """定义属性"""
        self.name = name
        self.age = age
    
    def sit(self):
    	print(f'{self.name} is now sitting.')    


    def roll_over(self):
    	print(f'{self.name} rolled over!')    
    	pass

if __name__ == '__main__':
	myDog = Dog('wangcai', 3)
	print(f'I have a dog named:{myDog.name}, it\'s {myDog.age} years old')
	myDog.sit()
	myDog.roll_over()
