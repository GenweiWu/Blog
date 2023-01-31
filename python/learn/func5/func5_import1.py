#!/usr/bin/env python3
# -*- coding:utf-8 -*-

# 引入模块
import func5_common
# 引入模块+别名
import func5_common as fc

if __name__ == '__main__':
	func5_common.hello()
	func5_common.bye()

	fc.hello('fc')
	fc.bye('fc')
