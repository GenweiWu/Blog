#!/usr/bin/env python3
# -*- coding:utf-8 -*-

import json


def test_json_write():
    with open('test_list.json', mode='w') as f:
        json.dump([1, 2, 3], f)

    with open('test_dict.json', mode='w') as f:
        json.dump({'dict_key': 'dict_value'}, f)
    pass


def test_json_read():
    with open('test_list.json') as f:
        nums = json.load(f)
        print(f'nums:{nums}')

    with open('test_dict.json') as f:
        dict_data = json.load(f)
        print(f'dict:{dict_data}')

    pass


if __name__ == '__main__':
    # test_json_write()
    test_json_read()
