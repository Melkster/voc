package org.python.stdlib.datetime;

import java.time.YearMonth;

public class date extends org.python.types.Object {
    public final static int MIN_YEAR = 1;
    public final static int MAX_YEAR = 9999;
    public final static int MIN_MONTH = 0;
    public final static int MAX_MONTH = 11;
    public final static int MIN_DAY = 1;
    public final static int MAX_DAY = 31;

    @org.python.Attribute()
    public final static date min = new date(MIN_YEAR, MIN_MONTH, MIN_DAY);
    @org.python.Attribute()
    public final static date max = new date(MAX_YEAR, MAX_MONTH, MAX_DAY);
    @org.python.Attribute()
    public final static date resolution = null; // TODO: implement `resolution` when timedelta is implemented

    public java.util.GregorianCalendar value;

    @org.python.Attribute()
    public final int year;
    @org.python.Attribute()
    public final int month;
    @org.python.Attribute()
    public final int day;

    private final static java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

    public int hashCode() {
        return this.value.hashCode();
    }

    @org.python.Method(
            __doc__ = "date(year, month, day) -> date" +
                "\n" +
                "Return new date of the format yyyy-MM-dd\n"
            ,
            args = {"year, month, day"}
    )
    public date(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
        org.python.Object year = getArg(0, "year", args, kwargs);
        org.python.Object month = getArg(1, "month", args, kwargs);
        org.python.Object day = getArg(2, "day", args, kwargs);
        if (!(year instanceof org.python.types.Int)) {
            throw new org.python.exceptions.TypeError("an integer is required (got type " + year.typeName() + ")");
        } else if (!(month instanceof org.python.types.Int)) {
            throw new org.python.exceptions.TypeError("an integer is required (got type " + month.typeName() + ")");
        } else if (!(day instanceof org.python.types.Int)) {
            throw new org.python.exceptions.TypeError("an integer is required (got type " + day.typeName() + ")");
        }

        int y = (int) ((org.python.types.Int) year.__int__()).value;
        int m = (int) ((org.python.types.Int) month.__int__()).value - 1; // GregorianCalendar starts counting from 0
        int d = (int) ((org.python.types.Int) day.__int__()).value;

        date.inputRangeCheck(y, m, d);

        this.year = y;
        this.month = m + 1;
        this.day = d;
        this.value = new java.util.GregorianCalendar(y, m, d);
    }

    public date(int year, int month, int day) {
        date.inputRangeCheck(year, month, day);

        this.year = year;
        this.month = month + 1;
        this.day = day;
        this.value = new java.util.GregorianCalendar(year, month, day);
    }

    private static void inputRangeCheck(int year, int month, int day) {
        if (year < date.MIN_YEAR || year > date.MAX_YEAR) {
            throw new org.python.exceptions.ValueError("year " + year + " is out of range");
        } else if (month < date.MIN_MONTH || month > date.MAX_MONTH) {
            throw new org.python.exceptions.ValueError("month must be in 1..12");
        } else if (day < date.MIN_DAY || day > YearMonth.of(year, month + 1).lengthOfMonth()) {
            throw new org.python.exceptions.ValueError("day is out of range for month");
        }
    }

    private org.python.Object getArg(int position, String keyword, org.python.Object[] args,
            java.util.Map<java.lang.String, org.python.Object> kwargs) {
        boolean hasPositional = args.length > position && args[position] != null;
        boolean hasKeyword = kwargs.containsKey(keyword);

        if (hasPositional && hasKeyword) {
            throw new org.python.exceptions.TypeError(
                    String.format("Argument given by name ('%s') and position (%d)", keyword, position + 1));
        } else if (hasPositional) {
            return args[position];
        } else if (hasKeyword) {
            return kwargs.get(keyword);
        } else {
            return org.python.types.Int.getInt(0);
        }
    }

    @org.python.Method(
            __doc__ = "Return the current local date.\n" + "\n"
            + "This is equivalent to date.fromtimestamp(time.time()).\n")
    public static final date today() {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        return new date(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());
    }

    @org.python.Method(
            __doc__ = "Return a date with the same value, except for those parameters given new values by \n" +
            "whichever keyword arguments are specified.\n" +
            "\n" +
            " For example, if d == date(2002, 12, 31), then d.replace(day=26) == date(2002, 12, 26).\n",
            args = {},
            kwargs = "kwargs"
    )
    public date replace(org.python.types.Dict kwargs) {
        int y = this.year;
        int m = this.month;
        int d = this.day;

        java.util.Map<org.python.Object, org.python.Object> kwargValues = kwargs.value;

        for (org.python.Object key : kwargValues.keySet()) {
            org.python.types.Str keyStr = (org.python.types.Str) key;
            org.python.Object obj = kwargValues.get(key);

            if (!(obj instanceof org.python.types.Int)) {
                throw new org.python.exceptions.TypeError("an integer is required (got type " + obj.typeName() + ")");
            }

            int v = (int) ((org.python.types.Int) (obj).__int__()).value;


            if (keyStr.value.equals("year"))
                y = v;
            else if (keyStr.value.equals("month"))
                m = v;
            else if (keyStr.value.equals("day"))
                d = v;
        }

        return new date(y, m - 1, d);
    }

    @org.python.Method(
            __doc__ = "Return the day of the week as an integer, where Monday is 0 and Sunday is 6.\n" + "\n"
            + "Java implementation is not aligned with Python's implementation, thus some shifing up to 2 days is expected on old dates (< 1800).\n")
    public final int weekday() {
        return (this.value.get(java.util.GregorianCalendar.DAY_OF_WEEK) + 5) % 7;
    }

    @org.python.Method(
            __doc__ = "Return repr(self).\n"
    )
    public org.python.types.Str __repr__() {
        return new org.python.types.Str(sdf.format(this.value.getTime()));
    }
}
