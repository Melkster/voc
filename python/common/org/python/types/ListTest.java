package org.python.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ListTest {

    @Test
    public void testSetItemInteger() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.__setitem__(Int.getInt(0), Int.getInt(5));
        assertEquals(Int.getInt(5), l.__getitem__(Int.getInt(0)));
    }

    @Test
    public void testSetItemString() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        l.__setitem__(Int.getInt(1), new Str("hello"));
        l.__setitem__(Int.getInt(2), new Str("there"));
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(new Str("hello"), l.__getitem__(Int.getInt(1)));
        assertEquals(new Str("there"), l.__getitem__(Int.getInt(2)));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void testSetItemOutOfBound() {
        org.python.types.List l = new List();
        l.__setitem__(Int.getInt(0), Int.getInt(5));
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void testSetItemOutOfBoundsNegativeIndex() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.__setitem__(Int.getInt(-2), Int.getInt(5));
    }

    @Test
    public void testDelItem() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        l.__delitem__(Int.getInt(1));
        assertEquals(Int.getInt(1), l.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(3), l.__getitem__(Int.getInt(1)));
        assertEquals(Int.getInt(2), l.__len__());
    }

    @Test(expected = org.python.exceptions.IndexError.class)
    public void testDelItemOutOfBounds() {
        org.python.types.List l = new List();
        l.__delitem__(Int.getInt(0));
    }

    @Test
    public void testIterator() {
        org.python.types.List l = new List();
        org.python.Object iter = l.__iter__();
        assertTrue(iter instanceof List_Iterator);
    }

    @Test
    public void testReversed() {
        org.python.types.List l = new List();
        org.python.Object reversedIter = l.__reversed__();
        assertTrue(reversedIter instanceof List_ReverseIterator);
    }

    @Test
    public void testContains() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        l.append(Int.getInt(4));
        l.append(Int.getInt(5));

        assertTrue(l.__contains__(Int.getInt(1)).toBoolean());
    }

    @Test
    public void testContainsDoesntExist() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        l.append(Int.getInt(4));
        l.append(Int.getInt(5));

        assertFalse(l.__contains__(Int.getInt(0)).toBoolean());
    }

    @Test
    public void testContainsBool() {
        org.python.types.List l = new List();
        l.append(Bool.getBool(true));
        l.append(Bool.getBool(false));

        // Since True=1 and False=0
        assertTrue(l.__contains__(Int.getInt(1)).toBoolean());
        assertTrue(l.__contains__(Int.getInt(0)).toBoolean());
        assertFalse(l.__contains__(Int.getInt(2)).toBoolean());
    }

    @Test
    public void testAdd() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        org.python.types.List m = new List();
        m.append(Int.getInt(2));
        org.python.types.List n = (List) l.__add__(m);

        assertEquals(Int.getInt(1), n.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), n.__getitem__(Int.getInt(1)));
    }

    @Test
    public void testMulWithInteger() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        org.python.types.List m = (List) l.__mul__(Int.getInt(2));

        assertEquals(m.__getitem__(Int.getInt(0)), m.__getitem__(Int.getInt(3)));
        assertEquals(m.__getitem__(Int.getInt(1)), m.__getitem__(Int.getInt(4)));
        assertEquals(m.__getitem__(Int.getInt(2)), m.__getitem__(Int.getInt(5)));
    }

    @Test
    public void testMulEmpty() {
        org.python.types.List l = new List();

        List mul = (List) l.__mul__(Int.getInt(2));

        assertEquals(Int.getInt(0), mul.__len__());
    }

    @Test
    public void testMulBoolTrue() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));

        List mulTrue = (List) l.__mul__(Bool.getBool(true));

        assertEquals(Int.getInt(1), mulTrue.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), mulTrue.__getitem__(Int.getInt(1)));
    }

    @Test
    public void testMulBoolFalse() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));

        List mulFalse = (List) l.__mul__(Bool.getBool(false));

        assertEquals(Int.getInt(0), mulFalse.__len__());
    }

    @Test
    public void testIMulWithInteger() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));
        l.append(Int.getInt(3));
        org.python.types.List m = (List) l.__imul__(Int.getInt(2));

        assertEquals(m.__getitem__(Int.getInt(0)), m.__getitem__(Int.getInt(3)));
        assertEquals(m.__getitem__(Int.getInt(1)), m.__getitem__(Int.getInt(4)));
        assertEquals(m.__getitem__(Int.getInt(2)), m.__getitem__(Int.getInt(5)));
    }
    

    @Test
    public void testIMulEmpty() {
        org.python.types.List l = new List();

        List mul = (List) l.__imul__(Int.getInt(2));

        assertEquals(Int.getInt(0), mul.__len__());
    }

    @Test
    public void testIMulBoolTrue() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));

        List mulTrue = (List) l.__imul__(Bool.getBool(true));

        assertEquals(Int.getInt(1), mulTrue.__getitem__(Int.getInt(0)));
        assertEquals(Int.getInt(2), mulTrue.__getitem__(Int.getInt(1)));
    }

    @Test
    public void testIMulBoolFalse() {
        org.python.types.List l = new List();
        l.append(Int.getInt(1));
        l.append(Int.getInt(2));

        List mulFalse = (List) l.__imul__(Bool.getBool(false));

        assertEquals(Int.getInt(0), mulFalse.__len__());
    }


}
