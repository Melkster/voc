from ..utils import TranspileTestCase


class DatetimeModuleTests(TranspileTestCase):
    def test_timedelta_default_constructor(self):
        self.assertCodeExecution("""
            import datetime
            delta = datetime.timedelta()
            print(delta.total_seconds())
        """)

    def test_timedelta_days_constructor(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta(0).total_seconds())
            print(datetime.timedelta(1).total_seconds())
            print(datetime.timedelta(-1).total_seconds())
            print(datetime.timedelta(999999999).total_seconds())
            print(datetime.timedelta(-999999999).total_seconds())
            print(datetime.timedelta(0.1).total_seconds())
            print(datetime.timedelta(-0.1).total_seconds())
            print(datetime.timedelta(1.9).total_seconds())
            print(datetime.timedelta(-2.9).total_seconds())
            print(datetime.timedelta(0.00001).total_seconds())
            print(datetime.timedelta(-0.0001).total_seconds())
            try:
                # Test more than max days (i.e. 999999999)
                print(datetime.timedelta(999999999+1).total_seconds())
            except OverflowError as e:
                print(e)
        """)

    def test_timedelta_seconds_constructor(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta(0, 0).total_seconds())
            print(datetime.timedelta(0, -1).total_seconds())
            print(datetime.timedelta(0, 1).total_seconds())
            print(datetime.timedelta(0, 999999999).total_seconds())
            print(datetime.timedelta(0, -999999999).total_seconds())
            print(datetime.timedelta(0, 0.1).total_seconds())
            print(datetime.timedelta(0, -0.1).total_seconds())
            print(datetime.timedelta(0, 0.9).total_seconds())
            print(datetime.timedelta(0, -0.9).total_seconds())
            print(datetime.timedelta(0, 1e9*60*60*24 - 1).total_seconds())
            try:
                # Test more than max days (i.e. 999999999)
                print(datetime.timedelta(0, 1e9*60*60*24).total_seconds())
            except OverflowError as e:
                print(e)
        """)

    # def test_timedelta_seconds_constructor(self):
    #     self.assertCodeExecution("""
    #         import datetime
    #         print(datetime.timedelta(0, 0).total_seconds())
    #         print(datetime.timedelta(0, -1).total_seconds())
    #         print(datetime.timedelta(0, 1).total_seconds())
    #         print(datetime.timedelta(0, 999999999).total_seconds())
    #         print(datetime.timedelta(0, -999999999).total_seconds())
    #         print(datetime.timedelta(0, 0.1).total_seconds())
    #         print(datetime.timedelta(0, -0.1).total_seconds())
    #         print(datetime.timedelta(0, 0.9).total_seconds())
    #         print(datetime.timedelta(0, -0.9).total_seconds())
    #     """)

# TODO: Add Kwargs tests