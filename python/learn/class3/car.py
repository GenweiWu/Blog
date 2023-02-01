#!/usr/bin/env python3
# -*- coding:utf-8 -*-

class Car:

	def __init__(self,make,model,year):
		"""描述汽车的属性"""
		self.make = make
		self.model = model
		self.year = year
		self.odometer_reading = 0

	def get_descriptive_name(self):
		long_name =  f'{self.year} {self.make} {self.model}'
		return long_name.title()

	def read_odometer(self):
		print(f'>>> This car has {self.odometer_reading} miles on it')


	def update_odometer(self,mileage):
		if mileage >= self.odometer_reading:
			self.odometer_reading = mileage
		else:
			print(f'You can not roll back an odometer')	
		pass


class Battery:

	def __init__(self,battery_size=75):
		self.battery_size = battery_size

	def describe_battery(self):
		return f'This car has a {self.battery_size}-KWh battery'

	def get_range(self):
		if self.battery_size == 75:
			range = 260
		elif self.battery_size == 100:
			range = 315	
		
		return range


## 3.子类
class ElastricCar(Car):

	def __init__(self,make,model,year):
		"""初始化父类构造函数"""
		super().__init__(make,model,year)
		self.battery = Battery()
		pass

	def get_descriptive_name(self):
		long_name =  f'{self.year} {self.make} {self.model} [battery]:{self.battery.describe_battery()}'
		return long_name.title()
		pass		
