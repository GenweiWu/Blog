#!/usr/bin/env python3
# -*- coding:utf-8 -*-

# 1.用法 try-except
def exception1():
    try:
        print(5 / 0)
    except ZeroDivisionError:
        print('You cannot divide a zero')


def calculator():
    print('please input two numbers, we will divide them.')
    print('input q to quit')

    while True:
        n1 = input('first number:')
        if n1 == 'q':
            break

        n2 = input('second number:')
        if n2 == 'q':
            break

        # 2.用法 try-expect-else-finally
        try:
            result = int(n1) / int(n2)
            # print(f'result is:{result}') #--A1
        except ZeroDivisionError:
            print('You cannot divide a zero')
        else:
            # else是不抛异常时，继续执行，就跟放在抛异常的代码后面A1一样 #--A2
            # 使用场景：当else的代码也可能抛异常，且不想被except捕获时，才放在else中；否则跟A1效果是一样的
            print(f'>>>result is:{result}')
        finally:
            print('-----')
    pass


if __name__ == '__main__':
    # exception1()
    calculator()
