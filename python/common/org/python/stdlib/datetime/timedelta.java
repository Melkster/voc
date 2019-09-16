package org.python.stdlib.datetime;


public class timedelta extends org.python.types.Object {

    @org.python.Method(
        __doc__ = "Creates empty timedelta",
        default_args = {"iterable"}
    )
    public timedelta(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
    }

    @org.python.Method(
        __doc__ = "Return the total number of seconds contained in the duration.\n" +
                  " Equivalent to td / timedelta(seconds=1). For interval units \n" +
                  "other than seconds, use the division form directly \n" + 
                  "(e.g. td / timedelta(microseconds=1)).",
        args = {}
    )
    public org.python.Object total_seconds() {
        return new org.python.types.Float(0);
    }
}

