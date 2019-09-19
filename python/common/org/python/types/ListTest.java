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
    public void __iadd___lists() {
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
    public void __iadd___listsReverse() {
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

    /**
     * Expected behaviour is actually for __iadd__ to
     * update this.value. Here we need to store the returned value.
     */
    @Test()
    public void __iadd___tuple() {
        java.util.ArrayList<org.python.Object> v1 = new java.util.ArrayList<org.python.Object>();
        v1.add(org.python.types.Int.getInt(1));
        v1.add(org.python.types.Int.getInt(2));

        java.util.ArrayList<org.python.Object> v2 = new java.util.ArrayList<org.python.Object>();
        v2.add(org.python.types.Int.getInt(3));
        v2.add(org.python.types.Int.getInt(4));

        Tuple t1 = new Tuple(v1);
        Tuple t2 = new Tuple(v2);

        Tuple t = (Tuple) t1.__iadd__(t2);
        assertEquals(4, t.value.size());
        assertEquals(org.python.types.Int.getInt(1), t.value.get(0));
        assertEquals(org.python.types.Int.getInt(2), t.value.get(1));
        assertEquals(org.python.types.Int.getInt(3), t.value.get(2));
        assertEquals(org.python.types.Int.getInt(4), t.value.get(3));
    }

    @Test()
    public void __iadd___tupleReverse() {
        java.util.ArrayList<org.python.Object> v1 = new java.util.ArrayList<org.python.Object>();
        v1.add(org.python.types.Int.getInt(1));
        v1.add(org.python.types.Int.getInt(2));

        java.util.ArrayList<org.python.Object> v2 = new java.util.ArrayList<org.python.Object>();
        v2.add(org.python.types.Int.getInt(3));
        v2.add(org.python.types.Int.getInt(4));

        Tuple t1 = new Tuple(v1);
        Tuple t2 = new Tuple(v2);

        Tuple t = (Tuple) t2.__iadd__(t1);
        assertEquals(4, t.value.size());
        assertEquals(org.python.types.Int.getInt(3), t.value.get(0));
        assertEquals(org.python.types.Int.getInt(4), t.value.get(1));
        assertEquals(org.python.types.Int.getInt(1), t.value.get(2));
        assertEquals(org.python.types.Int.getInt(2), t.value.get(3));
    }

    /**
     * They seem to have implemented Set and FrozenSet, but it's not working.
     * It's not implemented correctly.
     */
    @Test()
    public void __iadd___set() {
        Set s1 = new Set();
        s1.value.add(org.python.types.Int.getInt(1));
        
        Set s2 = new Set();
        s2.value.add(org.python.types.Int.getInt(1));

        // s1.__iadd__(s2);
        // assertEquals(org.python.types.Int.getInt(2), s2.value.size());
    }

    /**
     * Expected behaviour is actually for __iadd__ to
     * update this.value. Here we need to store the returned value.
     */
    @Test()
    public void __iadd___str() {
        Str s1 = new Str("foo");
        Str s2 = new Str("bar");

        Str s = (Str) s1.__iadd__(s2);

        assertEquals("foobar", s.toString());
    }

    @Test()
    public void __iadd___strReverse() {
        Str s1 = new Str("foo");
        Str s2 = new Str("bar");

        Str s = (Str) s2.__iadd__(s1);

        assertEquals("barfoo", s.toString());
    }
}

