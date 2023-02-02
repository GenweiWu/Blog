#!/usr/bin/env python3
# -*- coding:utf-8 -*-

class AnonymousSurvey:

    def __init__(self, question):
        self.question = question
        self.responses = []

    def show_question(self):
        print(f'Question is:{self.question}')

    def add_response(self, response):
        self.responses.append(response)

    def show_result(self):
        print('Survey result:')
        for r in self.responses:
            print(f'-- {r}')


if __name__ == '__main__':
    survey = AnonymousSurvey('What language would you like to learn?')
    survey.add_response('english')
    survey.add_response('chinese')
    survey.add_response('french')
    survey.add_response('spanish')

    survey.show_question()
    survey.show_result()
