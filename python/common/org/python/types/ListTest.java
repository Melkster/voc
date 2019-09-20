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

    @Test()
    public void List_empty() {
        assertNotNull(new List());
        assertEquals(Int.getInt(0), new List().__len__());
    }

    @Test()
    public void List_withList() {
        List l1 = new List();
        l1.append(Int.getInt(1));
        l1.append(Int.getInt(2));
        l1.append(Int.getInt(3));

        java.util.Map<java.lang.String, org.python.Object> kwargs = new java.util.HashMap<java.lang.String, org.python.Object>();
        org.python.Object[] args = {l1};

        List l2 = new List(args, kwargs);

        assertEquals(Int.getInt(1), l2.value.get(0));
        assertEquals(Int.getInt(2), l2.value.get(1));
        assertEquals(Int.getInt(3), l2.value.get(2));
    }

    @Test()
    public void List_withSet() {
        java.util.Set<org.python.Object> s = new java.util.HashSet<org.python.Object>();
        s.add(Int.getInt(1));
        s.add(Int.getInt(2));

        Set s1 = new Set(s);

      /*  java.util.Map<java.lang.String, org.python.Object> kwargs = new java.util.HashMap<java.lang.String, org.python.Object>();
        org.python.Object[] args = {s};

        List l2 = new List(args, kwargs);

        assertEquals(Int.getInt(1), l2.value.get(0));
        assertEquals(Int.getInt(2), l2.value.get(1));
        assertEquals(Int.getInt(3), l2.value.get(2));
        */
    }

    /*
     * __bool__()
     */

    @Test()
    public void __bool__empty() {
        List l1 = new List();

        assertEquals(org.python.types.Bool.FALSE, l1.__bool__());
    }

    @Test()
    public void __bool__str() {
        List l1 = new List();

        l1.append(new Str("foo"));
        assertEquals(org.python.types.Bool.TRUE, l1.__bool__());
    }

    @Test()
    public void __bool__multipleTypes() {
        List l1 = new List();

        l1.append(new Str("foo"));
        l1.append(Int.getInt(0));
        l1.append(new List());
        assertEquals(Bool.TRUE, l1.__bool__());
    }

    @Test()
    public void __bool__None() {
        List l1 = new List();

        l1.append(NoneType.NONE);
        assertEquals(Bool.TRUE, l1.__bool__());
    }

    @Test()
    public void __bool__EmptyList() {
        List l1 = new List();

        l1.append(new List());
        assertEquals(Bool.TRUE, l1.__bool__());
    }
}
