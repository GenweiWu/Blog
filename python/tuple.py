def tuple_create():
    tuple1 = (1, 2, 3)
    print(tuple1)  # (1, 2, 3)

    # 创建单个元素的元祖，要加逗号
    tuple2 = (1,)
    print(tuple2)  # (1,)
    pass


def tuple_read():
    tuple1 = (1, 2, 3)
    print(tuple1)  # (1, 2, 3)
    print(tuple1[:])  # (1, 2, 3)
    print(tuple1[1:])  # (2, 3)
    print(tuple1[1:-1])  # (2,)
    pass


def tuple_modify():
    tuple1 = (1, 2, 3)
    # tuple1[0] = 11 # tuple不支持修改

    print((1, 2, 3) + ('d',))  # (1, 2, 3, 'd')

    pass


def tuple_operator1():
    print(len((1,)))  # 1
    print((1,) + (True,))  # (1, True)
    print((False,) * 3)  # (False, False, False)

    print(3 in (1, 2, 3,))  # True
    print(33 in (1, 2, 3,))  # False

    for x in (3, 4, 5):
        print(x, end="|")  # 3|4|5|
    pass


def tuple_operator2():
    print(len((1,)))  # 1

    tuple1 = (1, 2, 3)
    print(max(tuple1))  # 3
    print(min(tuple1))  # 1

    list1 = ['z', 'd', True]
    print("list:", list1)  # ['z', 'd', True]
    print("tuple:", tuple(list1))  # ('z', 'd', True)
    pass


if __name__ == '__main__':
    # tuple_create()
    # tuple_read()
    # tuple_modify()
    # tuple_operator1()
    tuple_operator2()

    pass
