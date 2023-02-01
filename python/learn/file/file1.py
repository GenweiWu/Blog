#!/usr/bin/env python3
# -*- coding:utf-8 -*-

# 1.读取整个文件
def read_file():
    # 默认是只读模式打开
    with open('pi_digit.txt') as file_object:
        contents = file_object.read()
        print(f'file content:<<{contents}>>')
    pass


# 2.文件路径，这里使用相对路径
def read_file222():
    with open('text_files/test.txt') as file_object:
        contents = file_object.read()
        print(f'file contents:<<{contents}>>')
    pass


# 3.按行读取文件
def read_by_line():
    with open('pi_digit.txt') as file_object:
        for line in file_object:
            # 去除每行末尾的换行
            print(line.rstrip())
    pass


# 4.按行读取，放到列表中
def read_by_line222():
    with open('pi_digit.txt') as file_object:
        lines = file_object.readlines()

    for line in lines:
        print(f'line:{line.rstrip()}')
    pass


# 5.使用读取的内容
def read_pi():
    with open('pi_digit.txt') as file_object:
        lines = file_object.readlines()

    pi_str = ''
    for line in lines:
        pi_str = pi_str + line.strip()
    print(f'pi:{pi_str}')

    '''使用'''
    pi = float(pi_str)
    r = 5
    print(f'pi calculate:{pi * r ** 2}')

    pass


if __name__ == '__main__':
    # read_file()
    # read_file222()
    # read_by_line()
    # read_by_line222()
    read_pi()
