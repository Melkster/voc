package python;

@org.python.Module(
        __doc__ =
                "This module provides various functions to math." 
)
public class math extends org.python.types.Module {
    public math() {
        super();
    }

    private static long vm_start_time;
    public static org.python.Object _STRUCT_TM_ITEMS;

    @org.python.Attribute
    public static org.python.Object __file__ = new org.python.types.Str("python/common/python/math.java");
    @org.python.Attribute
    public static org.python.Object __loader__ = org.python.types.NoneType.NONE;  // TODO
    @org.python.Attribute
    public static org.python.Object __name__ = new org.python.types.Str("math");
    @org.python.Attribute
    public static org.python.Object __package__ = new org.python.types.Str("");
    @org.python.Attribute
    public static org.python.Object __spec__ = org.python.types.NoneType.NONE;  // TODO
    public static org.python.types.Int altzone;

    // Python has a maximum value of float larger than javas. This is pythons max float
    public static double pythonMaxFloat = 1.7976931348623157e+308;

    @org.python.Method(
            __doc__ = "exp",
            args = {"x"}
    )
    public static org.python.Object exp(org.python.Object x)
        throws org.python.exceptions.OverflowError {
        // The float type uses double internally, so we can simply use that
        double x_double = ((org.python.types.Float) x.__float__()).value;
        double result = Math.exp(x_double);
        if (result > pythonMaxFloat) {
            throw new org.python.exceptions.OverflowError("math range error");
        } else {
            return new org.python.types.Float(result);
        }
    }

}

