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

