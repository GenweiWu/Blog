#!/usr/bin/env python3
# -*- coding:utf-8 -*-


from car import ElastricCar


if __name__ == '__main__':
	my_tesla = ElastricCar('tesla','model s',2023)
	print(my_tesla.get_descriptive_name())
	print(f'range: {my_tesla.battery.get_range()}')
