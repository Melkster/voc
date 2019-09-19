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
            print(datetime.timedelta(0, 0, 1.5).total_seconds()) # Should be 2 microseconds because of round-half-to-even
            print(datetime.timedelta(0, 0, 0.5).total_seconds()) # Should be 0 microseconds because of round-half-to-even            
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
        """);

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
