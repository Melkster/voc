package org.python.stdlib.datetime;


public class timedelta extends org.python.types.Object {


    @org.python.Attribute
    public org.python.types.Int days;
    @org.python.Attribute
    public org.python.types.Int seconds;
    @org.python.Attribute
    public org.python.types.Int microseconds;

    @org.python.Method(
        __doc__ = "Creates empty timedelta",
        default_args = {"iterable"}
    )
    public timedelta(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
        long days = 0;
        long seconds = 0;
        long microseconds = 0;
        if (args.length >= 1 && args[0] != null) {
            days += getIntValue(args[0]);
            if (args[0].typeName().equals("float")) {
                double fDays = getFloatvalue(args[0]);
                double dayDiff = fDays - days;
                seconds += dayDiff * 24 * 60 * 60;
            }
        }
        this.days = org.python.types.Int.getInt(days);
        this.seconds = org.python.types.Int.getInt(seconds);
        this.microseconds = org.python.types.Int.getInt(microseconds);
    }

    @org.python.Method(
        __doc__ = "Return the total number of seconds contained in the duration.\n" +
                  " Equivalent to td / timedelta(seconds=1). For interval units \n" +
                  "other than seconds, use the division form directly \n" + 
                  "(e.g. td / timedelta(microseconds=1)).",
        args = {}
    )
    public org.python.Object total_seconds() {
        return new org.python.types.Float((double) days.value*24*60*60 + (double) seconds.value);
    }

    private long getIntValue(org.python.Object obj) {
            return ((org.python.types.Int) obj.__int__()).value;
    }
    private double getFloatvalue(org.python.Object obj) {
            return ((org.python.types.Float) obj.__float__()).value;
    }
}

