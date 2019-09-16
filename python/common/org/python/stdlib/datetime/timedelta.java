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
        double days = 0;
        double seconds = 0;
        double microseconds = 0;
        if (args.length >= 1 && args[0] != null) { // days
            days += getFloatvalue(args[0]);
        }
        if (args.length >= 2 && args[1] != null) { // seconds
            seconds += getFloatvalue(args[1]);
        }
        if (args.length >= 3 && args[2] != null) { // micros
            microseconds += getFloatvalue(args[2]);
        }
        if (args.length >= 4 && args[3] != null) { // millis
            microseconds += getFloatvalue(args[3]) * 1000;
        }
        if (args.length >= 5 && args[4] != null) { // minutes
            seconds += getFloatvalue(args[4]) * 60;
        }
        if (args.length >= 6 && args[5] != null) { // hours 
            seconds += getFloatvalue(args[5]) * 60 * 60;
        }
        if (args.length >= 7 && args[6] != null) { // weeks
            days += getFloatvalue(args[6]) * 7;
        }
        
        // convert decimals to smaller units e.g. 1.5 days => 1 days and 43200 seconds
        seconds += (days % 1) * 60 * 60 * 24;
        // Note: Math.floor() is no good because of issues with negative arguments
        days -= days % 1;

        microseconds += (seconds % 1) * 1e6;
        seconds -= seconds % 1;

        // convert many microseconds to seconds 
        seconds += Math.floor(microseconds / 1e6);
        microseconds -= Math.floor(microseconds / 1e6) * 1e6;
        // convert many seconds to hours 
        days += Math.floor(seconds / (24*60*60));
        seconds -= Math.floor(seconds / (24*60*60)) * (24*60*60);

        // round the answer to whole microseconds
        microseconds = Math.round(microseconds);

        /*
         * double dayDiff = days - (long) days; double diffSeconds = dayDiff * 24 * 60 *
         * 60; seconds += Math.round(diffSeconds * 10000000) / 10000000; // Round to
         * 1/10ths of microsecond //microseconds += (long) ((double) (diffSeconds -
         * seconds) * 1000000);
         */        
        this.days = org.python.types.Int.getInt((long)days);
        this.seconds = org.python.types.Int.getInt((long)seconds);
        this.microseconds = org.python.types.Int.getInt((long)microseconds);
    }

    @org.python.Method(
        __doc__ = "Return the total number of seconds contained in the duration.\n" +
                  " Equivalent to td / timedelta(seconds=1). For interval units \n" +
                  "other than seconds, use the division form directly \n" + 
                  "(e.g. td / timedelta(microseconds=1)).",
        args = {}
    )
    public org.python.Object total_seconds() {
        return new org.python.types.Float((double) days.value*24*60*60 + (double) seconds.value + 
            ((double) microseconds.value / 1000000));
    }

    private long getIntValue(org.python.Object obj) {
            return ((org.python.types.Int) obj.__int__()).value;
    }
    private double getFloatvalue(org.python.Object obj) {
        if (obj.typeName().equals("int")) {
            // if obj is really an integer -> cast to double in order to bypass __float__() function wich will cast 
            // the value into a 32-bit float.
            return (double)getIntValue(obj);
        } else {
            return ((org.python.types.Float) obj.__float__()).value;
        }
    }
}

