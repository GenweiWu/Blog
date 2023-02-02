#!/usr/bin/env python3
# -*- coding:utf-8 -*-

import unittest


class AssertTestCase(unittest.TestCase):
    """测试各种assert方法"""

    def test_assert(self):
        self.assertEqual(1, 1)
        self.assertEqual('Ok', 'ok'.title())

        self.assertNotEqual('a', 'b')

        self.assertTrue(1 == 1)
        self.assertFalse(2 > 3)

    def test_assert_list(self):
        self.assertIn(1, [2, 3, 1])
        self.assertNotIn(9, [2, 3, 1])

        self.assertListEqual([1, 2], [1, 2])
        # self.assertListEqual([1, 2], [2, 1])  # 顺序不同不相等

        # --校验元素相同，不管顺序
        self.assertCountEqual([1, 2], [1, 2])
        self.assertCountEqual([1, 2], [2, 1])

    def test_assert_dict(self):
        dict1 = {'k1': 'v1', 'k2': 'v2'}
        dict1_same = {'k1': 'v1', 'k2': 'v2'}
        dict2 = {'k2': 'v2', 'k1': 'v1'}

        self.assertTrue(dict1 == dict1_same)
        self.assertTrue(dict1 == dict2)

        self.assertEqual(dict1, dict1_same)
        self.assertEqual(dict1, dict2)

        self.assertDictEqual(dict1, dict1_same)
        self.assertDictEqual(dict1, dict2)

    def test_assert_dict_will_fail(self):
        """看起来3个方法都可以，但是后面两个提供的错误信息多一些"""
        dict1 = {'k1': 'v1', 'k2': 'v2'}
        dict2 = {'k2': 'v2', 'k1': False}

        self.assertTrue(dict1 == dict2)
        # AssertionError: False is not true

        self.assertEqual(dict1, dict2)
        '''
        AssertionError: {'k1': 'v1', 'k2': 'v2'} != {'k2': 'v2', 'k1': False}
        - {'k1': 'v1', 'k2': 'v2'}
        ?        ^^^^
        
        + {'k1': False, 'k2': 'v2'}
        ?        ^^^^^
        '''

        self.assertDictEqual(dict1, dict2)
        '''
        AssertionError: {'k1': 'v1', 'k2': 'v2'} != {'k2': 'v2', 'k1': False}
        - {'k1': 'v1', 'k2': 'v2'}
        ?        ^^^^
        
        + {'k1': False, 'k2': 'v2'}
        ?        ^^^^^
        '''


if __name__ == '__main__':
    unittest.main()
