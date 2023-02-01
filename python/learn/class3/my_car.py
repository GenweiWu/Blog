#!/usr/bin/env python3
# -*- coding:utf-8 -*-

from car import Car

if __name__ == '__main__':
	my_car = Car('audi','a4',2019)	
	print(my_car.get_descriptive_name())
