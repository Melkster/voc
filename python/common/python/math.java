package python;

@org.python.Module(
    __doc__ = "This module provides various functions to math." 
)
public class math extends org.python.types.Module {
    public math() {
        super();
    }

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

    // Python has a maximum value of float larger than javas. This is pythons max float
    public static double pythonMaxFloat = 1.7976931348623157e+308;

    @org.python.Method(
            __doc__ = "exp(x) = e^x",
            args = {"x"}
    )
    public static org.python.Object exp(org.python.Object x)
        throws org.python.exceptions.OverflowError {
        // The float type uses double internally, so we can simply use that
        double xValue = ((org.python.types.Float) x.__float__()).value;
        double result = Math.exp(xValue);
        if (result > pythonMaxFloat) {
            throw new org.python.exceptions.OverflowError("math range error");
        } else {
            return new org.python.types.Float(result);
        }
    }

    @org.python.Method(
            __doc__ = "sqrt(number) -> calculates the square root of the number `number`",
            args = {"number"}
    )
    public static org.python.Object sqrt(org.python.Object number) {
        double d = ((org.python.types.Float) number.__float__()).value;

        if (d < 0) throw new org.python.exceptions.ValueError("math domain error");

        return new org.python.types.Float(Math.sqrt(d));
    }
}
