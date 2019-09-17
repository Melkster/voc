package org.python.stdlib.datetime;

import java.math.BigDecimal;   

public class timedelta extends org.python.types.Object {


    @org.python.Attribute
    public org.python.types.Int days;
    @org.python.Attribute
    public org.python.types.Int seconds;
    @org.python.Attribute
    public org.python.types.Int microseconds;

    private final long MAX_DAYS = 999999999;
    private final long MAX_SECONDS = 60 * 60 * 24 - 1;
    private final long MAX_MICROS = 1000000 - 1;

    @org.python.Method(
        __doc__ = "Creates empty timedelta",
        default_args = {"iterable"},
        kwargs = "kwargs"
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

        // round the answer to whole microseconds
        microseconds = Math.round(microseconds);

        // convert many microseconds to seconds 
        seconds += (long)(microseconds / 1e6);
        microseconds -= ((long)(microseconds / 1e6)) * 1e6;
        // convert many seconds to days 
        days += (long)(seconds / (24*60*60));
        seconds -= (long)(seconds / (24*60*60)) * (24*60*60);


        if (microseconds < 0) {
            days -= 1;
            seconds += MAX_SECONDS;
            microseconds += MAX_MICROS + 1;

            // IF we overflow MAX_SECONDS again
            days += (long)(seconds / (24*60*60));
            seconds -= (long)(seconds / (24*60*60)) * (24*60*60);
        }
        if (seconds < 0) {
            days -= 1;
            seconds += MAX_SECONDS + 1;
        }
        


        if (Math.abs(days) > MAX_DAYS) {
            throw new org.python.exceptions.OverflowError(String.format("days=%d; must have magnitude <= %d", (long)days, MAX_DAYS));
        }

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
        // Regular double precision isn't enough! We need the big stuff
        BigDecimal daysInSeconds = BigDecimal.valueOf(days.value).multiply(BigDecimal.valueOf(24 * 60 * 60));
        BigDecimal bigSeconds = BigDecimal.valueOf(seconds.value);
        BigDecimal microsecondsInSeconds = BigDecimal.valueOf(microseconds.value).divide(BigDecimal.valueOf(1e6));
        BigDecimal total = daysInSeconds.add(bigSeconds).add(microsecondsInSeconds);
        return new org.python.types.Float(total.doubleValue());
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

