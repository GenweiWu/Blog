#!/usr/bin/env python3
# -*- coding:utf-8 -*-

## 引入特定函数
from func5_common import hello
from func5_common import bye

## 引入特定函数+别名
from func5_common import hello as hh
from func5_common import bye as bb


hello()
bye()

hh('as')
bb('as')
