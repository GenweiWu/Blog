def test01():
    list1 = [11, 22, 33]
    list2 = [22, 44]
    # 交集
    result = [i for i in list1 if i in list2]
    print(result)
    # 差集removeAll
    result = [i for i in list1 if i not in list2]
    print(result)
    # 并集
    merge = list1 + list2
    print(merge)


def map_to_obj():
    # 1.map x-> x
    list1 = [11, 22, 33]
    list2 = map(lambda x: x + 100, list1)
    print(list(list2))

    # 2.map x->obj
    list2 = map(lambda x: {"id": x}, list1)
    print(list(list2))
    pass

    # 3.map两个数组
    list3 = [1, 2, 3]
    list4 = [10, 12, 14]
    result = map(lambda x, y: x + y, list3, list4)
    print(list(result))


if __name__ == '__main__':
    # test01()

    map_to_obj()
