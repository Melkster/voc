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

        assertEquals(org.python.types.Int.getInt(1), l1.__getitem__(org.python.types.Int.getInt(0)));
        assertEquals(org.python.types.Int.getInt(2), l1.__getitem__(org.python.types.Int.getInt(1)));
        assertEquals(org.python.types.Int.getInt(2), l1.__len__());
        assertEquals(org.python.types.Int.getInt(1), l2.__len__());
    }

    @Test()
    public void __iadd__listsReverse() {
        List l1 = new List();
        List l2 = new List();

        l1.append(org.python.types.Int.getInt(1));
        l2.append(org.python.types.Int.getInt(2));

        l2.__iadd__(l1);

        assertEquals(org.python.types.Int.getInt(2), l2.__getitem__(org.python.types.Int.getInt(0)));
        assertEquals(org.python.types.Int.getInt(1), l2.__getitem__(org.python.types.Int.getInt(1)));
        assertEquals(org.python.types.Int.getInt(2), l2.__len__());
        assertEquals(org.python.types.Int.getInt(1), l1.__len__());
    }

    @Test()
    public void __iadd__tuple() {
        java.util.ArrayList<org.python.Object> v1 = new java.util.ArrayList<org.python.Object>();
        v1.add(org.python.types.Int.getInt(1));
        v1.add(org.python.types.Int.getInt(2));

        java.util.ArrayList<org.python.Object> v2 = new java.util.ArrayList<org.python.Object>();
        v2.add(org.python.types.Int.getInt(3));
        v2.add(org.python.types.Int.getInt(4));

        Tuple t1 = new Tuple(v1);
        Tuple t2 = new Tuple(v2);

        t1.__iadd__(t2);
        
        assertEquals(org.python.types.Int.getInt(1), t1.value.get(0));
    }

}

