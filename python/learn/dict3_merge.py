#!/usr/bin/env python3
# -*- coding:utf-8 -*-


def merge_dict():
    dict1 = {'a': 1, 'b': 22}
    dict2 = {'b': 333, 'd': 4444}

    # --使用update方法，会改变dict1本身
    # --如果有重复key,则dict2会覆盖dict1
    dict1.update(dict2)
    print(f'dict1:{dict1}')
    print(f'dict2:{dict2}')

    # 测试2
    dict1 = {'a': 1, 'b': 22}
    dict2 = {'b': 333, 'd': 4444}
    # --此时dict1会覆盖dict2的相同元素
    dict2.update(dict1)
    print(f'dict1:{dict1}')
    print(f'dict2:{dict2}')

    pass


def merge_dict2():
    dict1 = {'a': 1, 'b': 22}
    dict2 = {'b': 333, 'd': 4444}

    # --此时dict2会覆盖dict1中的相同元素
    # --dict1，dict2本身不会改变
    merge = {**dict1, **dict2}
    print(f'dict1:{dict1}')
    print(f'dict2:{dict2}')
    print(f'merge:{merge}')  # {'a': 1, 'b': 333, 'd': 4444}

    # --此时dict1会覆盖dict2中的相同元素
    merge = {**dict2, **dict1}
    print(f'dict1:{dict1}')
    print(f'dict2:{dict2}')
    print(f'merge:{merge}')  # {'b': 22, 'd': 4444, 'a': 1}

    pass


if __name__ == '__main__':
    # merge_dict()
    merge_dict2()
