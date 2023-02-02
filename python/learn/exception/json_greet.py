#!/usr/bin/env python3
# -*- coding:utf-8 -*-

import json


def get_stored_user_name():
    try:
        with open('user.tmp') as f:
            remembered_name = json.load(f)
    except FileNotFoundError:
        return None
    else:
        return remembered_name


def new_username_and_save():
    name = input('please input your name:')
    with open('user.tmp', 'w') as f:
        json.dump(name.title(), f)
    pass


def greet_user():
    stored_name = get_stored_user_name()

    if stored_name:
        print(f'Welcome back, {stored_name}')
    else:
        new_username_and_save()
    pass


if __name__ == '__main__':
    greet_user()
