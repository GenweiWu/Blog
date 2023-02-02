#!/usr/bin/env python3
# -*- coding:utf-8 -*-

import unittest
from name_function import get_formatted_name


class NamesTestCase(unittest.TestCase):
	"""测试"""

	def test_first_last_name(self):
		actual = get_formatted_name('zhang','san')
		self.assertEqual(actual,'Zhang San')

	def test_first_middle_last_name(self):	
		actual = get_formatted_name('aa','Cc','bb')
		self.assertEqual(actual,'Aa Bb Cc')


if __name__ == '__main__':
	unittest.main()

