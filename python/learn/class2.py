#!/usr/bin/env python3
# -*- coding:utf-8 -*-

class Car:

	def __init__(self,make,model,year):
		"""描述汽车的属性"""
		self.make = make
		self.model = model
		self.year = year
		# 1. 设置默认值
		self.odometer_reading = 0

	def get_descriptive_name(self):
		long_name =  f'{self.year} {self.make} {self.model}'
		return long_name.title()

	def read_odometer(self):
		print(f'>>> This car has {self.odometer_reading} miles on it')


	def update_odometer(self,mileage):
		"""通过set方法可以增加校验逻辑"""
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
		## 3.1调用父类构造函数
		super().__init__(make,model,year)
		# 3.3类作为属性
		self.battery = Battery()
		pass

	# def describe_battery(self):
	# 	print(f'This car has a {self.battery_size}-kWh battery')
	# 	pass

    ## 3.2 override父类方法
	def get_descriptive_name(self):
		long_name =  f'{self.year} {self.make} {self.model} [battery]:{self.battery.describe_battery()}'
		return long_name.title()
		pass	



if __name__ == '__main__':
	my_car = Car('audi','a4',2019)	
	print(my_car.get_descriptive_name())

	# 2.1 修改属性--直接访问属性
	my_car.odometer_reading = 66
	my_car.read_odometer()

	# 2.2 修改属性--通过set方法
	my_car.update_odometer(10)
	my_car.read_odometer()
	my_car.update_odometer(200)
	my_car.read_odometer()


	##测试子类
	my_tesla = ElastricCar('tesla','model s',2023)
	print(my_tesla.get_descriptive_name())
	print(f'range: {my_tesla.battery.get_range()}')
