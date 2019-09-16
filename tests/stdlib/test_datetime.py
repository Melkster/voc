from ..utils import TranspileTestCase


class DatetimeModuleTests(TranspileTestCase):
    def test_timedelta_construction(self):
        self.assertCodeExecution("""
            import datetime
            datetime.timedelta()
        """)


