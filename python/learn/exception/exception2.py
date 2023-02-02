#!/usr/bin/env python3
# -*- coding:utf-8 -*-


# 1.文件不存在 FileNotFoundError
def test_file_not_found():
    file_name = 'not_exist.txt'
    try:
        with open(file_name, encoding='utf-8') as f:
            content = f.read()
    except FileNotFoundError:
        print(f'file:{file_name} not found')


def count_word_from_file(file_name):
    try:
        with open(file_name, encoding='utf-8') as f:
            content = f.read()
    except FileNotFoundError:
        print(f'file:{file_name} not found')
        pass
    else:
        words = content.split()
        count = len(words)
        print(f'The file {file_name} contains {count} words')


if __name__ == '__main__':
    # test_file_not_found()

    # 计算书籍的字数
    file_names = ['books/book1.txt', 'books/book2.txt', 'books/not_exist.txt']
    for i in file_names:
        count_word_from_file(i)
