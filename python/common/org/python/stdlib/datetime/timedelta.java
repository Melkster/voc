package org.python.stdlib.datetime;

import java.math.BigDecimal;

public class timedelta extends org.python.types.Object {

    private static final long MAX_DAYS = 999999999;
    private static final long MAX_SECONDS = 60 * 60 * 24 - 1;
    private static final long MAX_MICROS = 1000000 - 1;

    @org.python.Attribute
    public final static org.python.Object min = new timedelta(-MAX_DAYS, 0, 0);
    @org.python.Attribute
    public final static org.python.Object max = new timedelta(MAX_DAYS, MAX_SECONDS, MAX_MICROS);
    @org.python.Attribute
    public final static org.python.Object resolution = new timedelta(0, 0, 1);

    @org.python.Attribute
    public org.python.types.Int days;
    @org.python.Attribute
    public org.python.types.Int seconds;
    @org.python.Attribute
    public org.python.types.Int microseconds;

    private double getArg(int position, String keyword, org.python.Object[] args,
            java.util.Map<java.lang.String, org.python.Object> kwargs) {
        boolean hasPositional = args.length > position && args[position] != null;
        boolean hasKeyword = kwargs.containsKey(keyword);

        if (hasPositional && hasKeyword) {
            throw new org.python.exceptions.TypeError(
                    String.format("Argument given by name ('%s') and position (%d)", keyword, position + 1));
        } else if (hasPositional) {
            return getFloatvalue(args[position]);
        } else if (hasKeyword) {
            return getFloatvalue(kwargs.get(keyword));
        } else {
            return 0;
        }
    }

    private timedelta(long days, long seconds, long microseconds) {
        this((double) days, (double) seconds, (double) microseconds);
    }

    private timedelta(double days, double seconds, double microseconds) {
        normalize(days, seconds, microseconds);
    }

    @org.python.Method(__doc__ = "Creates empty timedelta", kwargs = "kwargs")
    public timedelta(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
        double days = 0;
        double seconds = 0;
        double microseconds = 0;

        days += getArg(0, "days", args, kwargs);
        seconds += getArg(1, "seconds", args, kwargs);
        microseconds += getArg(2, "microseconds", args, kwargs);
        microseconds += getArg(3, "milliseconds", args, kwargs) * 1000;
        seconds += getArg(4, "minutes", args, kwargs) * 60;
        seconds += getArg(5, "hours", args, kwargs) * 60 * 60;
        days += getArg(6, "weeks", args, kwargs) * 7;

        normalize(days, seconds, microseconds);
    }

    private void normalize(double days, double seconds, double microseconds) {
        // convert decimals to smaller units e.g. 1.5 days => 1 days and 43200 seconds
        seconds += (days % 1) * 60 * 60 * 24;
        days -= days % 1;

        microseconds += (seconds % 1) * 1e6;
        seconds -= seconds % 1;

        // round the answer to whole microseconds
        microseconds = roundHalfToEven(microseconds);

        // convert many microseconds to seconds
        seconds += (long) (microseconds / 1e6);
        microseconds -= ((long) (microseconds / 1e6)) * 1e6;
        // convert many seconds to days
        days += (long) (seconds / (24 * 60 * 60));
        seconds -= (long) (seconds / (24 * 60 * 60)) * 24 * 60 * 60;

        if (microseconds < 0) {
            days -= 1;
            seconds += MAX_SECONDS;
            microseconds += MAX_MICROS + 1;

            // IF we overflow MAX_SECONDS again
            days += (long) (seconds / (24 * 60 * 60));
            seconds -= (long) (seconds / (24 * 60 * 60)) * 24 * 60 * 60;
        }
        if (seconds < 0) {
            days -= 1;
            seconds += MAX_SECONDS + 1;
        }

        if (Math.abs(days) > MAX_DAYS) {
            throw new org.python.exceptions.OverflowError(
                    String.format("days=%d; must have magnitude <= %d", (long) days, MAX_DAYS));
        }

        this.days = org.python.types.Int.getInt((long) days);
        this.seconds = org.python.types.Int.getInt((long) seconds);
        this.microseconds = org.python.types.Int.getInt((long) microseconds);
    }

    private static long roundHalfToEven(double d) {
        if (d % 0.5 == 0 && d % 1 != 0) {
            if (Math.floor(d) % 2 == 0) {
                return (long) Math.floor(d);
            } else {
                return (long) Math.ceil(d);
            }
        }
        return Math.round(d);
    }

    @org.python.Method(__doc__ = "Return the total number of seconds contained in the duration.\n"
            + " Equivalent to td / timedelta(seconds=1). For interval units \n"
            + "other than seconds, use the division form directly \n"
            + "(e.g. td / timedelta(microseconds=1)).", args = {})
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
            // if obj is really an integer -> cast to double in order to bypass __float__()
            // function wich will cast
            // the value into a 32-bit float.
            return (double) getIntValue(obj);
        } else {
            return ((org.python.types.Float) obj.__float__()).value;
        }
    }

    @Override
    @org.python.Method(
            __doc__ = "Return self*value",
            args = {"other"}
    )
    public org.python.Object __mul__(org.python.Object other) {
        if (other instanceof org.python.types.Int || other instanceof org.python.types.Float) {
            double mult = getFloatvalue(other);
            return new timedelta(mult * this.days.value,
                    this.seconds.value * mult, 
                    this.microseconds.value * mult);
        }
        return super.__mul__(other);
    }

    @Override
    @org.python.Method(
            __doc__ = "Return self+value.",
            args = {"other"}
    )
    public org.python.Object __add__(org.python.Object other) {
        if (other instanceof org.python.stdlib.datetime.timedelta) {
            timedelta otherTd = (timedelta) other;
            return new timedelta(this.days.value + otherTd.days.value, 
                                this.seconds.value + otherTd.seconds.value,
                                this.microseconds.value + otherTd.microseconds.value);
        } else {
            return super.__add__(other);
        }
    }

    @Override
    public java.lang.String typeName() {
        return "datetime.timedelta";
    }
}
