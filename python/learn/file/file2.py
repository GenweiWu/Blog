#!/usr/bin/env python3
# -*- coding:utf-8 -*-


def write_to_file():
    """1.写入文件(清空原文件，写入文件)"""
    file_name = 'programming.txt'

    with open(file_name, 'w') as file_object:
        file_object.write("go for it")
    pass


def write_lines():
    """2.写入多行文本"""
    file_name = 'programming.txt'

    with open(file_name, 'w') as file_object:
        # 使用\n来添加换行效果
        file_object.write("I love money.\n")
        file_object.write("I love play.\n")
    pass


def append_to_file():
    """3.追加内容到文件"""
    file_name = 'programming.txt'

    with open(file_name, 'a') as file_object:
        file_object.write("\nI love money.")
        file_object.write("\nI love play.")
    pass


if __name__ == '__main__':
    # write_to_file()
    # write_lines()

    append_to_file()
