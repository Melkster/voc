package python;

@org.python.Module(__doc__ = "The datetime module supplies classes for manipulating dates and times in both simple and complex ways.\n")

public class datetime extends org.python.types.Module {
    public datetime() {
        super();
    }

    static {
        date = org.python.types.Type.pythonType(org.python.stdlib.datetime.date.class);
        timedelta = org.python.types.Type.pythonType(org.python.stdlib.datetime.timedelta.class);
    }

    @org.python.Attribute()
    public static org.python.Object date;
    @org.python.Attribute()
    public static org.python.Object timedelta;

    @org.python.Attribute
    public static org.python.Object __file__ = new org.python.types.Str("python/common/python/datetime.java");
    @org.python.Attribute
    public static org.python.Object __loader__ = org.python.types.NoneType.NONE; // TODO
    @org.python.Attribute
    public static org.python.Object __name__ = new org.python.types.Str("datetime");
    @org.python.Attribute
    public static org.python.Object __package__ = new org.python.types.Str("");
    @org.python.Attribute
    public static org.python.Object __spec__ = org.python.types.NoneType.NONE; // TODO
}
