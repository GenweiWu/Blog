#!/usr/bin/env python3
# -*- coding:utf-8 -*-

import unittest
from survey import AnonymousSurvey


class ServeyTestCase(unittest.TestCase):
    """1.使用setup进行优化"""

    def setUp(self) -> None:
        question = 'What language would you like to learn?'
        self.survey = AnonymousSurvey(question)
        self.responses = ['chinese', 'spanish', 'french']
        print(f'>>> setup run')

    def tearDown(self) -> None:
        print(f'>>> tearDown run')

    def test_add_single_response(self) -> None:
        self.survey.add_response(self.responses[1])
        self.assertIn(self.responses[1], self.survey.responses)

    def test_add_many_response(self) -> None:
        for resp in self.responses:
            self.survey.add_response(resp)

        for resp in self.responses:
            self.assertIn(resp, self.survey.responses)

        # or --
        self.assertCountEqual(self.responses, self.survey.responses)


if __name__ == '__main__':
    unittest.main()
