#!/usr/bin/env python3
# -*- coding:utf-8 -*-
import functools


def list_sort():
    arr = [10, 1, 5, 2]
    print(f'{arr}')

    # ----- 使用sort进行排序，会修改原list;默认升序
    arr.sort()
    print(f'{arr}')
    arr.sort(reverse=True)
    print(f'{arr}')

    # ----- sorted不会修改原list,而是返回排序后的列表
    arr = [10, 1, 5, 2]
    sorted_arr = sorted(arr)
    print(f'sored arr:{sorted_arr}')
    print(f'source arr:{arr}')

    sorted_arr = sorted(arr, reverse=True)
    print(f'sored arr:{sorted_arr}')
    print(f'source arr:{arr}')

    # --自定义排序
    # V标识vip,N标识普通用户
    def _compare(c1, c2) -> int:
        prefix1 = c1[:1]
        prefix2 = c2[:1]
        if prefix1 == prefix2:
            return int(c1[1:]) - int(c2[1:])
        else:
            return -1 if prefix1 == 'V' else 1
        pass

    arr = ['V21', 'S11', 'V10', 'S10', 'S02', 'V99']
    print(f'custom sort before:{arr}')
    arr.sort(key=functools.cmp_to_key(_compare))
    print(f'custom sort before:{arr}')

    pass


def list_reverse():
    arr = [2, 40, 4, 1]
    print(arr)

    # --reverse会修改原列表
    arr.reverse()
    print(arr)
    pass


def list_len():
    print(len([]))
    print(len([1, 2]))
    print(len([1, [2, 3]]))
    pass


if __name__ == '__main__':
    list_sort()
# list_reverse()
# list_len()
