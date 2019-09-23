from ..utils import TranspileTestCase
from unittest import expectedFailure

class DatetimeModuleTests(TranspileTestCase):
    def test_date_constructor(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.date(2000, 2, 1))
            print(datetime.date(1, 1, 1))
            print(datetime.date(9999, 12, 31))
        """)

    def test_date_constructor_invalid(self):
        self.assertCodeExecution("""
            import datetime
            try:
                datetime.date(-10, 1, 1)
            except ValueError as e:
                print(e)
            try:
                datetime.date(1, -11, 1)
            except ValueError as e:
                print(e)
            try:
                datetime.date(1, 1, -12)
            except ValueError as e:
                print(e)
            try:
                datetime.date(2019, 9, -1)
            except ValueError as e:
                print(e)
            try:
                datetime.date(2019, 9, 31)
            except ValueError as e:
                print(e)
            try:
                datetime.date(1, 1, 0)
            except ValueError as e:
                print(e)
            try:
                datetime.date(1, 0, 1)
            except ValueError as e:
                print(e)
            try:
                datetime.date(0, 1, 1)
            except ValueError as e:
                print(e)
            try:
                datetime.date(0, 0, 0)
            except ValueError as e:
                print(e)
            try:
                datetime.date(10000, 1, 1)
            except ValueError as e:
                print(e)
            try:
                datetime.date("foo", 1, 1)
            except TypeError as e:
                print(e)
            try:
                datetime.date(1, "foo", 1)
            except TypeError as e:
                print(e)
            try:
                datetime.date(1, 1, "foo")
            except TypeError as e:
                print(e)
        """)

    def test_date_today(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.date(2000, 1, 1).today())
        """)

    def test_date_min(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.date(1999, 1, 20).min)
            print(datetime.date(1, 1, 1).min)
            print(datetime.date(9999, 12, 31).min)
        """)

    def test_date_max(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.date(1999, 1, 20).max)
            print(datetime.date(1, 1, 1).max)
            print(datetime.date(9999, 12, 31).max)
        """)

    @expectedFailure
    def test_date_resolution(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.date(1999, 1, 1).resolution)
        """)

    def test_date_year(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.date(1999, 1, 20).year)
            print(datetime.date(1, 1, 1).year)
            print(datetime.date(9999, 12, 31).year)

        """)

    def test_date_month(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.date(1999, 1, 20).month)
            print(datetime.date(1, 1, 1).month)
            print(datetime.date(9999, 12, 31).month)
        """)

    def test_date_day(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.date(1999, 1, 20).day)
            print(datetime.date(1, 1, 1).day)
            print(datetime.date(9999, 12, 31).day)
        """)

    def test_date_replace(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.date(1999, 1, 1).replace(day=3))
            print(datetime.date(1999, 1, 1).replace(month=3))
            print(datetime.date(1999, 1, 1).replace(year=3))
            print(datetime.date(1999, 1, 1).replace(day=3, month=3))
            print(datetime.date(1999, 1, 1).replace(month=3, year=3))
            print(datetime.date(1999, 1, 1).replace(day=3, year=3))
            print(datetime.date(1999, 1, 1).replace(day=3, month=3, year=3))
        """)

    def test_date_replace_invalid(self):
        self.assertCodeExecution("""
            import datetime
            try:
                datetime.date(1, 1, 1).replace(day=0)
            except ValueError as e:
                print(e)
            try:
                datetime.date(1, 1, 1).replace(month=0)
            except ValueError as e:
                print(e)
            try:
                datetime.date(1, 1, 1).replace(year=0)
            except ValueError as e:
                print(e)
            try:
                datetime.date(9999, 12, 31).replace(day=32)
            except ValueError as e:
                print(e)
            try:
                datetime.date(9999, 12, 31).replace(month=13)
            except ValueError as e:
                print(e)
            try:
                datetime.date(9999, 12, 31).replace(year=10000)
            except ValueError as e:
                print(e)
            try:
                datetime.date(9999, 12, 31).replace(day=32, year=10000)
            except ValueError as e:
                print(e)
            try:
                datetime.date(9999, 12, 31).replace(day=32, month=13, year=10000)
            except ValueError as e:
                print(e)
            try:
                datetime.date(1, 1, 1).replace(day="foo")
            except TypeError as e:
                print(e)
            try:
                datetime.date(1, 1, 1).replace(month="foo")
            except TypeError as e:
                print(e)
            try:
                datetime.date(1, 1, 1).replace(year="foo")
            except TypeError as e:
                print(e)
        """)

    def test_date_replace_empty(self):
        self.assertCodeExecution("""
            import datetime
            d = datetime.date(1999, 1, 1)
            print(d.replace())
            print(d is not d.replace())
        """)

    def test_date_weekday(self):
        self.assertCodeExecution("""
            import datetime
            for i in range(9, 16):
                print(datetime.date(2019, 9, i).weekday())
            for i in range(24, 32):
                print(datetime.date(9999, 12, i).weekday())
        """)

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

    def test_timedelta_microseconds_constructor(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta(0, 0, 0).total_seconds())
            print(datetime.timedelta(0, 0, -1).total_seconds())
            print(datetime.timedelta(0, 0, 1).total_seconds())
            print(datetime.timedelta(0, 0, 999999999).total_seconds())
            print(datetime.timedelta(0, 0, -999999999).total_seconds())
            print(datetime.timedelta(0, 0, 0.1).total_seconds())
            print(datetime.timedelta(0, 0, -0.1).total_seconds())
            print(datetime.timedelta(0, 0, 0.9).total_seconds())
            print(datetime.timedelta(0, 0, -0.9).total_seconds())
            # Testing overflow to days
            print(datetime.timedelta(0, 0, 1e6*24*60*60 + 1).total_seconds())
            print(datetime.timedelta(0, 0, 1e6*24*60*60 - 1).total_seconds())
            print(datetime.timedelta(0, 0, 1e6*24*60*60).total_seconds())

            # Testing round-half-to-even
            # Should be 2 microseconds because of round-half-to-even
            print(datetime.timedelta(0, 0, 1.5).total_seconds())
            # Should be 0 microseconds because of round-half-to-even
            print(datetime.timedelta(0, 0, 0.5).total_seconds())
        """)

    def test_timedelta_milliseconds_constructor(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta(0, 0, 0, 0).total_seconds())
            print(datetime.timedelta(0, 0, 0, -1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 999999999).total_seconds())
            print(datetime.timedelta(0, 0, 0, -999999999).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0.1).total_seconds())
            print(datetime.timedelta(0, 0, 0, -0.1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0.9).total_seconds())
            print(datetime.timedelta(0, 0, 0, -0.9).total_seconds())
            # Testing overflow to days
            print(datetime.timedelta(0, 0, 0, 1e3*24*60*60 + 1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 1e3*24*60*60 - 1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 1e3*24*60*60).total_seconds())
        """)

    def test_timedelta_minutes_constructor(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta(0, 0, 0, 0, 0).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, -1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 999999999).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, -999999999).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0.1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, -0.1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0.9).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, -0.9).total_seconds())
        """)

    def test_timedelta_hours_constructor(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta(0, 0, 0, 0, 0, 0).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, -1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 999999999).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, -999999999).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0.1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, -0.1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0.9).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, -0.9).total_seconds())
        """)

    def test_timedelta_weeks_constructor(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta(0, 0, 0, 0, 0, 0, 0).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0, -1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0, 1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0, 999999999 / 8).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0, -999999999 / 8).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0, 0.1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0, -0.1).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0, 0.9).total_seconds())
            print(datetime.timedelta(0, 0, 0, 0, 0, 0, -0.9).total_seconds())
        """)

    def test_timedelta_instance_attributes(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta().days)
            print(datetime.timedelta().seconds)
            print(datetime.timedelta().microseconds)
            print(datetime.timedelta(-1, -1, -1).days)
            print(datetime.timedelta(-1, -1, -1).seconds)
            print(datetime.timedelta(-1, -1, -1).microseconds)
            print(datetime.timedelta(1.12345).days)
            print(datetime.timedelta(1.12345).seconds)
            print(datetime.timedelta(1.12345).microseconds)
            print(datetime.timedelta(999.12345).days)
            print(datetime.timedelta(999.12345).seconds)
            print(datetime.timedelta(999.12345).microseconds)
            print(datetime.timedelta(0, -0.1).days)
            print(datetime.timedelta(0, -0.1).seconds)
            print(datetime.timedelta(0, -0.1).microseconds)
            print(datetime.timedelta(999999999).days)
            print(datetime.timedelta(999999999).seconds)
            print(datetime.timedelta(999999999).microseconds)
        """)

    def test_timedelta_kwargs(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta(days=5).days)
            print(datetime.timedelta(days=5).seconds)
            print(datetime.timedelta(days=5).microseconds)
            print(datetime.timedelta(seconds=5).days)
            print(datetime.timedelta(seconds=5).seconds)
            print(datetime.timedelta(seconds=5).microseconds)
            print(datetime.timedelta(-1, seconds=5).days)
            print(datetime.timedelta(-1, seconds=5).seconds)
            print(datetime.timedelta(-1, seconds=5).microseconds)
            print(datetime.timedelta(seconds=5, days=5).days)
            print(datetime.timedelta(seconds=5, days=5).seconds)
            print(datetime.timedelta(seconds=5, days=5).microseconds)
            print(datetime.timedelta(1, 1, 1, weeks=1).days)
            print(datetime.timedelta(1, 1, 1, weeks=1).seconds)
            print(datetime.timedelta(1, 1, 1, weeks=1).microseconds)

            try:
                datetime.timedelta(1, 1, 1, days=1)
            except TypeError as e:
                print(e)
        """)

    def test_timedelta_class_attributes(self):
        self.assertCodeExecution("""
            import datetime
            print(datetime.timedelta().min.days)
            print(datetime.timedelta().max.days)
            print(datetime.timedelta().resolution.days)
            print(datetime.timedelta().min.seconds)
            print(datetime.timedelta().max.seconds)
            print(datetime.timedelta().resolution.seconds)
            print(datetime.timedelta().min.microseconds)
            print(datetime.timedelta().max.microseconds)
            print(datetime.timedelta().resolution.microseconds)
        """)

    def test_timedelta_multiplication(self):
        self.assertCodeExecution("""
            import datetime
            a = datetime.timedelta(1, 1, 1)
            print((a*1).total_seconds())
            print((a*0).total_seconds())
            print((a*-1).total_seconds())
            print((a*99999999).total_seconds())
            print((a*-99999999).total_seconds())
            print((a*0.9).total_seconds())
            print((a*-0.9).total_seconds())
            print((a*4.9).total_seconds())
            print((a*-4.9).total_seconds())

            b = datetime.timedelta(1)
            print((b*999999999).total_seconds())
            try:
                print((b*(999999999+1)).total_seconds())
            except OverflowError as e:
                print(e)

            # From documentation: "The result is rounded to the nearest multiple
            #                       of timedelta.resolution using round-half-to-even"
            c = datetime.timedelta(0, 0, 2)
            print((c*0.75).total_seconds()) # Should be 2 microseconds because of round-half-to-even
            print((c*0.25).total_seconds()) # Should be 0 microseconds because of round-half-to-even
        """)

    def test_timedelta_addition(self):
        self.assertCodeExecution("""
            import datetime
            print((datetime.timedelta(1) + datetime.timedelta(0)).total_seconds())
            print((datetime.timedelta(1) + datetime.timedelta(-1)).total_seconds())
            print((datetime.timedelta(1) + datetime.timedelta(0, 0, 1)).total_seconds())
            print((datetime.timedelta(1) + datetime.timedelta(0, 0, -1)).total_seconds())
            print((datetime.timedelta(0, 0, 1) + datetime.timedelta(-1, 0, 0)).total_seconds())
            print((datetime.timedelta(1, 1, 1) + datetime.timedelta(1, 2, 3)).total_seconds())
            print((datetime.timedelta(999999999) + datetime.timedelta(0, 60*60*24-1, 999999)).total_seconds())

            try:
                print((datetime.timedelta(1) + 2).total_seconds())
            except TypeError as e:
                print(e)

            try:
                print((datetime.timedelta(999999999) + datetime.timedelta(1)).total_seconds())
            except OverflowError as e:
                print(e)

        """)
