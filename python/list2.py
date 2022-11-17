from operator import itemgetter, attrgetter
import functools


def read():
    ll = [0, 1, 2, 3, 4]
    print(ll)  # [0, 1, 2, 3, 4]
    print(ll[:])  # [0, 1, 2, 3, 4]
    print(ll[:-1])  # [0, 1, 2, 3]
    print(ll[1:])  # [1, 2, 3, 4]
    # [x,y)包括头，不包括尾
    print(ll[1:-1])  # [1, 2, 3]
    print(ll[1:1])  # []


def modify():
    ll = [0, 1, 2, 3, 4]
    ll.append(5)
    print(ll)  # [0, 1, 2, 3, 4, 5]

    # 指定位置插入
    ll.insert(2, 1.5)
    print(ll)  # [0, 1, 1.5, 2, 3, 4, 5]
    pass


def delete():
    ll = [0, 1, 2, 3, 4]
    print(ll)  # [0, 1, 2, 3, 4]
    del ll[2]
    print(ll)  # [0, 1, 3, 4]
    pass


# 列表操作符
def list_operation():
    print(len([1, 2, 4]))  # 3
    print([1, 2, 3] + [4, 5, 6])  # [1, 2, 3, 4, 5, 6]
    print([1, 2] * 3)  # [1, 2, 1, 2, 1, 2]
    print(1 in [2, 3, 1])  # True
    print(11 in [2, 3, 1])  # False
    for i in [1, 2, 3]:
        print(i, end=','),  # 1,2,3,
    pass


def list_function():
    # --- len
    print(len([1, 2]))  # 2
    print(len([]))  # 0

    # --max
    print(max([1, 4]))  # 4
    print(max(['a', 'd']))  # d
    # print(max([]))  # 报错，不允许空列表

    # --min
    print(min([1, 4]))  # 1
    print(min(['a', 'd']))  # a
    # print(min([]))  # 报错，不允许空列表

    # 元祖转换为list(元素可以看成不可修改的list)
    seq = (1, 2, 3)
    list1 = list(seq)
    list1.append(5)
    print(list1)  # [1, 2, 3, 5]

    pass


def list_function2():
    # append方法
    ll = [1, 2, 3]
    ll.append(4)
    print(ll)  # [1, 2, 3, 4]

    # count方法
    ll = [1, 'ab', 'cd', 1]
    print("count: ", ll.count(1))  # count:  2
    print("count: ", ll.count('ab'))  # count:  1
    print("count: ", ll.count('abcd'))  # count:  0

    # extend方法
    ll2 = [1, 4]
    print(ll2.extend(['a', True]))  # None
    print(ll2)  # [1, 4, 'a', True]

    # index方法
    ll3 = [0, 1, 2, 3]
    print(ll3.index(2))  # 2
    print(ll3.index(2, 0, 3))  # 2
    # print(ll3.index(2, 0, 2))  # ValueError: 2 is not in list #说明是[x,y)
    # print(ll3.index(9))  # ValueError: 9 is not in list #不存在会抛异常

    # insert指定位置插入
    ll4 = [0, 1, 2, 4]
    ll4.insert(3, 3)
    print("after insert:", ll4)  # [0, 1, 2, 3, 4]
    ll4.insert(5, 'a')
    print("after insert:", ll4)  # [0, 1, 2, 3, 4, 'a']
    ll4.insert(99, 'b')
    print("after insert:", ll4)  # [0, 1, 2, 3, 4, 'a', 'b'] #可以看出，只是插到最后了

    # pop用于移除列表中的一个元素，并返回该元素; 默认返回最后一个
    ll5 = [11, 12]
    print(ll5.pop())  # 12
    print(ll5.pop())  # 11
    # print(ll5.pop())  #  IndexError: pop from empty list
    print([21, 22].pop(-2))  # 21

    # remove元素
    ll6 = [0, 1, 2, 1]
    ll6.remove(1)
    print("after remove:", ll6)  # [0, 2, 1]
    ll6.remove(1)
    print("after remove:", ll6)  # [0, 2]
    # ll6.remove(1) # ValueError: list.remove(x): x not in list

    # reverse
    ll7 = [1, 2, 34]
    ll7.reverse()
    print(ll7)  # [34, 2, 1]

    # sort排序
    ll8a = [1, 2, 3]
    ll8a.sort()
    print("after sort1:", ll8a)  # [1, 2, 3] #默认升序
    ll8a.sort(reverse=False)
    print("after sort1:", ll8a)  # [1, 2, 3] #升序
    ll8a.sort(reverse=True)
    print("after sort1:", ll8a)  # [3, 2, 1] #降序

    ll8b = [(1, 5), (2, 4), (3, 3)]
    print("before sort2", ll8b)  # [(1, 5), (2, 4), (3, 3)]
    ll8b.sort(key=lambda x: x[0])
    print("after sort2", ll8b)  # [(1, 5), (2, 4), (3, 3)]
    ll8b.sort(key=lambda x: x[1])
    print("after sort2", ll8b)  # [(3, 3), (2, 4), (1, 5)]

    sort_complex()

    pass


def sort_complex():
    # ========================== 使用多个元素排序
    ll8c = [(1, 'd'), (3, 'c'), (1, 'a')]
    print("before sort3", ll8c)  # [(1, 'd'), (3, 'c'), (1, 'a')]

    # 降序|升序
    print("after sort3", sorted(ll8c, key=lambda x: (-x[0], x[1])))  # [(3, 'c'), (1, 'a'), (1, 'd')]

    # 升序|降序
    # 方案1不行 # [(1, 'd'), (3, 'c'), (1, 'a')]
    print("sort_complex0", sorted(sorted(ll8c, key=lambda x: x[0]), key=lambda x: x[1], reverse=True))

    # 方案2可以 
    def multi_sort_item(_self, specs):
        for key, reverse in reversed(specs):
            _self.sort(key=itemgetter(key), reverse=reverse)
        return _self

    print("sort_complex1:", multi_sort_item(ll8c, ((0, False), (1, True))))  # [(1, 'd'), (1, 'a'), (3, 'c')]

    # 方案3可以，类似comparator比较器
    def compare(x1, x2):
        if x1[0] != x2[0]:
            return 1 if x1[0] > x2[0] else -1
        if x1[1] == x2[1]:
            return 0
        else:
            return 1 if x2[1] > x1[1] else -1
        pass

    print("sort_complex2:", sorted(ll8c, key=functools.cmp_to_key(compare)))  # [(1, 'd'), (1, 'a'), (3, 'c')]

    # ========================== 对象排序

    class Student:
        def __init__(self, grade, age):
            self.grade = grade
            self.age = age

        def __repr__(self):
            return repr((self.grade, self.age))

    student_objects = [
        Student('B', 10),
        Student('A', 8),
        Student('B', 7),
    ]

    def multi_sort_obj(_self, specs):
        for key, reverse in reversed(specs):
            _self.sort(key=attrgetter(key), reverse=reverse)
        return _self

    print(multi_sort_obj(list(student_objects), (('grade', True), ('age', False))))
    # [('B', 7), ('B', 10), ('A', 8)]
    pass


if __name__ == '__main__':
    # read()
    # modify()
    # delete()
    # list_operation()
    # list_function()
    list_function2()

    pass
