package org.python.types;

import org.Python;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ListTest {
    @Test
    public void toJava_notNull() {
        assertNotNull(new List().toJava());
    }

    @Test
    public void isHashable_isFalse() {
        assertFalse(new List().isHashable());
    }

    @Test
    public void hashCode_notNull() {
        assertNotNull(new List().hashCode());
    }

    @Test(expected = org.python.exceptions.AttributeError.class)
    public void __hash___shouldThrowException() {
        new List().__hash__();
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnInt() {
        new List().__iadd__(org.python.types.Int.getInt(1));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnBool() {
        new List().__iadd__(org.python.types.Bool.getBool(true));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnEllipsis() {
        new List().__iadd__(org.python.types.Ellipsis.ELLIPSIS);
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnNone() {
        new List().__iadd__(org.python.types.NoneType.NONE);
    }

    @Test()
    public void __iadd__lists() {
        List l1 = new List();
        List l2 = new List();

        l1.append(org.python.types.Int.getInt(1));
        l2.append(org.python.types.Int.getInt(2));

        l1.__iadd__(l2);

        assertEquals(l1.__getitem__(org.python.types.Int.getInt(0)), org.python.types.Int.getInt(1));
        assertEquals(l1.__getitem__(org.python.types.Int.getInt(1)), org.python.types.Int.getInt(2));
        assertEquals(l1.__len__(), org.python.types.Int.getInt(2));
        assertEquals(l2.__len__(), org.python.types.Int.getInt(1));
    }


}

