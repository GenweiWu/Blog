#!/usr/bin/env python3
# -*- coding:utf-8 -*-


def get_formatted_name(first_name, last_name, middle_name=''):
    """生成格式化的名称"""
    if middle_name == '':
        full_name = f'{first_name} {last_name}'
    else:
        full_name = f'{first_name} {middle_name} {last_name}'

    return full_name.title()


if __name__ == '__main__':
    print(get_formatted_name('li', 'si'))
