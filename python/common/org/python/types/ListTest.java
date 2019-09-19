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
        new List().__iadd__(Int.getInt(1));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnBool() {
        new List().__iadd__(Bool.getBool(true));
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnEllipsis() {
        new List().__iadd__(Ellipsis.ELLIPSIS);
    }

    @Test(expected = org.python.exceptions.TypeError.class)
    public void __iadd___exceptionOnNone() {
        new List().__iadd__(NoneType.NONE);
    }

    @Test()
    public void __iadd___lists() {
        List l1 = new List();
        List l2 = new List();

        l1.append(Int.getInt(1));
        l2.append(Int.getInt(2));

        assertEquals(Int.getInt(1), l1.__len__());
        assertEquals(Int.getInt(1), l2.__len__());
        
        l1.__iadd__(l2);

        assertEquals(Int.getInt(1), l1.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), l1.__getitem__(Int.getInt(1)));
        assertEquals(Int.getInt(2), l1.__len__());
        assertEquals(Int.getInt(1), l2.__len__());
    }

    @Test()
    public void __iadd___listsReverse() {
        List l1 = new List();
        List l2 = new List();

        l1.append(Int.getInt(1));
        l2.append(Int.getInt(2));

        assertEquals(Int.getInt(1), l1.__len__());
        assertEquals(Int.getInt(1), l2.__len__());

        l2.__iadd__(l1);

        assertEquals(Int.getInt(2), l2.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(1), l2.__getitem__(Int.getInt(1)));
        assertEquals(Int.getInt(2), l2.__len__());
        assertEquals(Int.getInt(1), l1.__len__());
    }
}

