from unittest import expectedFailure

from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):
    def test_sqrt(self):
        self.assertCodeExecution("""
            import math
            print(math.sqrt(1))
        """)

    @expectedFailure
    def test_sqrt_neg(self):
        self.assertCodeExecution("""
            import math
            print(math.sqrt(-1))
        """)

    @expectedFailure
    def test_sqrt_no_args(self):
        self.assertCodeExecution("""
            import math
            print(math.sqrt())
        """)

    @expectedFailure
    def test_sqrt_too_many_args(self):
        self.assertCodeExecution("""
            import math
            print(math.sqrt(1,2))
        """)

        self.assertCodeExecution("""
            import math
            print(math.sqrt(1,2,3,4,5))
        """)

    def test_exp_standard(self):
        self.assertCodeExecution("""
            import math
            print(math.exp(0))
            print(math.exp(-1))
            print(math.exp(1))
            print(math.exp(0.0001))
            print(math.exp(-0.0001))
            print(math.exp(-100000))
        """)

    @expectedFailure
    def test_exp_fail_empty(self):
        self.assertCodeExecution("""
            import math
            print(math.exp())
        """)

    @expectedFailure
    def test_exp_fail_nan(self):
        self.assertCodeExecution("""
            import math
            print(math.exp('this is not a number'))
        """)

    @expectedFailure
    def test_exp_fail_two_args(self):
        self.assertCodeExecution("""
            import math
            print(math.exp(1, 2))
        """)

    @expectedFailure
    def test_exp_fail_array_arg(self):
        self.assertCodeExecution("""
            import math
            print(math.exp([1, 2, 3]))
        """)

    @expectedFailure
    def test_exp_fail_large(self):
        self.assertCodeExecution("""
            import math
            print(math.exp(100000))
        """)
